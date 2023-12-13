package com.jiang.practice.factory;

import com.jiang.practice.BeansException;

/**
 * 创建 Bean 的工厂
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

    /**
     * 带有参数的 bean 获取
     */
    Object getBean(String name, Object... args) throws BeansException;

}
