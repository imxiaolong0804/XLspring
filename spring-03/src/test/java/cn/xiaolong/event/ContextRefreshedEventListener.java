package cn.xiaolong.event;

import com.xiaolong.context.ApplicationListener;
import com.xiaolong.context.event.ContextRefreshedEvent;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/29 20:34
 */
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("刷新事件：" + this.getClass().getName());
    }
}
