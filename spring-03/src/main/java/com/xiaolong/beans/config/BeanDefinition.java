package com.xiaolong.beans.config;

import com.xiaolong.PropertyValues;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:11
 */
public class BeanDefinition<T> {

    private Class<T> beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class<T> beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues;
    }

    public Class<T> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
}
