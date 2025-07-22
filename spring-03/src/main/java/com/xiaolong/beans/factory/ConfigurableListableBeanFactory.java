package com.xiaolong.beans.factory;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.config.AutowireCapableBeanFactory;
import com.xiaolong.beans.factory.config.BeanDefinition;
import com.xiaolong.beans.factory.config.ConfigurableBeanFactory;

/**
 * 提供分析和修改Bean以及预先实例化的操作接口，不过目前只有一个 getBeanDefinition 方法。
 *
 * @author baixiaolong
 * @date 2025/7/21 14:50
 */
public interface ConfigurableListableBeanFactory extends ConfigurableBeanFactory, ListableBeanFactory, AutowireCapableBeanFactory {

    BeanDefinition<?> getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons();
}
