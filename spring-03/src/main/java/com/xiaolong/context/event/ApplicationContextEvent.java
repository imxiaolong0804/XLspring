package com.xiaolong.context.event;

import com.xiaolong.context.ApplicationContext;
import com.xiaolong.context.ApplicationEvent;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/28 11:14
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
