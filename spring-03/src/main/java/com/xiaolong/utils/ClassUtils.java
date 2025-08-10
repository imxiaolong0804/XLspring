package com.xiaolong.utils;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/15 14:45
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (cl == null) {
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }

    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

    private static boolean isCglibProxyClassName(String name) {
        return (name != null && name.contains("$$"));
    }

}
