package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.ConfigurableListableBeanFactory;
import com.xiaolong.beans.factory.config.BeanDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public void preInstantiateSingletons() {
        // 这里相当于就实例化了单例对象
        beanDefinitionMap.keySet().forEach(this::getBean);
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

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition<?>> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (requiredType.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
        }
        if (1 == beanNames.size()) {
            return getBean(beanNames.get(0), requiredType);
        }

        throw new BeansException(requiredType + "expected single bean but found " + beanNames.size() + ": " + beanNames);
    }
}
