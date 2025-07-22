package com.xiaolong.context.support;

import com.xiaolong.beans.factory.ConfigurableListableBeanFactory;
import com.xiaolong.beans.factory.support.DefaultListableBeanFactory;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 17:30
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() {
        // 获取 默认的 beanFactory
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        // 加载 beanDefinition 信息
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
