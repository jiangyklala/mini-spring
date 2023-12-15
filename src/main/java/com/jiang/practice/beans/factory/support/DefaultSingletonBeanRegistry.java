package com.jiang.practice.beans.factory.support;

import java.util.HashMap;
import java.util.Map;

import com.jiang.practice.beans.factory.config.SingletonBeanRegistry;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
