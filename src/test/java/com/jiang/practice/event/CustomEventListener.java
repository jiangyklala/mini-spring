package com.jiang.practice.event;

import java.util.Date;

import com.jiang.practice.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-20
 */
@Slf4j
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        log.debug("收到消息: {}, 时间: {}, 内容: {}", event.getSource(), new Date(), event.getId() + ":" + event.getMessage());
    }

}

