package com.xiaolong.aop;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/30 19:20
 */
public interface ClassFilter {

    boolean matches(Class<?> clazz);
}
