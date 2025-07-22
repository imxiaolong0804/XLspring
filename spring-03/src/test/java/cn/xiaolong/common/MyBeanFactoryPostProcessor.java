package cn.xiaolong.common;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.PropertyValue;
import com.xiaolong.beans.PropertyValues;
import com.xiaolong.beans.factory.ConfigurableListableBeanFactory;
import com.xiaolong.beans.factory.config.BeanDefinition;
import com.xiaolong.beans.factory.config.BeanFactoryPostProcessor;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 20:19
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition<?> beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "改为：字节跳动"));
    }
}
