package com.jiang.practice.beans.factory;

/**
 * 实现此接口，既能感知到所属的 BeanName <br/>
 * Interface to be implemented by beans that want to be aware of their bean name in a bean factory.
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-18
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String name);

}

