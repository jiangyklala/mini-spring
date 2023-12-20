package com.jiang.practice.context;

import com.jiang.practice.beans.factory.HierarchicalBeanFactory;
import com.jiang.practice.beans.factory.ListableBeanFactory;
import com.jiang.practice.core.io.ResourceLoader;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-14
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {

}

