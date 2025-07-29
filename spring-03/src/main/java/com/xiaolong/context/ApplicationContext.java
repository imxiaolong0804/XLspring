package com.xiaolong.context;

import com.xiaolong.beans.factory.HierarchicalBeanFactory;
import com.xiaolong.beans.factory.ListableBeanFactory;
import com.xiaolong.core.io.ResourceLoader;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 17:14
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
