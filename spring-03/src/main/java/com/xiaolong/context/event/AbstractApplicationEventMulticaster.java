package com.xiaolong.context.event;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.BeanFactory;
import com.xiaolong.beans.factory.BeanFactoryAware;
import com.xiaolong.context.ApplicationEvent;
import com.xiaolong.context.ApplicationListener;
import com.xiaolong.utils.ClassUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/28 13:36
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected BeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected Collection<ApplicationListener<?>> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener<?>> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     * @param listener
     * @param event
     * @return
     */
    private boolean supportsEvent(ApplicationListener<ApplicationEvent> listener, ApplicationEvent event) {
        // 1. 获取监听器的实际类型
        Class<? extends ApplicationListener> listenerClass = listener.getClass();

        // 2. 处理代理类情况 - 如果是CGLIB代理，获取其父类
        Class<?> targetClass = ClassUtils.isCjlibProxyClass(listenerClass) ?
                listenerClass.getSuperclass() : listenerClass;

        // 3. 获取第一个泛型接口（ApplicationListener接口）
        Type genericInterface = targetClass.getGenericInterfaces()[0];

        // 4. 获取泛型参数的实际类型
        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();

        // 5. 通过类名加载事件类型
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name");
        }

        // 6. 判断事件类型是否匹配
        return eventClassName.isAssignableFrom(event.getClass());
    }


}
