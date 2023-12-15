package com.jiang.practice;


import org.junit.Test;

import com.jiang.practice.bean.UserService;
import com.jiang.practice.beans.factory.support.DefaultListableBeanFactory;
import com.jiang.practice.beans.factory.xml.XmlBeanDefinitionReader;
import com.jiang.practice.common.MyBeanFactoryPostProcessor;
import com.jiang.practice.common.MyBeanPostProcessor;
import com.jiang.practice.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-15
 */
@Slf4j
public class ApplicationContextTest {

    /**
     * 测试 spring.xml 文件中不含 BeanFactoryPostProcessor 和 BeanPostProcessor 两个 Bean, 手动创建的效果
     */
    @Test
    public void testBeanFactoryPostProcessorAndBeanPostProcessor(){
        // 1.初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 读取配置文件&注册Bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");

        // 3. BeanDefinition 加载完成 & Bean实例化之前，修改 BeanDefinition 的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);

        // 4. Bean实例化之后，修改 Bean 属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5. 获取Bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    /**
     * 测试只使用 xml 文件配置的效果
     */
    @Test
    public void testBeanFactoryPostProcessorAndBeanPostProcessorWithXml() {
        // 1.初始化 BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-2.xml");

        // 2. 获取Bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }


}
