package com.jiang.practice.factory.config;

/**
 * 单例 Bean 注册接口
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
