package com.jiang.practice.factory.support;

import com.jiang.practice.factory.config.BeanDefinition;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 BeanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
