package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.config.BeanDefinition;
import com.xiaolong.beans.factory.config.BeanPostProcessor;
import com.xiaolong.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:13
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    private <T> T doGetBean(String name, Object[] args) {
        Object singleton = getSingleton(name);
        if (null != singleton) {
            return (T) singleton;
        }
        BeanDefinition<?> beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected abstract Object createBean(String name, BeanDefinition<?> beanDefinition, Object[] args);

    protected abstract BeanDefinition<?> getBeanDefinition(String name);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    protected List<BeanPostProcessor> getBeanPostProcessors() {
        return beanPostProcessors;
    }
}
