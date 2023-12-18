package com.jiang.practice.context;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.Aware;

/**
 * 实现此接口，既能感知到所属的 ApplicationContext <br/>
 * Interface to be implemented by any object that wishes to be notified of the {@link ApplicationContext} that it runs in.
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-18
 */
public interface ApplicationContextAware extends Aware {

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}

