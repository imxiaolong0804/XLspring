package cn.xiaolong.bean;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 14:56
 */
public class UserService {

    private String name;

    private UserDao userDao;

    private String location;

    private String company;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public UserService(String name, UserDao userDao) {
        this.name = name;
        this.userDao = userDao;
    }

    public void print() {
        System.out.printf("hello this is user service !!! %s \n", name);
    }

    public void queryUserInfo() {
        System.out.printf("查询用户信息：%s, 公司 = %s， 地点 = %s", userDao.queryUserName(name), company, location);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCompany(String company) {
        this.company = company;
    }

}
