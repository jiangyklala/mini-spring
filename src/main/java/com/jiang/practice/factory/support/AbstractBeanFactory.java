package com.jiang.practice.factory.support;

import com.jiang.practice.BeansException;
import com.jiang.practice.factory.BeanFactory;
import com.jiang.practice.factory.config.BeanDefinition;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板模式, 对外提供 getBean() 的流程, 对内定义 getBeanDefinition, createBean 抽象方法
 * <p>
 * 作为获取 Bean 能力的收口类:
 * 1. 继承自 DefaultSingletonBeanRegistry 获取单例 Bean 的注册能力
 * 2. 实现自 BeanFactory 获取普通 Bean 的能力
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            log.debug("get singleton bean named: {}", name);
            return bean;
        }

        log.debug("first get bean named: {}, try to create..", name);
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;

}
