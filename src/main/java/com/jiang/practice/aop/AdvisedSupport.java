package com.jiang.practice.aop;

import org.aopalliance.intercept.MethodInterceptor;

import lombok.Getter;
import lombok.Setter;


/**
 * 用于把代理、拦截、匹配的各项属性包装到一个类中，方便在 Proxy 实现类进行使用
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-25
 */
@Getter
@Setter
public class AdvisedSupport {

    // ProxyConfig
    private boolean proxyTargetClass = false;

    // 被代理的目标对象, 用于在目标对象类中提供 Object 入参属性，以及获取目标类 TargetClass 信息
    private TargetSource targetSource;

    // 方法拦截器, 由用户自己实现 MethodInterceptor#invoke 方法，做具体的处理
    private MethodInterceptor methodInterceptor;

    // 方法匹配器(检查目标方法是否符合通知条件), 这个对象由 AspectJExpressionPointcut 提供服务
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }
}
