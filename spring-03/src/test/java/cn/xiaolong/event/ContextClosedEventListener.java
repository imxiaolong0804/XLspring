package cn.xiaolong.event;

import com.xiaolong.context.ApplicationListener;
import com.xiaolong.context.event.ContextClosedEvent;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/29 20:36
 */
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("关闭事件：" + this.getClass().getName());
    }
}
