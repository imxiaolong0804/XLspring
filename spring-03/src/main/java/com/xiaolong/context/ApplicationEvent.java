package com.xiaolong.context;

import java.util.EventObject;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/28 11:13
 */
public abstract class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
