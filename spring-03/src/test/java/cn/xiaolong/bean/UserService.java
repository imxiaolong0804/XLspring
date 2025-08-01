package cn.xiaolong.bean;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/27 22:01
 */
public class UserService {

    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }
    public void setUId(String uId) {
        this.uId = uId;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public IUserDao getUserDao() {
        return userDao;
    }
    public String getUId() {
        return uId;
    }
    public String getCompany() {
        return company;
    }
}
