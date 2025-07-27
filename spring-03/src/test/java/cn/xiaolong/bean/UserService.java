package cn.xiaolong.bean;

import com.xiaolong.beans.BeansException;
import com.xiaolong.beans.factory.BeanFactory;
import com.xiaolong.beans.factory.BeanFactoryAware;
import com.xiaolong.beans.factory.DisposableBean;
import com.xiaolong.beans.factory.InitializingBean;
import com.xiaolong.context.ApplicationContext;
import com.xiaolong.context.ApplicationContextAware;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 14:56
 */
public class UserService implements InitializingBean, DisposableBean, ApplicationContextAware, BeanFactoryAware {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setUid(String uId) {
        this.uId = uId;
    }
    public String getUid() {
        return uId;
    }
    public String getCompany() {
        return company;
    }
    public String getLocation() {
        return location;
    }
    public UserDao getUserDao() {
        return userDao;
    }
    public void print() {
        System.out.println("UserService print");
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + uId);;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
