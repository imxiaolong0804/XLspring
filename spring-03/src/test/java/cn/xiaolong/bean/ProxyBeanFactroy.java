package cn.xiaolong.bean;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.util.HashMap;
import java.util.Map;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/27 22:02
 */
public class ProxyBeanFactroy implements FactoryBean<IUserDao> {

    @Override
    public IUserDao getObject() throws BeansException {
        InvocationHandler handler = (proxy, method, args) -> {
            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "小傅哥");
            hashMap.put("10002", "八杯水");
            hashMap.put("10003", "阿毛");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };
        return (IUserDao) java.lang.reflect.Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{IUserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IUserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
