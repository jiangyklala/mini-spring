package com.jiang.practice.beans.factory.support;

import java.lang.reflect.Method;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.DisposableBean;
import com.jiang.practice.beans.factory.config.BeanDefinition;

import cn.hutool.core.util.StrUtil;

/**
 * Bean 销毁 Adapter
 * <p>
 * Q: 为什么销毁时需要以适配器的方式实现呢?
 * A: 销毁动作是由 AbstractApplicationContext 在注册虚拟机钩子后看，虚拟机关闭前执行的操作动作。
 *    并且在销毁执行时不太希望还得关注都销毁哪些类型的方法，它在使用上更希望是有一个统一的接口进行销毁，所以这里就新增了适配类，做统一处理。
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-18
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;
    private final String beanName;
    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1. 接口实现方式
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2. XML 文件配置方式 (判断是为了避免二次执行销毁)
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }

    }

}
