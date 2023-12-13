package com.jiang.practice.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.jiang.practice.BeansException;
import com.jiang.practice.factory.config.BeanDefinition;

/**
 * 管理 BeanDefinition 信息
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    /**
     * 定义注册 BeanDefinition 能力
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    /**
     * 定义获取 BeanDefinition 能力
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) throw new BeansException("No bean named '" + beanName + "' is defined");
        return beanDefinition;
    }

}
