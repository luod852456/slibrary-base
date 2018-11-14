package com.chaolu.slibrary.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.chaolu.slibrary.event.SBaseEvent;
import com.chaolu.slibrary.manager.SEventManager;

import org.greenrobot.eventbus.Subscribe;

public class SBaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 注册EventBus
         */
        SEventManager.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * 注销EventBus
         */
        SEventManager.unregister(this);
    }

    /**
     * EventBus注册就必须要有回调函数
     *
     * @param event
     */
    @Subscribe
    public void onEvent(SBaseEvent event) {

    }

    @Override
    public void onClick(View v) {

    }
}
