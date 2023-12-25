package com.jiang.practice.aop;

import java.lang.reflect.Method;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-25
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);

}
