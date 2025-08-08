package com.xiaolong.stereotype;

import java.lang.annotation.*;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/7 21:11
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Component {

    String value() default "";
}
