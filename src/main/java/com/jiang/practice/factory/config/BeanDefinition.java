package com.jiang.practice.factory.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 用于定义 Bean 的实例化信息
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Getter
@Setter
@AllArgsConstructor
public class BeanDefinition {

    private Class beanClass;

}
