package com.jiang.practice.bean;

import com.jiang.practice.beans.factory.DisposableBean;
import com.jiang.practice.beans.factory.InitializingBean;

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
public class UserService implements InitializingBean, DisposableBean {

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
}
