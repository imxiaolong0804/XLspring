package com.xiaolong.aop;

import java.lang.reflect.Method;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/30 19:21
 */
public interface MethodMatcher {

    boolean matches(Method method, Class<?> targetClass);
}
