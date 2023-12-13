package com.jiang.practice;


import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.jiang.practice.bean.UserService;
import com.jiang.practice.factory.config.BeanDefinition;
import com.jiang.practice.factory.support.DefaultListableBeanFactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
public class ApiTest {

    @Test
    public void testBeanFactory(){
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 第一次获取 bean (需要 create)
        UserService userService = (UserService) beanFactory.getBean("userService", "jiang");
        userService.queryUserInfo();

        // 4. 第二次获取 bean (直接在内存中取)
        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService", "jiang");
        userService.queryUserInfo();
    }

    @Test
    public void testNewInstanceWithoutArgs() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        UserService userService = UserService.class.getDeclaredConstructor().newInstance();
        log.info(userService.toString());
    }

    @Test
    public void testNewInstanceWithArgs() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        UserService userService = UserService.class.getDeclaredConstructor().newInstance();
        log.info(userService.toString());
    }


}
