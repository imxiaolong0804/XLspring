import com.xiaolong.beans.config.BeanDefinition;
import com.xiaolong.beans.support.DefaultListableBeanFactory;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/6/30 19:12
 */
public class ApiTest3 {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerBeanDefinition("userService", new BeanDefinition<>(UserService.class));

        UserService  userService =  (UserService) beanFactory.getBean("userService", "xiaolong");

        userService.print();

        UserService singleton = (UserService) beanFactory.getBean("userService", "xiaolong");

        singleton.print();

        UserService  bean = (UserService) beanFactory.getBean("userService");
//
        bean.print();
    }
}
