package com.jiang.practice;


import org.junit.Test;

import com.jiang.practice.bean.UserService;
import com.jiang.practice.factory.config.BeanDefinition;
import com.jiang.practice.factory.support.DefaultListableBeanFactory;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public class ApiTest {

    @Test
    public void test_BeanFactory(){
        // 1. 初始化 BeanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册 bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 第一次获取 bean (需要 create)
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4. 第二次获取 bean (直接在内存中取)
        UserService userServiceSingleton = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
