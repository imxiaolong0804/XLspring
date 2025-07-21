package com.xiaolong.beans;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 17:10
 */
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
