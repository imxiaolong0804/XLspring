package cn.xiaolong.event;

import com.xiaolong.context.ApplicationListener;

import java.util.Date;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/29 20:32
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
