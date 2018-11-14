package com.luodong.slibrary.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.luodong.slibrary.adapter.callback.OnItemClickListener;
import com.luodong.slibrary.adapter.callback.OnItemLongClickListener;
import com.luodong.slibrary.adapter.viewholder.SRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView适配器
 *
 * @param <T> 实体类型
 * @author luodong 2018.11.5
 */
public abstract class SRecyclerAdapter<T> extends RecyclerView.Adapter<SRecyclerViewHolder> {
    protected Activity activity;
    protected OnItemLongClickListener<T> onItemLongClickListener;
    protected OnItemClickListener<T> onItemClickListener;
    protected List<T> list;

    public SRecyclerAdapter(Activity activity) {
        this.activity = activity;
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

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public T getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getItemCount()
    {
        return list == null ? 0 : list.size();
    }

    @Override
    public final SRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SRecyclerViewHolder holder = onCreateVHolder(parent, viewType);
        return holder;
    }

    @Override
    public final void onBindViewHolder(SRecyclerViewHolder holder, int position, List<Object> payloads) {
        onBindViewData(holder,position);
    }

    @Override
    public final void onBindViewHolder(SRecyclerViewHolder holder, int position) {
        onBindViewData(holder,position);
    }

    private void onBindViewData(SRecyclerViewHolder holder, int position) {
        onBindData(holder, position, getItem(position));
    }

    /**
     * 创建ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract SRecyclerViewHolder onCreateVHolder(ViewGroup parent, int viewType);

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     * @param model
     */
    public abstract void onBindData(SRecyclerViewHolder holder, int position, T model);

    public Activity getActivity() {
        return activity;
    }

    /**
     * 设置item点击回调
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 设置item长按回调
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener<T> onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }
}
