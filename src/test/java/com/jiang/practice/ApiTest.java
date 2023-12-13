package com.jiang.practice;


import org.junit.Test;

import com.jiang.practice.bean.UserDao;
import com.jiang.practice.bean.UserService;
import com.jiang.practice.beans.PropertyValue;
import com.jiang.practice.beans.PropertyValues;
import com.jiang.practice.factory.config.BeanDefinition;
import com.jiang.practice.factory.config.BeanReference;
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

}
