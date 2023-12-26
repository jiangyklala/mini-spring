package com.jiang.practice.context.annotation;

import java.util.LinkedHashSet;
import java.util.Set;

import com.jiang.practice.beans.factory.config.BeanDefinition;
import com.jiang.practice.stereotype.Component;

import cn.hutool.core.util.ClassUtil;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-26
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 通过 "配置路径" 可以解析出 classes 信息的工具方法
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }

}
