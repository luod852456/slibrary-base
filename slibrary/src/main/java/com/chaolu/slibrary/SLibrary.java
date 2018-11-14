package com.chaolu.slibrary;

import android.app.Application;
import android.content.Context;

import com.chaolu.slibrary.manager.SActivityManager;

public class SLibrary
{
    private static SLibrary sInstance;
    private Context mContext;

    private SLibrary()
    {
    }

    public static SLibrary getInstance()
    {
        if (sInstance == null)
        {
            synchronized (SLibrary.class)
            {
                if (sInstance == null)
                    sInstance = new SLibrary();
            }
        }
        return sInstance;
    }

    public Context getContext()
    {
        return mContext;
    }

    public synchronized void init(Application application)
    {
        if (mContext == null)
        {
            mContext = application;
            //Activity管理器
            SActivityManager.getInstance().init(application);
        }
    }
}
