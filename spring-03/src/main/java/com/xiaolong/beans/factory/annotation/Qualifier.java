package com.xiaolong.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/9 23:08
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
