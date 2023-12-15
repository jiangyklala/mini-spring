package com.jiang.practice.beans.factory.support;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.core.io.Resource;
import com.jiang.practice.core.io.ResourceLoader;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String... locations) throws BeansException;

}
