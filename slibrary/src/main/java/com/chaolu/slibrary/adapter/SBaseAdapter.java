package com.chaolu.slibrary.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.chaolu.slibrary.adapter.callback.OnItemClickListener;
import com.chaolu.slibrary.adapter.callback.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * SBaseAdapter
 *
 * @author luodong 2018.10.30
 */
public abstract class SBaseAdapter<T> extends BaseAdapter {

    protected Activity activity;
    protected OnItemClickListener<T> onItemClickListener;
    protected OnItemLongClickListener<T> onItemLongClickListener;
    protected List<T> list;

    public SBaseAdapter(Activity activity) {
        this.activity = activity;
    }

    protected Activity getActivity(){
        return activity;
    }

    public void setData(List<T> arr, int page) {
        if (list == null) {
            list = new ArrayList<>();
        }
        if (page == 1) {
            list.clear();
            list = arr;
        } else {
            if (arr != null) {
                list.addAll(arr);
            }
        }
        notifyDataSetChanged();
    }

    public void setData(List<T> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<T> getList() {
        return list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        return onGetView(position,convertView,parent);
    }

    protected abstract View onGetView(int position, View convertView, ViewGroup parent);

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
