package com.xiaolong.context.support;

import com.xiaolong.beans.factory.support.DefaultListableBeanFactory;
import com.xiaolong.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中对配置信息的加载.
 *
 * @author baixiaolong
 * @date 2025/7/21 19:10
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
