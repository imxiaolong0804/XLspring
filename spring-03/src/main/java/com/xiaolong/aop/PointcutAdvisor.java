package com.xiaolong.aop;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/5 21:26
 */
public interface PointcutAdvisor extends Advisor{


    Pointcut getPointcut();
}
