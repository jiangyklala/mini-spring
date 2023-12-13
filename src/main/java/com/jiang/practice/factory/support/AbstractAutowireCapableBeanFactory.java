package com.jiang.practice.factory.support;

import java.lang.reflect.InvocationTargetException;

import com.jiang.practice.BeansException;
import com.jiang.practice.factory.config.BeanDefinition;

/**
 * 管理创建 Bean 的能力
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 只专注实现 AbstractBeanFactory 的 createBean 方法, getBeanDefinition 方法交由接下来的子类 DefaultListableBeanFactory 去实现, 各司其职
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

}

