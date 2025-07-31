package com.xiaolong.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/30 19:53
 */
public class AdvisedSupport {

    // 被代理的目标对象
    private TargetSource targetSource;

    // 方法拦截器
    private MethodInterceptor methodInterceptor;

    // 方法匹配器
    private MethodMatcher methodMatcher;

    // get、set方法
    public TargetSource getTargetSource() {
        return targetSource;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
