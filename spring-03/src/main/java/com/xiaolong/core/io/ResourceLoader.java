package com.xiaolong.core.io;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/15 14:40
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
