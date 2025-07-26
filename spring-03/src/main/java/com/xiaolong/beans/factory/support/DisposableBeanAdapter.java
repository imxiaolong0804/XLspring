package com.xiaolong.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.xiaolong.beans.factory.DisposableBean;
import com.xiaolong.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/26 19:20
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition<?> beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (bean instanceof DisposableBean disposableBean) {
            disposableBean.destroy();
        }

        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && destroyMethodName.equals("destroy"))) {
//            Class<?> targetClass = getTargetClass(bean);
//            Method declaredMethod = targetClass.getDeclaredMethod(destroyMethodName);
            Method method = bean.getClass().getMethod(destroyMethodName);
            method.setAccessible(true);
            method.invoke(bean);
            method.setAccessible(false);
        }

    }

    /**
     * 获取目标类（处理CGLIB代理）
     */
    private Class<?> getTargetClass(Object bean) {
        Class<?> clazz = bean.getClass();

        // 如果是CGLIB代理类，获取父类（原始类）
        if (clazz.getName().contains("$$EnhancerByCGLIB$$")) {
            return clazz.getSuperclass();
        }

        return clazz;
    }
}
