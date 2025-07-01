package com.xiaolong.beans.support;

import com.xiaolong.BeansException;
import com.xiaolong.beans.BeanFactory;
import com.xiaolong.beans.config.BeanDefinition;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:13
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    private Object doGetBean(String name, Object[] args) {
        Object singleton = getSingleton(name);
        if (null != singleton) {
            return singleton;
        }
        BeanDefinition<?> beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition, args);
    }

    protected abstract Object createBean(String name, BeanDefinition<?> beanDefinition, Object[] args);

    protected abstract BeanDefinition<?> getBeanDefinition(String name);
}
