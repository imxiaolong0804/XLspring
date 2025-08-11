package com.xiaolong.beans.factory;

import com.xiaolong.beans.BeansException;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/8/11 13:28
 */
public interface ObjectFactory<T> {


    T getObject() throws BeansException;
}
