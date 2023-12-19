package com.jiang.practice.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-19
 */
@Getter
@Setter
public class IUserService {

    private String uid;
    private String company;
    private String location;
    private IUserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uid) + "," + company + "," + location;
    }

}
