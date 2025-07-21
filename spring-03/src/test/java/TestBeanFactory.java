import cn.xiaolong.bean.UserDao;
import cn.xiaolong.bean.UserService;
import com.xiaolong.beans.PropertyValue;
import com.xiaolong.beans.PropertyValues;
import com.xiaolong.beans.factory.config.BeanDefinition;
import com.xiaolong.beans.factory.config.BeanReference;
import com.xiaolong.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/7 19:07
 */
public class TestBeanFactory {

    @Test
    public void test1() throws Exception {

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        defaultListableBeanFactory.registerBeanDefinition("userDao", new BeanDefinition<>(UserDao.class));

        UserDao userDao = (UserDao) defaultListableBeanFactory.getBean("userDao");
        System.out.println(userDao.queryUserName("10002"));

        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        defaultListableBeanFactory.registerBeanDefinition("userService", new BeanDefinition<>(UserService.class, propertyValues));

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");
        userService.print();
        userService.queryUserInfo();
    }

}
