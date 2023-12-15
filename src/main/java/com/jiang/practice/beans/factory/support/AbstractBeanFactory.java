package com.jiang.practice.beans.factory.support;

import java.util.ArrayList;
import java.util.List;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.config.BeanDefinition;
import com.jiang.practice.beans.factory.config.BeanPostProcessor;
import com.jiang.practice.beans.factory.config.ConfigurableBeanFactory;

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
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {

    /**
     * BeanPostProcessors to apply in createBean
     */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();


    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    protected <T> T doGetBean(final String name, final Object[] args) {
        Object bean = getSingleton(name);
        if (bean != null) {
            log.debug("get singleton bean named: {}", name);
            return (T) bean;
        }

        log.debug("first get bean named: {}, try to create..", name);
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return (T) createBean(name, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor){
        // 每一次添加都覆盖之前的
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * Return the list of BeanPostProcessors that will get applied
     * to beans created with this factory.
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

}
