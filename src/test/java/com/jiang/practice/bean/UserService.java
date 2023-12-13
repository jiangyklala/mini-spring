package com.jiang.practice.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
public class UserService {

    private String name;

    public void queryUserInfo() {
        log.debug("查询用户信息");
    }

}
