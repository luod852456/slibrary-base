package com.luodong.slibrary.jsinterfaces;

import android.app.Activity;

public class BaseJsInterface {
    private Activity activity;

    public BaseJsInterface(Activity activity) {
        this.activity = activity;
        if (activity == null) {
            throw new NullPointerException("activity is null");
        }
    }

    public Activity getActivity() {
        return activity;
    }

    public String getName() {
        return "App";
    }
}