package com.luodong.slibrary.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * SSimpleAdapter
 *
 * @author luodong 2018.10.30
 */
public abstract class SSimpleAdapter<T> extends SBaseAdapter<T>
{
    public SSimpleAdapter(Activity activity)
    {
        super(activity);
    }

    @Override
    protected View onGetView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            final int layoutId = getLayoutId(position, convertView, parent);
            if (layoutId != 0)
            {
                convertView = LayoutInflater.from(getActivity()).inflate(layoutId, parent, false);
                onInit(position, convertView, parent);
            }
        }
        onBindData(position, convertView, parent, getItem(position));
        return convertView;
    }

    public abstract int getLayoutId(int position, View convertView, ViewGroup parent);

    public void onInit(int position, View convertView, ViewGroup parent)
    {
    }

    public abstract void onBindData(int position, View convertView, ViewGroup parent, T model);
}
