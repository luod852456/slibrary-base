package com.luodong.slibrary.view.statelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class SBaseStateView extends FrameLayout {
    public SBaseStateView(Context context) {
        super(context);
    }

    public SBaseStateView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SBaseStateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SBaseStateView setContentView(int layoutId) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, this, false);
        return setContentView(view);
    }

    public SBaseStateView setContentView(View contentView) {
        removeAllViews();
        if (contentView != null) {
            addView(contentView);
        }
        return this;
    }
}
