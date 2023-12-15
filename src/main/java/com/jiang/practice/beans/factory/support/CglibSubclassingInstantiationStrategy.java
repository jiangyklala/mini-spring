package com.jiang.practice.beans.factory.support;

import java.lang.reflect.Constructor;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.NoOp;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.config.BeanDefinition;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用 CGlib 实例化对象
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition, Constructor ctor, Object[] args) throws BeansException {
        log.debug("use cglib instantiate bean");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == ctor) return enhancer.create();
        return enhancer.create(ctor.getParameterTypes(), args);
    }

}

