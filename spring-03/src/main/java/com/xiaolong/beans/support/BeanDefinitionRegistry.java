package com.xiaolong.beans.support;


import com.xiaolong.beans.config.BeanDefinition;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 14:27
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition<?> beanDefinition);

}
