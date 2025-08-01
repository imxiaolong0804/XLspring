package com.xiaolong.beans.factory.config;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.ConfigurableListableBeanFactory;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 17:07
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;

}
