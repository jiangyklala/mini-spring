package com.jiang.practice;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;

import com.jiang.practice.aop.AdvisedSupport;
import com.jiang.practice.aop.MethodMatcher;
import com.jiang.practice.aop.TargetSource;
import com.jiang.practice.aop.aspectj.AspectJExpressionPointcut;
import com.jiang.practice.aop.framework.Cglib2AopProxy;
import com.jiang.practice.aop.framework.JdkDynamicAopProxy;
import com.jiang.practice.aop.framework.ReflectiveMethodInvocation;
import com.jiang.practice.bean.IUserService2;
import com.jiang.practice.bean.UserService;
import com.jiang.practice.bean.UserServiceInterface;
import com.jiang.practice.context.support.ClassPathXmlApplicationContext;
import com.jiang.practice.interceptor.IUserServiceInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-25
 */
@Slf4j
public class AOPTest {

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-5.xml");
        UserServiceInterface userService = applicationContext.getBean("userService", UserServiceInterface.class);
        log.debug("测试结果：{}", userService.queryUserInfo());
    }


    /**
     * 校验 AspectJExpressionPointcut 是否可以拦截所指定的方法
     */
    @Test
    public void testAspectJMethod() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.jiang.practice.bean.UserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");

        log.debug("pointcut.matches(clazz): {}", pointcut.matches(clazz));
        log.debug("pointcut.matches(method, clazz): {}", pointcut.matches(method, clazz));
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        UserServiceInterface userService = new IUserService2();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new IUserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.jiang.practice.bean.UserServiceInterface.*(..))"));

        // 代理对象(JdkDynamicAopProxy) 测试调用
        UserServiceInterface proxy_jdk = (UserServiceInterface) new JdkDynamicAopProxy(advisedSupport).getProxy();
        log.debug("测试结果: {}", proxy_jdk.queryUserInfo());

        // 代理对象(Cglib2AopProxy) 测试调用
        UserServiceInterface proxy_cglib = (UserServiceInterface) new Cglib2AopProxy(advisedSupport).getProxy();
        log.debug("测试结果: {}", proxy_cglib.register("jiang"));
    }



    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new IUserService2();
        // AOP 代理
        UserServiceInterface proxy = (UserServiceInterface) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.jiang.practice.bean.UserServiceInterface.*(..))");
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });
        String result = proxy.queryUserInfo();
        log.debug("测试结果: {}", result);
    }

}
