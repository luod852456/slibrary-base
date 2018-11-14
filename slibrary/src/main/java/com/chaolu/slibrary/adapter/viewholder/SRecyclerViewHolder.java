package com.chaolu.slibrary.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 *  RecyclerViewHolder
 *
 *  @author luodong 2018.11.5
 */
public class SRecyclerViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mArrayView;

    public SRecyclerViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 在itemView中通过id查找view，如果需要频繁的通过id查找view，调用此方法查找效率较高
     *
     * @param id
     * @param <V>
     * @return
     */
    public final <V extends View> V get(int id) {
        if (mArrayView == null) {
            mArrayView = new SparseArray<>();
        }
        View view = mArrayView.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            if (view != null) {
                mArrayView.put(id, view);
            }
        }
        return (V) view;
    }
}
