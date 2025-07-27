package com.xiaolong.beans.factory;

import com.xiaolong.beans.BeansException;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/27 21:41
 */
public interface FactoryBean<T> {

    /**
     * 获取对象
     * @return
     * @throws BeansException
     */
    T getObject() throws BeansException;

    /**
     * 对象类型
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否单例
     * @return
     */
    boolean isSingleton();

}
