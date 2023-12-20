package com.jiang.practice.context.event;

/**
 * 监听刷新事件
 * @author jiangyunkai <jiangyunkai@kuaishou.com>
 * Created on 2023-12-20
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) throws IllegalArgumentException {
        super(source);
    }

}
