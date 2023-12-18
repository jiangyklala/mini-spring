package com.jiang.practice.beans.factory;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-18
 */
public interface DisposableBean {

    void destroy() throws Exception;

}
