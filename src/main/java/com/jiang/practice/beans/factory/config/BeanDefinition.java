package com.jiang.practice.beans.factory.config;

import com.jiang.practice.beans.PropertyValues;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用于定义 Bean 的实例化信息
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Getter
@Setter
@ToString
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private String scope = SCOPE_SINGLETON;

    private boolean singleton = true;

    private boolean prototype = false;

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

    // 在 xml 注册 Bean 定义时，通过 scope 字段来判断是单例还是原型
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
}
