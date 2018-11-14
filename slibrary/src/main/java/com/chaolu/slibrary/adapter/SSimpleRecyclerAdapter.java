package com.chaolu.slibrary.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.chaolu.slibrary.adapter.viewholder.SRecyclerViewHolder;

/**
 * RecyclerView的适配器
 *
 * @author luodong 2018.11.5
 */
public abstract class SSimpleRecyclerAdapter<T> extends SRecyclerAdapter<T> {
    public SSimpleRecyclerAdapter(Activity activity) {
        super(activity);
    }

    @Override
    public SRecyclerViewHolder onCreateVHolder(ViewGroup parent, int viewType) {
        final int layoutId = getLayoutId(parent, viewType);
        final View itemView = LayoutInflater.from(getActivity()).inflate(layoutId, parent, false);
        SRecyclerViewHolder holder = new SRecyclerViewHolder(itemView);
        return holder;
    }

    /**
     * getLayoutId
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(ViewGroup parent, int viewType);
}
