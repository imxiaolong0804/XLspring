package cn.xiaolong.bean;

import com.xiaolong.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/6 19:36
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before method: " + method.getName());
    }
}
