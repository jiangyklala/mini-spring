package com.jiang.practice.beans.factory;

import com.jiang.practice.beans.BeansException;

/**
 * 创建 Bean 的工厂
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    /**
     * 带有参数的 Bean 获取
     */
    Object getBean(String name, Object... args) throws BeansException;

    // 指定 class 类型的 Bean 获取
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

}
