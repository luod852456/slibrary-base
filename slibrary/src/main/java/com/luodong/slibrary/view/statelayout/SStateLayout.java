package com.luodong.slibrary.view.statelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

public abstract class SStateLayout<T extends SBaseStateView, D extends SBaseStateView> extends FrameLayout {

    protected View mContentView;
    protected T mEmptyView;
    protected D mErrorView;

    public SStateLayout(Context context) {
        super(context);
        init(null);
    }

    public SStateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

    }

    /**
     * 显示内容
     */
    public void showContent() {
        showView(getContentView());
        hideView(mErrorView);
        hideView(mEmptyView);
    }

    /**
     * 显示错误
     */
    public void showError() {
        showView(getErrorView());
        showView(getContentView());
        hideView(mEmptyView);
        bringChildToFront(getErrorView());
    }

    /**
     * 显示无内容
     */
    public void showEmpty() {
        showView(getEmptyView());
        showView(getContentView());
        hideView(mErrorView);
        bringChildToFront(getEmptyView());
    }

    protected View getContentView() {
        return mContentView;
    }

    protected abstract T getEmptyView();

    protected abstract D getErrorView();

    public void setEmptyView(T emptyView) {
        mEmptyView = emptyView;
    }

    public void setErrorView(D errorView) {
        mErrorView = errorView;
    }

    protected void setContentView(View view) {
        mContentView = view;
    }

    protected void hideView(View view) {
        if (view != null) {
            view.setVisibility(View.GONE);
        }
    }

    protected void showView(View view) {
        if (view != null) {
            view.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 1) {
            throw new IllegalArgumentException("SStateLayout can only add one child");
        }
        setContentView(getChildAt(0));
        onAfterFinishInflate();
    }

    /**
     * 自定义状态View在这个方法中设置
     * getEmptyView().setText();
     * getEmptyView().setImageResource();
     */
    protected abstract void onAfterFinishInflate();

    /**
     * 更新view状态
     *
     * @param count 大于0，显示内容；小于等于0，显示空内容
     */
    public void updateStateForCount(int count) {
        if (count > 0) {
            showContent();
        } else {
            showEmpty();
        }
    }
}
