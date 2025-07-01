package com.xiaolong.beans.support;

import com.xiaolong.BeansException;
import com.xiaolong.beans.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:34
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition<?> beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException;

}
