package com.xiaolong.aop.aspectj;

import com.xiaolong.aop.Pointcut;
import com.xiaolong.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/5 21:29
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
