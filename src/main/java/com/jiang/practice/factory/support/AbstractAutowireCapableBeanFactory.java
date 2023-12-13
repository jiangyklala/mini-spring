package com.jiang.practice.factory.support;

import java.lang.reflect.Constructor;

import com.jiang.practice.BeansException;
import com.jiang.practice.factory.config.BeanDefinition;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 管理创建 Bean 的能力
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
@Getter
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * 只专注实现 AbstractBeanFactory 的 createBean 方法, getBeanDefinition 方法交由接下来的子类 DefaultListableBeanFactory 去实现, 各司其职
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        log.debug("bean named: {}, declaredConstructors: {}", beanName, declaredConstructors);

        // 从 bean 中获取到的所有构造器中一一比对, 找到符合入参的构造器
        for (Constructor ctor : declaredConstructors) {
            // TODO 目前只是参数数量的对比, 实际上还需对比每个参数的类型
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

}

