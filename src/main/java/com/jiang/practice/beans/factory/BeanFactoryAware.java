package com.jiang.practice.beans.factory;

import com.jiang.practice.beans.BeansException;

/**
 * 实现此接口，既能感知到所属的 BeanFactory
 * Interface to be implemented by beans that wish to be aware of their owning {@link BeanFactory}.
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-18
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
