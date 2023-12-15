package com.jiang.practice.common;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.PropertyValue;
import com.jiang.practice.beans.PropertyValues;
import com.jiang.practice.beans.factory.ConfigurableListableBeanFactory;
import com.jiang.practice.beans.factory.config.BeanDefinition;
import com.jiang.practice.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-15
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();

        propertyValues.addPropertyValue(new PropertyValue("company", "kuaishou"));
    }

}
