package com.xiaolong.beans.factory;

import com.xiaolong.beans.BeansException;

/**
 * 类的简要描述.
 * 实现此接口，既能感知到所属的 BeanFactory
 *
 * @author baixiaolong
 * @date 2025/7/27 16:57
 */
public interface BeanFactoryAware extends Aware{

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
