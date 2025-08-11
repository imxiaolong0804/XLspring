package com.xiaolong.beans.factory.support;

import com.xiaolong.beans.factory.DisposableBean;
import com.xiaolong.beans.factory.ObjectFactory;
import com.xiaolong.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
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

    protected static final Object NULL_OBJECT = new Object();

    // 一级缓存，普通对象
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // 二级缓存，提前暴漏对象，没有完全实例化的对象
    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    // 三级缓存，存放代理对象
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String name) {
        Object singletonObject = singletonObjects.get(name);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(name);
            if (null == singletonObject) {
                ObjectFactory<?> objectFactory = singletonFactories.get(name);
                if (null != objectFactory) {
                    singletonObject = objectFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(name, singletonObject);
                    singletonFactories.remove(name);
                }
            }
        }
        return singletonObject;
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

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    public void addSingletonFactory(String beanName, ObjectFactory<?> objectFactory) {
        if (!singletonFactories.containsKey(beanName)) {
            singletonFactories.put(beanName, objectFactory);
            earlySingletonObjects.remove(beanName);
        }
    }

    protected void addSingleton(String name, Object bean) {
        singletonObjects.put(name, bean);
    }

    public void registerDisposableBean(String name, DisposableBean bean) {
        disposableBeans.put(name, bean);
    }
}
