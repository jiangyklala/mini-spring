package com.jiang.practice.factory.support;

import java.lang.reflect.Constructor;

import com.jiang.practice.BeansException;
import com.jiang.practice.beans.PropertyValue;
import com.jiang.practice.beans.PropertyValues;
import com.jiang.practice.factory.config.BeanDefinition;
import com.jiang.practice.factory.config.BeanReference;

import cn.hutool.core.bean.BeanUtil;
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
            bean = createBeanInstance(beanName, beanDefinition, args);  // 带参实例化, 此时是没有注入属性的
            applyPropertyValues(beanName, beanDefinition, bean);   // 手动注入对象属性
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }

        addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(String beanName, BeanDefinition beanDefinition, Object[] args) {
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
        return getInstantiationStrategy().instantiate(beanName, beanDefinition, constructorToUse, args);
    }

    /**
     * Bean 的对象属性填充
     */
    protected void applyPropertyValues(String beanName, BeanDefinition beanDefinition, Object bean) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // A 依赖 B, 则获取 B 的实例化 Bean
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());  // 递归调用注入 bean 实例  TODO: 可能会有循环依赖的问题
                }

                // 属性注入
                BeanUtil.setProperty(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values: " + beanName);
        }
    }

}

