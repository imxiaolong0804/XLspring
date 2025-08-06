package com.xiaolong.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.PropertyValue;
import com.xiaolong.beans.PropertyValues;
import com.xiaolong.beans.factory.*;
import com.xiaolong.beans.factory.config.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    SimpleInstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition<?> beanDefinition, Object[] args) {
        Object bean;
        try {
            // 判断是否返回代理 bean 对象
            bean = resolveBeforeInstantiation(name, beanDefinition);
            if (null != bean) {
                return bean;
            }
            // jdk 或者 cglib 创建实例
            bean = createBeanInstance(beanDefinition, name, args);
            applyPropertyValues(bean, name, beanDefinition);
            // 执行 bean 的初始化方法 和 beanPostProcessor 的前置和后置处理方法
            bean = initializeBean(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 注册实现了 disposable 接口的
        registerDisposableBeanIfNecessary(name, bean, beanDefinition);

        if (beanDefinition.isSingleton()) {
            addSingleton(name, bean);
        }
        return bean;
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition) {
        Object bean = applyBeanPostProcessorBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (null != bean) {
            bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        }
        return bean;
    }

    // 注意，此方法为新增方法，与 “applyBeanPostProcessorBeforeInitialization” 是两个方法
    public Object applyBeanPostProcessorBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            if (processor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) processor).postProcessBeforeInstantiation(beanClass, beanName);
                if (null != result) return result;
            }
        }
        return null;
    }

    private void registerDisposableBeanIfNecessary(String name, Object bean, BeanDefinition<?> beanDefinition) {
        // 如果不是单例模式，直接返回
        if (!beanDefinition.isSingleton()) {
            return;
        }
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(name, new DisposableBeanAdapter(bean, name, beanDefinition));
        }
    }

    private Object initializeBean(String name, Object bean, BeanDefinition<?> beanDefinition) {

        if (bean instanceof Aware) {
            if (bean instanceof BeanFactoryAware factoryAware) {
                factoryAware.setBeanFactory(this);
            }
            if (bean instanceof BeanNameAware beanNameAware) {
                beanNameAware.setBeanName(name);
            }
            if (bean instanceof BeanClassLoaderAware classLoaderAware) {
                classLoaderAware.setBeanClassLoader(this.getDefaultClassLoader());
            }
        }


        // 执行 bean 前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, name);

        // TODO 待完成内容
        try {
            invokeInitMethods(name, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 执行 bean 后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, name);
        return wrappedBean;
    }

    private void invokeInitMethods(String name, Object wrappedBean, BeanDefinition<?> beanDefinition) throws Exception {
        // 1 实现了 initalizingBean 接口
        if (wrappedBean instanceof InitializingBean initial) {
            initial.afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)) {
            Class<?> beanClass = beanDefinition.getBeanClass();
            Method declaredMethod = beanClass.getDeclaredMethod(initMethodName);
            if (null == declaredMethod) {
                throw new RuntimeException("init method not found");
            }
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(wrappedBean);
            declaredMethod.setAccessible(false);
        }
    }

    /**
     * bean 属性填充
     */
    private void applyPropertyValues(Object bean, String beanName, BeanDefinition<?> beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            if (null == propertyValues) {
                return;
            }
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference beanReference) {
                    value = getBean(beanReference.getName());
                }
                Class<?> beanClass = beanDefinition.getBeanClass();
                Field field = beanClass.getDeclaredField(name);
                field.setAccessible(true);
                field.set(bean, value);
                field.setAccessible(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Object createBeanInstance(BeanDefinition<?> beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 拿到需要的构造器
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        if (null != args) {
            for (Constructor<?> ctor : declaredConstructors) {
                if (ctor.getParameterTypes().length == args.length) {
                    constructorToUse = ctor;
                    break;
                }
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return simpleInstantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (null == current) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
