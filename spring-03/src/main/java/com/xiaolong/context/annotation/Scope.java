package com.xiaolong.context.annotation;

import java.lang.annotation.*;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/7 21:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface Scope {

    String value() default "singleton";
}
