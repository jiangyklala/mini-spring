package com.jiang.practice.advice;

import java.lang.reflect.Method;

import com.jiang.practice.aop.MethodBeforeAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-25
 */
@Slf4j
public class IUserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        log.debug("拦截方法：{}", method.getName());
    }
}
