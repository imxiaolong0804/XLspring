package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.ConfigurableListableBeanFactory;
import com.xiaolong.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:21
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    Map<String, BeanDefinition<?>> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public BeanDefinition<?> getBeanDefinition(String name) {
        BeanDefinition<?> beanDefinition = beanDefinitionMap.get(name);
        if (null == beanDefinition) {
            throw new RuntimeException("beanDefinition not found");
        }
        return beanDefinition;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> map = new HashMap<>();
        beanDefinitionMap.forEach((k, v) -> {
            if (type.isAssignableFrom(v.getBeanClass())) {
                map.put(k, (T) getBean(k));
            }
        });
        return map;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition<?> beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
