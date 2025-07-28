package com.xiaolong.beans.factory.config;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 11:30
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String name);

    /**
     * 销毁单例对象
     */
    void destroySingletons();

    void registerSingleton(String beanName, Object singletonObject);
}
