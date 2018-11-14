package com.chaolu.slibrary.manager;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by luodong on 2018/10/22.
 */
public class SEventManager {

    private SEventManager() {

    }

    /**
     * 注册
     *
     * @param object
     */
    public static void register(final Object object) {
        if (!EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().register(object);
        }
    }

    /**
     * 注销
     *
     * @param object
     */
    public static void unregister(Object object) {
        if (EventBus.getDefault().isRegistered(object)) {
            EventBus.getDefault().unregister(object);
        }
    }

    /**
     * 发送事件
     *
     * @param object
     */
    public static void post(Object object) {
        EventBus.getDefault().post(object);
    }

}
