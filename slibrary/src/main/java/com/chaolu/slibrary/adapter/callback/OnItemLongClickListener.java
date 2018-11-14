package com.chaolu.slibrary.adapter.callback;

import android.view.View;

public interface OnItemLongClickListener<T> {
        void onClick(int position, View view, T model);
    }