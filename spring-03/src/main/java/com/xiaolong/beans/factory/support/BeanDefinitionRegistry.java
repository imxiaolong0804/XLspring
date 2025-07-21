package com.xiaolong.beans.factory.support;


import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.config.BeanDefinition;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 14:27
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String name, BeanDefinition<?> beanDefinition);


    /**
     * 使用Bean名称查询BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition<?> getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的BeanDefinition
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * Return the names of all beans defined in this registry.
     * <p>
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
