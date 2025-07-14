/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 14:56
 */
public class UserService {

    private String name;

    private UserDao userDao;

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
        System.out.printf("查询用户信息：%s \n", userDao.queryUserName(name));
    }

}
