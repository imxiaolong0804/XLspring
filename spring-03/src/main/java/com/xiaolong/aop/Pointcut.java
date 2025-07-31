package com.xiaolong.aop;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/30 19:20
 */
public interface Pointcut {

    /**
     * return the classFilter of this pointcut
     *
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * return the methodMatcher of this pointcut
     *
     * @return
     */
    MethodMatcher getMethodMatcher();
}
