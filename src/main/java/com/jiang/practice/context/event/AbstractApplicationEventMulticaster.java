package com.jiang.practice.context.event;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.BeanFactory;
import com.jiang.practice.beans.factory.BeanFactoryAware;
import com.jiang.practice.context.ApplicationEvent;
import com.jiang.practice.context.ApplicationListener;
import com.jiang.practice.utils.ClassUtils;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-20
 */
public abstract class AbstractApplicationEventMulticaster implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners = new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 获取符合广播事件中的监听处理器
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) allListeners.add(listener);
        }
        return allListeners;
    }

    /**
     * 判断该监听器是否对该事件感兴趣<br/>
     * 具体的实现思路是: 用传进来的 listener, 拿到这个 listener 直接实现的 ApplicationListener<?> 中的泛型类型 (也就是这个 ?),
     * 这个泛型类型也是个 xxxEvent, 然后通过 isAssignableFrom 来判断其是否是 平级 or 继承关系; 是, 就代表这个 listener 所对应的就是
     * 这个 event
     * @param applicationListener 监听器
     * @param event 事件
     */
    protected boolean supportsEvent(ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();

        // 按照 CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy 不同的实例化类型，需要判断后获取目标 class
        Class<?> targetClass = ClassUtils.isCglibProxyClass(listenerClass) ? listenerClass.getSuperclass() : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];  // 获取这个 Class 直接实现的 Interface Class

        Type actualTypeArgument = ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new BeansException("wrong event class name: " + className);
        }

        /*
         * 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表示的类或接口是否相同，或是否是其超类或超接口。
         * <p/>
         * 具体的: isAssignableFrom 是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的。
         * 例如: 如果 A.isAssignableFrom(B) 结果是 true，证明 B 可以转换成为 A, 也就是 A 可以由 B 转换而来。
         */
        return eventClassName.isAssignableFrom(event.getClass());
    }

}

