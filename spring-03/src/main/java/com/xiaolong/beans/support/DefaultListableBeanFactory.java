package com.xiaolong.beans.support;

import com.xiaolong.beans.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:21
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    Map<String, BeanDefinition<?>> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    protected BeanDefinition<?> getBeanDefinition(String name) {
        BeanDefinition<?> beanDefinition = beanDefinitionMap.get(name);
        if (null == beanDefinition) {
            throw new RuntimeException("beanDefinition not found");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition<?> beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
