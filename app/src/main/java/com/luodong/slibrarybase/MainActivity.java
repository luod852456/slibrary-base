package com.luodong.slibrarybase;

import android.os.Bundle;

import com.chaolu.slibrary.activity.SBaseActivity;
import com.chaolu.slibrary.utils.LogUtil;

public class MainActivity extends SBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.e("slibrarybase");
    }
}
