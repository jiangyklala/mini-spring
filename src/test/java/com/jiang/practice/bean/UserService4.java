package com.jiang.practice.bean;

import java.util.Random;

import com.jiang.practice.beans.factory.annotation.Autowired;
import com.jiang.practice.beans.factory.annotation.Value;
import com.jiang.practice.stereotype.Component;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2024-01-23
 */
@Component("userService4")
public class UserService4 implements UserServiceInterface {

    @Value("${token}")
    private String token;

    @Autowired
    private UserDao userDao;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    @Override
    public String register(String userName) {
        return null;
    }
}
