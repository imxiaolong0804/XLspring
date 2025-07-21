package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:36
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition<?> beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException {
        try {
            Class<?> beanClass = beanDefinition.getBeanClass();
            if (null != constructor) {
                return beanClass.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            } else {
                return beanClass.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate bean", e);
        }
    }
}
