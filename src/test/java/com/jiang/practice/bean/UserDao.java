package com.jiang.practice.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static {
        hashMap.put("10001", "111");
        hashMap.put("10002", "222");
        hashMap.put("10003", "333");
    }

    public String queryUserName(String uid) {
        return hashMap.get(uid);
    }

}
