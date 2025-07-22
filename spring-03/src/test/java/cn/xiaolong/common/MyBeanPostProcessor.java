package cn.xiaolong.common;

import cn.xiaolong.bean.UserService;
import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.config.BeanPostProcessor;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 20:20
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
