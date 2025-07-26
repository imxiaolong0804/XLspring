package com.xiaolong.beans.factory;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/26 17:47
 */
public interface InitializingBean {


    /**
     * bean 处理了属性后调用
     * @throws Exception
     */
    void afterPropertiesSet() throws Exception;
}
