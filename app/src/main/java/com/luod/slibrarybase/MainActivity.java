package com.luod.slibrarybase;

import android.os.Bundle;

import com.luodong.slibrary.activity.SBaseActivity;
import com.luodong.slibrary.utils.LogUtil;

public class MainActivity extends SBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.e("slibrarybase");
    }
}
