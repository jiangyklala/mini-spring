package com.jiang.practice.bean;

import java.util.HashMap;
import java.util.Map;

import com.jiang.practice.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-13
 */
@Slf4j
@Component
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<>();

    static  {
        log.debug("execute: init-method");
        hashMap.put("10001", "111");
        hashMap.put("10002", "222");
        hashMap.put("10003", "333");
    }

    public void destroyDataMethod(){
        log.debug("execute：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uid) {
        return hashMap.get(uid);
    }

}
