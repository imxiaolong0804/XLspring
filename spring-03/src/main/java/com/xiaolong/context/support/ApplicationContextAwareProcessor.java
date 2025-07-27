package com.xiaolong.context.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.config.BeanPostProcessor;
import com.xiaolong.context.ApplicationContext;
import com.xiaolong.context.ApplicationContextAware;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/27 16:59
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ApplicationContextAware instance) {
            instance.setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
