package com.xiaolong.beans.support;

import com.xiaolong.PropertyValue;
import com.xiaolong.PropertyValues;
import com.xiaolong.beans.config.BeanDefinition;
import com.xiaolong.beans.config.BeanReference;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    CglibSubclassingInstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    SimpleInstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition<?> beanDefinition, Object[] args) {
        Object bean;
        try {
            // jdk 或者 cglib 创建实例
            bean = createBeanInstance(beanDefinition, name, args);
            applyPropertyValues(bean, name, beanDefinition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        addSingleton(name, bean);
        return bean;
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
//        return simpleInstantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }
}
