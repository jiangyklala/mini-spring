package com.jiang.practice.aop.framework.autoproxy;

import java.util.Collection;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import com.jiang.practice.aop.AdvisedSupport;
import com.jiang.practice.aop.Advisor;
import com.jiang.practice.aop.ClassFilter;
import com.jiang.practice.aop.Pointcut;
import com.jiang.practice.aop.TargetSource;
import com.jiang.practice.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.jiang.practice.aop.framework.ProxyFactory;
import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.PropertyValues;
import com.jiang.practice.beans.factory.BeanFactory;
import com.jiang.practice.beans.factory.BeanFactoryAware;
import com.jiang.practice.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.jiang.practice.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-25
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {

        if (isInfrastructureClass(beanClass)) return null;

        // 获取所有的 AspectJExpressionPointcutAdvisor
        Collection<AspectJExpressionPointcutAdvisor> advisors = beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();

        // 对于每个 AspectJExpressionPointcutAdvisor, 遍历填充其属性
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(beanClass)) continue;

            AdvisedSupport advisedSupport = new AdvisedSupport();

            TargetSource targetSource = null;
            try {
                targetSource = new TargetSource(beanClass.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);

            // 利用代理工厂生成代理类
            return new ProxyFactory(advisedSupport).getProxy();

        }

        return null;
    }

    private boolean isInfrastructureClass(Class<?> beanClass) {
        return Advice.class.isAssignableFrom(beanClass) || Pointcut.class.isAssignableFrom(beanClass) || Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

}