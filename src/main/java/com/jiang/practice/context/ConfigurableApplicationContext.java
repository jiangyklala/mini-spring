package com.jiang.practice.context;

import com.jiang.practice.beans.BeansException;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public interface ConfigurableApplicationContext extends ApplicationContext {

    /**
     * 刷新容器
     */
    void refresh() throws BeansException;

    /**
     * 向虚拟机注册钩子，保证在虚拟机关闭之前，执行销毁操作
     */
    void registerShutdownHook();

    /**
     * 手动关闭方法
     */
    void close();

}

