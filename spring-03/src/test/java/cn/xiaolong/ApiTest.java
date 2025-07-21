package cn.xiaolong;

import cn.hutool.core.io.IoUtil;
import cn.xiaolong.bean.UserService;
import com.xiaolong.beans.factory.support.DefaultListableBeanFactory;
import com.xiaolong.beans.factory.xml.XmlBeanDefinitionReader;
import com.xiaolong.core.io.DefaultResourceLoader;
import com.xiaolong.core.io.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * 类的简要描述.
 *
 * @author baixiaolong
 * @date 2025/7/21 15:31
 */
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void inti() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_classpath() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

}
