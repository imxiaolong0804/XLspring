package com.xiaolong.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/9 23:07
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.METHOD})
@Documented
public @interface Autowired {
}
