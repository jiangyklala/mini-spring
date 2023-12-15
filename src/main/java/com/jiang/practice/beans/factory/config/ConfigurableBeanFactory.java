package com.jiang.practice.beans.factory.config;

import com.jiang.practice.beans.factory.BeanFactory;
import com.jiang.practice.beans.factory.HierarchicalBeanFactory;

/**
 * Configuration interface to be implemented by most bean factories. Provides
 * facilities to configure a bean factory, in addition to the bean factory
 * client methods in the {@link BeanFactory}
 * interface.
 * <p>
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}