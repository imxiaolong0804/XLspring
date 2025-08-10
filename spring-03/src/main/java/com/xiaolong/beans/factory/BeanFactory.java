package com.xiaolong.beans.factory;

import com.xiaolong.beans.BeansException;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:06
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    Object getBean(String name, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;
}
