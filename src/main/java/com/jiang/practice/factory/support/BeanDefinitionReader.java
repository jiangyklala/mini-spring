package com.jiang.practice.factory.support;

import com.jiang.practice.BeansException;
import com.jiang.practice.beans.core.io.Resource;
import com.jiang.practice.beans.core.io.ResourceLoader;

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

}
