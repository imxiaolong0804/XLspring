package com.xiaolong.context.event;

import com.xiaolong.context.ApplicationEvent;
import com.xiaolong.context.ApplicationListener;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/28 13:31
 */
public interface ApplicationEventMulticaster {

    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
