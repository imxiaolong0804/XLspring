package cn.xiaolong;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.XmlUtil;
import cn.xiaolong.bean.IUserService;
import cn.xiaolong.bean.UserDao;
import cn.xiaolong.bean.UserService;
import cn.xiaolong.bean.UserServiceInterceptor;
import cn.xiaolong.event.CustomEvent;
import com.xiaolong.aop.AdvisedSupport;
import com.xiaolong.aop.TargetSource;
import com.xiaolong.aop.aspectj.AspectJExpressionPointcut;
import com.xiaolong.aop.framework.Cglib2AopProxy;
import com.xiaolong.aop.framework.JdkDynamicAopProxy;
import com.xiaolong.beans.factory.support.DefaultListableBeanFactory;
import com.xiaolong.beans.factory.xml.XmlBeanDefinitionReader;
import com.xiaolong.context.support.ClassPathXmlApplicationContext;
import com.xiaolong.core.io.DefaultResourceLoader;
import com.xiaolong.core.io.Resource;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.lang.reflect.Method;

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
        Resource resource = resourceLoader.getResource("classpath:token.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_scan2() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
        System.out.println(userService);
    }


    @Test
    public void test_property() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        userService.queryUserInfo();
        System.out.println(userService);
    }

    @Test
    public void test_scan() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());

    }

    // 第六章 资源加载测试
    @Test
    public void test_xml() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_documentXml() throws Exception {
        Resource resource = resourceLoader.getResource("classpath:spring.xml");
        InputStream inputStream = resource.getInputStream();

        Document document = XmlUtil.readXML(inputStream);
        Element rootElement = document.getDocumentElement();
        NodeList childNodes = rootElement.getChildNodes();

    }

//    @Test
//    @Ignore
//    public void test_beanFactoryPostProcessorAndBeanPostProcessor() throws Exception {
//        // 1 初始化 beanfactory
//        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//
//        // 2 读取配置
//        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
//
//        // 3  BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
//        MyBeanFactoryPostProcessor myBeanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
//        myBeanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
//
//        // 4. Bean实例化之后，修改 Bean 属性信息
//        MyBeanPostProcessor myBeanPostProcessor = new MyBeanPostProcessor();
//        beanFactory.addBeanPostProcessor(myBeanPostProcessor);
//
//        // 5. 获取Bean对象调用方法
//        UserService userService = beanFactory.getBean("userService", UserService.class);
//        userService.queryUserInfo();
//    }

//    @Test
//    public void test_useContext() throws Exception {
//        // 1 初始化 beanfactory
//        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//
//        // 2 获取 bean 对象调用方法
//        UserService userService = applicationContext.getBean("userService", UserService.class);
//        userService.queryUserInfo();
//    }


    @Test
    public void test_init() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        UserService userService = applicationContext.getBean("userService", UserService.class);

        userService.queryUserInfo();
//        System.out.println(userService.getBeanFactory());
//        System.out.println(userService.getApplicationContext());
    }

    @Test
    public void test_method() throws Exception {

        UserDao userDao = new UserDao();
        Method method = userDao.getClass().getMethod("destroyDataMethod");
        method.invoke(userDao);
    }

    @Test
    public void test_prototype() throws Exception {

        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        UserService userService01 = applicationContext.getBean("userService", UserService.class);
        UserService userService02 = applicationContext.getBean("userService", UserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(userService01);
        System.out.println(userService02);

        System.out.println(userService01.queryUserInfo());

        // 4. 打印十六进制哈希
        System.out.println(userService01 + " 十六进制哈希：" + Integer.toHexString(userService01.hashCode()));
//        System.out.println(ClassLayout.parseInstance(userService01).toPrintable());
    }


    @Test
    public void test_classname() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        System.out.println(userServiceClass.getName());
    }

    @Test
    public void test_event() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1092012L, "hello world"));

        applicationContext.registerShutdownHook();


    }

//    @Test
//    public void test_proxy_method() throws Exception {
//        UserService userService = new UserService();
//
//    }


    @Test
    public void test_aaa() throws Exception {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* cn.xiaolong.bean.UserService.*(..))");
        Class<UserService> userServiceClass = UserService.class;
        Method method = userServiceClass.getDeclaredMethod("queryUserInfo");

        System.out.println(aspectJExpressionPointcut.matches(userServiceClass));
        System.out.println(aspectJExpressionPointcut.matches(method, userServiceClass));

    }

    @Test
    public void test_dynamic() throws Exception {
        // 目标对象
        IUserService userService = new UserService();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* cn.xiaolong.bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

    @Test
    public void test_aop() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
