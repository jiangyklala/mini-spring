package com.jiang.practice.beans.factory;

/**
 * 实现此接口，既能感知到所属的 ClassLoader <br/>
 * Callback that allows a bean to be aware of the bean {@link ClassLoader classLoader}; that is, the class loader used by the present bean factory to load bean classes.
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-18
 */
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader);

}