package com.xiaolong.context.event;

import com.xiaolong.beans.factory.BeanFactory;
import com.xiaolong.context.ApplicationEvent;
import com.xiaolong.context.ApplicationListener;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/28 17:40
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
