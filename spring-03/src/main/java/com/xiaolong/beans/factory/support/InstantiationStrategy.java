package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 创建实例接口
 *
 * @author baixiaolong
 * @date 2025/6/30 17:34
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition<?> beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException;

}
