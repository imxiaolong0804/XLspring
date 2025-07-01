package com.xiaolong.beans.support;

import com.xiaolong.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:19
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    CglibSubclassingInstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    SimpleInstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String name, BeanDefinition<?> beanDefinition, Object[] args) {
        Object bean;
        try {
            // jdk 或者 cglib 创建实例
            bean = createBeanInstance(beanDefinition, name, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        addSingleton(name, bean);
        return bean;
    }


    public Object createBeanInstance(BeanDefinition<?> beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 拿到需要的构造器
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
//        return simpleInstantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
        return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
    }
}
