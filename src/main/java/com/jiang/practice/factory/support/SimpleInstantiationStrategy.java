package com.jiang.practice.factory.support;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.jiang.practice.BeansException;
import com.jiang.practice.factory.config.BeanDefinition;

import lombok.extern.slf4j.Slf4j;

/**
 * 利用 JDK 自带方法实例化对象
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        log.debug("user jdk instantiate bean");
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                // 符合入参信息的构造函数存在, 传入构造信息, 进行带参实例化
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                // 否则就用默认构造器
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}
