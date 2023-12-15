package com.jiang.practice.bean;

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
public class UserService {

    private String uid;

    private String company;

    private String location;

    private UserDao userDao;

    public void queryUserInfo() {
        log.debug("查询用户信息: uid={}, company:{}, location:{}", userDao.queryUserName(uid), company, location);
    }
}
