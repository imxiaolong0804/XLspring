package com.xiaolong.context;

import java.util.EventListener;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/28 13:32
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {


    void onApplicationEvent(E event);

}
