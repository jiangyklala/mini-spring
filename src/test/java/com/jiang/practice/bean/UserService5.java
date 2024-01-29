package com.jiang.practice.bean;

import java.util.Random;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2024-01-23
 */
public class UserService5 implements UserServiceInterface {

    private String token;

    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Jiang, 10001, Beijing" + token;
    }

    @Override
    public String register(String userName) {
        return userName + " registered";
    }
}
