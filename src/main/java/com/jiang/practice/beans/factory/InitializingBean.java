package com.jiang.practice.beans.factory;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-18
 */
public interface InitializingBean {

    /**
     * 在 Bean 属性填充后调用
     */
    void afterPropertiesSet() throws Exception;

}
