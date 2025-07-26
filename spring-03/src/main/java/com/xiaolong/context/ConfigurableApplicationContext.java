package com.xiaolong.context;

import com.xiaolong.beans.BeansException;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 17:15
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
