package com.xiaolong.beans.config;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:11
 */
public class BeanDefinition<T> {

    private Class<T> beanClass;

    public BeanDefinition(Class<T> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<T> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<T> beanClass) {
        this.beanClass = beanClass;
    }
}
