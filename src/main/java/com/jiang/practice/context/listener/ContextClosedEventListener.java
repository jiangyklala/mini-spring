package com.jiang.practice.context.listener;

import com.jiang.practice.context.ApplicationListener;
import com.jiang.practice.context.event.ContextClosedEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-20
 */
@Slf4j
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        log.debug("ContextClosedEventListener#onApplicationEvent: context closed!");
    }
}
