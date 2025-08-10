package cn.xiaolong.bean;

import com.xiaolong.beans.factory.annotation.Autowired;
import com.xiaolong.beans.factory.annotation.Value;
import com.xiaolong.stereotype.Component;

import java.util.Random;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/27 22:01
 */
@Component("userService")
public class UserService implements IUserService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    @Override
    public String register(String userName) {
        return "";
    }

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
