package com.xiaolong.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/9 23:09
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
