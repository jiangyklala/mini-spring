package com.jiang.practice.bean;

import com.jiang.practice.beans.BeansException;
import com.jiang.practice.beans.factory.BeanClassLoaderAware;
import com.jiang.practice.beans.factory.BeanFactory;
import com.jiang.practice.beans.factory.BeanFactoryAware;
import com.jiang.practice.beans.factory.BeanNameAware;
import com.jiang.practice.beans.factory.DisposableBean;
import com.jiang.practice.beans.factory.InitializingBean;
import com.jiang.practice.context.ApplicationContext;
import com.jiang.practice.context.ApplicationContextAware;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, ApplicationContextAware, BeanFactoryAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;


    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

    public void queryUserInfo() {
        log.debug("查询用户信息: uid={}, company:{}, location:{}", userDao.queryUserName(uid), company, location);
    }

    @Override
    public void destroy() throws Exception {
        log.debug("execute: UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug("execute: UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        log.debug("Bean's classLoader is: {}", classLoader);
    }

    @Override
    public void setBeanName(String name) {
        log.debug("Bean's name is: {}", name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
