package com.jiang.practice.beans.factory.config;

import com.jiang.practice.beans.BeansException;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public interface BeanPostProcessor {

    /**
     * 在 Bean 对象执行初始化方法之前，执行此初始化方法
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在 Bean 对象执行初始化方法之后，执行此初始化方法
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

}

