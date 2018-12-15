package com.luodong.slibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.luodong.slibrary.R;
import com.luodong.slibrary.event.SBaseEvent;
import com.luodong.slibrary.manager.SEventManager;

import org.greenrobot.eventbus.Subscribe;

public class SBaseDialog extends Dialog implements View.OnClickListener, DialogInterface.OnDismissListener {
    private View mContentView;
    private boolean mDismissAfterClick = true;

    private Handler mHandler;

    public SBaseDialog(Activity activity) {
        this(activity, R.style.dialogBaseBlur);
    }

    public SBaseDialog(Activity activity, int theme) {
        super(activity, theme);

        SEventManager.register(this);
        setOwnerActivity(activity);
        setOnDismissListener(this);
        setCanceledOnTouchOutside(false);
    }

    /**
     * EventBus注册就必须要有回调函数
     *
     * @param event
     */
    @Subscribe
    public void onEvent(SBaseEvent event) {

    }

    //---------- FIDialog implements start----------

    public View getContentView() {
        return mContentView;
    }

    @Override
    public void setContentView(int layoutId) {
        FrameLayout tempLayout = new FrameLayout(getContext());
        View view = LayoutInflater.from(getContext()).inflate(layoutId, tempLayout, false);
        tempLayout.removeView(view);

        setDialogView(view, view.getLayoutParams());

        /**
         * 注册EventBus
         */
        SEventManager.register(this);
    }

    @Override
    public void setContentView(View view) {
        setDialogView(view, view.getLayoutParams());
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        setDialogView(view, params);
    }

    public SBaseDialog setWidth(int width) {
        ViewGroup.LayoutParams params = mContentView.getLayoutParams();
        params.width = width;
        mContentView.setLayoutParams(params);

        synchronizeWidth();
        return this;
    }

    /**
     * 设置宽度
     *
     * @param height
     * @return
     */
    public SBaseDialog setHeight(int height) {
        ViewGroup.LayoutParams params = mContentView.getLayoutParams();
        params.height = height;
        mContentView.setLayoutParams(params);

        synchronizeHeight();
        return this;
    }

    public SBaseDialog setFullScreen() {
        paddings(0);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        return this;
    }

    public int getDefaultPadding() {
        int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        int value = (int) (screenWidth * 0.1f);
        return value;
    }

    public SBaseDialog paddingLeft(int left) {
        View view = getWindow().getDecorView();
        view.setPadding(left, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
        return this;
    }

    public SBaseDialog paddingTop(int top) {
        View view = getWindow().getDecorView();
        view.setPadding(view.getPaddingLeft(), top, view.getPaddingRight(), view.getPaddingBottom());
        return this;
    }

    public SBaseDialog paddingRight(int right) {
        View view = getWindow().getDecorView();
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), right, view.getPaddingBottom());
        return this;
    }

    public SBaseDialog paddingBottom(int bottom) {
        View view = getWindow().getDecorView();
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), bottom);
        return this;
    }

    public SBaseDialog paddings(int paddings) {
        View view = getWindow().getDecorView();
        view.setPadding(paddings, paddings, paddings, paddings);
        return this;
    }

    public boolean isDismissAfterClick() {
        return mDismissAfterClick;
    }

    public SBaseDialog setDismissAfterClick(boolean dismissAfterClick) {
        mDismissAfterClick = dismissAfterClick;
        return this;
    }

    public SBaseDialog setGrativity(int gravity) {
        getWindow().setGravity(gravity);
        return this;
    }

    public SBaseDialog setAnimations(int resId) {
        getWindow().setWindowAnimations(resId);
        return this;
    }

    public void showAlpha() {
        setGrativity(Gravity.CENTER);
        setAnimations(R.style.anim_alpha_alpha);
        show();
    }

    public void showTop() {
        setGrativity(Gravity.TOP);
        setAnimations(R.style.anim_top_top);
        show();
    }

    public void showCenter() {
        setGrativity(Gravity.CENTER);
        show();
    }

    public void showBottom() {
        setGrativity(Gravity.BOTTOM);
        setAnimations(R.style.anim_bottom_bottom);
        show();
    }

    private Handler getHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    public SBaseDialog startDismissRunnable(long delay) {
        stopDismissRunnable();
        getHandler().postDelayed(mDismissRunnable, delay);
        return this;
    }

    public SBaseDialog stopDismissRunnable() {
        getHandler().removeCallbacks(mDismissRunnable);
        return this;
    }

    //---------- FIDialog implements end----------

    private SBaseDialog setDialogView(View view, ViewGroup.LayoutParams params) {
        mContentView = view;
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        super.setContentView(mContentView, params);

        paddings(getDefaultPadding());
        synchronizeWidth();
        return this;
    }

    /**
     * 把contentView的宽度同步到window
     */
    private void synchronizeWidth() {
        if (mContentView == null) {
            return;
        }
        ViewGroup.LayoutParams p = mContentView.getLayoutParams();
        if (p == null) {
            return;
        }

        WindowManager.LayoutParams wParams = getWindow().getAttributes();
        if (wParams.width != p.width) {
            wParams.width = p.width;
            getWindow().setAttributes(wParams);
        }
    }

    /**
     * 把contentView的高度同步到window
     */
    private void synchronizeHeight() {
        if (mContentView == null) {
            return;
        }
        ViewGroup.LayoutParams p = mContentView.getLayoutParams();
        if (p == null) {
            return;
        }

        WindowManager.LayoutParams wParams = getWindow().getAttributes();
        if (wParams.height != p.height) {
            wParams.height = p.height;
            getWindow().setAttributes(wParams);
        }
    }

    protected void dismissAfterClickIfNeed() {
        if (isDismissAfterClick()) {
            dismiss();
        }
    }

    private Runnable mDismissRunnable = new Runnable() {
        @Override
        public void run() {
            dismiss();
        }
    };

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopDismissRunnable();
        SEventManager.unregister(this);
    }

    @Override
    public void show() {
        try {
            if (getOwnerActivity() != null && !getOwnerActivity().isFinishing()) {
                super.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {

    }

    @Override
    public void dismiss() {
        stopDismissRunnable();
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void setBackgroundDrawable(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingTop = view.getPaddingTop();
        int paddingRight = view.getPaddingRight();
        int paddingBottom = view.getPaddingBottom();
        view.setBackgroundDrawable(drawable);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
}
