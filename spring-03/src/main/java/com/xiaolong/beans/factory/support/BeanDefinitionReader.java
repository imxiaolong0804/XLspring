package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.BeansException;
import com.xiaolong.core.io.Resource;
import com.xiaolong.core.io.ResourceLoader;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/15 14:51
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;
}
