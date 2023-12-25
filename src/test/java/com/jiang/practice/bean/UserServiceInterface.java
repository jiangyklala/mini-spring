package com.jiang.practice.bean;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-25
 */
public interface UserServiceInterface {

    String queryUserInfo();

    String register(String userName);
}
