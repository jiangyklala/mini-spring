package com.jiang.practice.context.support;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.ConfigurableListableBeanFactory;
import com.jiang.practice.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-15
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }

}
