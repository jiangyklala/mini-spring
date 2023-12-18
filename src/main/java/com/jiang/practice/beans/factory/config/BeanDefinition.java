package com.jiang.practice.beans.factory.config;

import com.jiang.practice.beans.PropertyValues;

import lombok.Getter;
import lombok.Setter;

/**
 * 用于定义 Bean 的实例化信息
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Getter
@Setter
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    /**
     * Bean 的初始化方法
     */
    private String initMethodName;

    /*
     * Bean 的销毁方法
     */
    private String destroyMethodName;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

}
