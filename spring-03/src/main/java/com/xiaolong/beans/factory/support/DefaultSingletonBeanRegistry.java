package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.factory.DisposableBean;
import com.xiaolong.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 11:32
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String name) {
        return singletonObjects.get(name);
    }

    @Override
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new RuntimeException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

    protected void addSingleton(String name, Object bean) {
        singletonObjects.put(name, bean);
    }

    public void registerDisposableBean(String name, DisposableBean bean) {
        disposableBeans.put(name, bean);
    }
}
