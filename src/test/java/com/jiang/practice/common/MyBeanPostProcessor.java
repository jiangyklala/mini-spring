package com.jiang.practice.common;

import com.jiang.practice.bean.UserService;
import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.config.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-15
 */
@Slf4j
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.debug("trap in MyBeanPostProcessor#postProcessBeforeInitialization");
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.debug("trap in MyBeanPostProcessor#postProcessAfterInitialization");
        return bean;
    }

}