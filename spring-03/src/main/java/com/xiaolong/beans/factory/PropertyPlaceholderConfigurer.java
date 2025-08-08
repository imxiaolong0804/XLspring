package com.xiaolong.beans.factory;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.PropertyValue;
import com.xiaolong.beans.PropertyValues;
import com.xiaolong.beans.factory.config.BeanDefinition;
import com.xiaolong.beans.factory.config.BeanFactoryPostProcessor;
import com.xiaolong.core.io.DefaultResourceLoader;
import com.xiaolong.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/7 20:49
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {


    public static final String PLACEHOLDER_PREFIX = "${";

    public static final String PLACEHOLDER_SUFFIX = "}";

    private String location;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition<?> beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    String strVal = (String) value;
                    StringBuilder buffer = new StringBuilder(strVal);
                    int startIdx = strVal.indexOf(PLACEHOLDER_PREFIX);
                    int stopIdx = strVal.indexOf(PLACEHOLDER_SUFFIX);
                    if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
                        String propKey = strVal.substring(startIdx + 2, stopIdx);
                        String propVal = properties.getProperty(propKey);
                        buffer.replace(startIdx, stopIdx + 1, propVal);
                        propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), buffer.toString()));
                    }
                }
            }
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
