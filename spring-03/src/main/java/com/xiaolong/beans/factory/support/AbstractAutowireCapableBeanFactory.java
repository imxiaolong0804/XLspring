package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.PropertyValue;
import com.xiaolong.beans.PropertyValues;
import com.xiaolong.beans.factory.config.AutowireCapableBeanFactory;
import com.xiaolong.beans.factory.config.BeanDefinition;
import com.xiaolong.beans.factory.config.BeanPostProcessor;
import com.xiaolong.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

//    SimpleInstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition<?> beanDefinition, Object[] args) {
        Object bean;
        try {
            // jdk 或者 cglib 创建实例
            bean = createBeanInstance(beanDefinition, name, args);
            applyPropertyValues(bean, name, beanDefinition);
            // 执行 bean 的初始化方法 和 beanPostProcessor 的前置和后置处理方法
            bean = initializeBean(name, bean, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        addSingleton(name, bean);
        return bean;
    }

    private Object initializeBean(String name, Object bean, BeanDefinition<?> beanDefinition) {
        // 执行 bean 前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, name);

        // TODO 待完成内容
        invokeInitMethods(name, wrappedBean, beanDefinition);

        // 执行 bean 后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, name);
        return wrappedBean;
    }

    private void invokeInitMethods(String name, Object wrappedBean, BeanDefinition<?> beanDefinition) {

    }

    /**
     * bean 属性填充
     *
     * @param bean
     * @param name
     * @param beanDefinition
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
        return instantiationStrategy;
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
