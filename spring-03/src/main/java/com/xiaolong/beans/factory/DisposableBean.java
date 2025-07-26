package com.xiaolong.beans.factory;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/26 17:48
 */
public interface DisposableBean {


    /**
     * 销毁方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
