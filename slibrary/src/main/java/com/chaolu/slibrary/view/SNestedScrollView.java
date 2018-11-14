package com.chaolu.slibrary.view;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

public class SNestedScrollView extends NestedScrollView {
    private OnScrollChangeListener onScrollChangeListener;

    public SNestedScrollView(Context context) {
        super(context);
    }

    public SNestedScrollView(Context context, AttributeSet attrs,
                             int defStyle) {
        super(context, attrs, defStyle);
    }

    public SNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 解决获取onScrollChanged 需要api23以上的问题
     */
    public void setScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public interface OnScrollChangeListener{
        void onScrollChanged(View v,int x, int y, int oldx, int oldy);
    }

    @Override
    public void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}