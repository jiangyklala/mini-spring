package com.jiang.practice.beans.factory.support;

import java.lang.reflect.Constructor;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.config.BeanDefinition;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public interface InstantiationStrategy {

    Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctor, Object[] args) throws BeansException;

}
