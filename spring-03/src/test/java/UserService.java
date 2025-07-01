/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 14:56
 */
public class UserService {

    String name;

    public UserService() {
    }

    public UserService(String name) {
        this.name = name;
    }

    public void print() {
        System.out.printf("hello this is user service !!! %s \n", name);
    }

}
