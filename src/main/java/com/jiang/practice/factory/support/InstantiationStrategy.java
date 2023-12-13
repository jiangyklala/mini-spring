package com.jiang.practice.factory.support;

import java.lang.reflect.Constructor;

import com.jiang.practice.BeansException;
import com.jiang.practice.factory.config.BeanDefinition;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
