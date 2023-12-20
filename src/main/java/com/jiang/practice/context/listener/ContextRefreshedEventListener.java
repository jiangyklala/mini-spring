package com.jiang.practice.context.listener;

import com.jiang.practice.context.ApplicationListener;
import com.jiang.practice.context.event.ContextRefreshedEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-20
 */
@Slf4j
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("ContextRefreshedEventListener#onApplicationEvent: context refreshed!");
    }
}
