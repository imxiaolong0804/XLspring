package com.xiaolong.beans.factory;

import com.xiaolong.beans.BeansException;

import java.util.Map;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 14:48
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     * <p>
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();

}
