package com.xiaolong.beans.factory.config;

import com.xiaolong.beans.factory.HierarchicalBeanFactory;
import com.xiaolong.utils.StringValueResolver;

/**
 * 可获取 BeanPostProcessor、BeanClassLoader等的一个配置化接口。.
 *
 * @author baixiaolong
 * @date 2025/7/21 14:49
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    /**
     * Add a String resolver for embedded values such as annotation attributes.
     * @param valueResolver the String resolver to apply to embedded values
     * @since 3.0
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);
}
