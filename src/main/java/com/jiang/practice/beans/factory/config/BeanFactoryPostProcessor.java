package com.jiang.practice.beans.factory.config;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的 BeanDefinition 加载完成后，实例化 Bean 对象之前，提供修改 BeanDefinition 属性的机制
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
