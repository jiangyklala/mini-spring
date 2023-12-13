package com.jiang.practice;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用于定义 Bean 的实例化信息
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Getter
@AllArgsConstructor
public class BeanDefinition {

    private Object bean;

}
