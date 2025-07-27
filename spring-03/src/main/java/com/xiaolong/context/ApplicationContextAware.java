package com.xiaolong.context;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.Aware;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/27 16:58
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
