package com.xiaolong.context.event;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/28 11:16
 */
public class ContextClosedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ContextClosedEvent(Object source) {
        super(source);
    }
}
