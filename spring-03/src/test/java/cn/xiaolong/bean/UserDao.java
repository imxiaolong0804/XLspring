package cn.xiaolong.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/7 19:05
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "小龙");
        hashMap.put("10002", "小付哥");
        hashMap.put("10003", "小王");
    }

    public String queryUserName(String uid) {
        return hashMap.get(uid);
    }

}
