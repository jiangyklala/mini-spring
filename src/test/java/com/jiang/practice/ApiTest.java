package com.jiang.practice;


import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import com.jiang.practice.bean.IUserService;
import com.jiang.practice.bean.UserDao;
import com.jiang.practice.bean.UserService;
import com.jiang.practice.bean.UserServiceInterface;
import com.jiang.practice.beans.PropertyValue;
import com.jiang.practice.beans.PropertyValues;
import com.jiang.practice.beans.factory.config.BeanDefinition;
import com.jiang.practice.beans.factory.config.BeanReference;
import com.jiang.practice.beans.factory.support.DefaultListableBeanFactory;
import com.jiang.practice.context.event.ContextClosedEvent;
import com.jiang.practice.context.event.ContextRefreshedEvent;
import com.jiang.practice.context.support.ClassPathXmlApplicationContext;
import com.jiang.practice.core.io.DefaultResourceLoader;
import com.jiang.practice.core.io.Resource;
import com.jiang.practice.event.CustomEvent;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;


/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
public class ApiTest {

    private DefaultResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testBeanFactory(){
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册 bean
        beanFactory.registerBeanDefinition("userDao",  new BeanDefinition(UserDao.class));

        // 3. userService 设置属性 (uid, userDao)
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uid", "10001"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 5. 获取 UserService
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    /**
     * 测试 classpath 信息加载
     */
    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:application.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        log.debug(content);
    }

    /**
     * 测试 file 信息加载
     */
    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/main/resources/application.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        log.debug(content);
    }

    /**
     * 测试 url 信息加载
     */
    @Test
    public void test_url() throws IOException {
        // 网络原因可能导致GitHub不能读取，可以放到自己的 Gitee 仓库。读取后可以从内容中搜索关键字；OLpj9823dZ
        Resource resource = resourceLoader.getResource("https://github.com/fuzhengwei/small-spring/blob/main/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        log.debug(content);
    }

    /**
     * 测试 xml 文件配置信息读取
     */
    @Test
    public void test_xml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 3. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
        log.debug("applicationContextAware: {}", userService.getApplicationContext());
        log.debug("BeanFactoryAware: {}", userService.getBeanFactory());
    }

    @Test
    public void test_prototype() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-3.xml");
        applicationContext.registerShutdownHook();

        // 2. 获取Bean对象调用方法
        IUserService iUserService01 = applicationContext.getBean("userService", IUserService.class);
        IUserService iUserService02 = applicationContext.getBean("userService", IUserService.class);

        // 3. 配置 scope="prototype/singleton"
        System.out.println(iUserService01);
        System.out.println(iUserService02);

        // 4. 打印十六进制哈希
        System.out.println(iUserService01 + " 十六进制哈希：" + Integer.toHexString(iUserService01.hashCode()));
        System.out.println(ClassLayout.parseInstance(iUserService01).toPrintable());

    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-4.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
        applicationContext.publishEvent(new ContextClosedEvent(applicationContext));
        applicationContext.publishEvent(new ContextRefreshedEvent(applicationContext));

        applicationContext.registerShutdownHook();
    }

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-7.xml");
        UserServiceInterface userService = applicationContext.getBean("userService4", UserServiceInterface.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

    @Test
    public void test_autoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-8.xml");
        UserServiceInterface userService = applicationContext.getBean("userService5", UserServiceInterface.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }

}
