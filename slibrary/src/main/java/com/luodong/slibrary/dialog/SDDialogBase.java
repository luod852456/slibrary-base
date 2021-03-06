package com.luodong.slibrary.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.luodong.slibrary.R;
import com.luodong.slibrary.manager.SDHandlerManager;
import com.luodong.slibrary.utils.SDViewUtil;

/**
 * 被{@link SBaseDialog 替代}
 */
@Deprecated
public class SDDialogBase extends Dialog implements View.OnClickListener, OnDismissListener
{

    public static final int DEFAULT_PADDING_LEFT_RIGHT = SDViewUtil.dp2px(20);
    public static final int DEFAULT_PADDING_TOP_BOTTOM = SDViewUtil.dp2px(10);

    private View contentView;
    protected LinearLayout linearLayoutRoot;
    protected boolean dismissAfterClick = true;

    public SDDialogBase(Activity activity)
    {
        this(activity, R.style.dialogBaseBlur);
    }

    public SDDialogBase(Activity activity, int theme)
    {
        super(activity, theme);
        setOwnerActivity(activity);
        baseInit();
    }

    private void initDrawable()
    {
//        drawableManager = new SDDrawableManager();
    }

    private void baseInit()
    {
        linearLayoutRoot = new LinearLayout(getContext());
        linearLayoutRoot.setBackgroundColor(Color.parseColor("#00000000"));
        linearLayoutRoot.setGravity(Gravity.CENTER);
        this.setOnDismissListener(this);
        initDrawable();
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v)
    {

    }

    // ------------------getter setter-----------------

    /**
     * 是否点击按钮后自动关闭窗口
     *
     * @return
     */
    public boolean isDismissAfterClick()
    {
        return dismissAfterClick;
    }

    /**
     * 设置是否点击按钮后自动关闭窗口,默认true(是)
     *
     * @param dismissAfterClick
     * @return
     */
    public SDDialogBase setDismissAfterClick(boolean dismissAfterClick)
    {
        this.dismissAfterClick = dismissAfterClick;
        return this;
    }

    // ---------------------show gravity

    /**
     * 设置窗口显示的位置
     *
     * @param gravity
     * @return
     */
    public SDDialogBase setGrativity(int gravity)
    {
        getWindow().setGravity(gravity);
        return this;
    }

    public void showTop()
    {
        showTop(true);
    }

    /**
     * 显示在顶部
     *
     * @param anim 是否需要动画
     */
    public void showTop(boolean anim)
    {
        setGrativity(Gravity.TOP);
        if (anim)
        {
            setAnimations(R.style.anim_top_top);
        }
        show();
    }

    public void showBottom()
    {
        showBottom(true);
    }

    /**
     * 显示在底部
     *
     * @param anim 是否需要动画
     */
    public void showBottom(boolean anim)
    {
        setGrativity(Gravity.BOTTOM);
        if (anim)
        {
            setAnimations(R.style.anim_bottom_bottom);
        }
        show();
    }

    public void showCenter()
    {
        setGrativity(Gravity.CENTER);
        show();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        stopDismissRunnable();
    }

    public void destroy()
    {
        if (isShowing())
        {
            dismiss();
        } else
        {
            onStop();
        }
        setOwnerActivity(null);
    }

    @Override
    public void show()
    {
        if (getOwnerActivity() != null && !getOwnerActivity().isFinishing())
        {
            super.show();
        }
    }

    /**
     * 设置窗口的显示和隐藏动画
     *
     * @param resId
     */
    public void setAnimations(int resId)
    {
        getWindow().setWindowAnimations(resId);
    }

    // -----------------------padding

    public SDDialogBase paddingTop(int top)
    {
        linearLayoutRoot.setPadding(linearLayoutRoot.getPaddingLeft(), top, linearLayoutRoot.getPaddingRight(), linearLayoutRoot.getPaddingBottom());
        return this;
    }

    public SDDialogBase paddingBottom(int bottom)
    {
        linearLayoutRoot.setPadding(linearLayoutRoot.getPaddingLeft(), linearLayoutRoot.getPaddingTop(), linearLayoutRoot.getPaddingRight(), bottom);
        return this;
    }

    public SDDialogBase paddingLeft(int left)
    {
        linearLayoutRoot.setPadding(left, linearLayoutRoot.getPaddingTop(), linearLayoutRoot.getPaddingRight(), linearLayoutRoot.getPaddingBottom());
        return this;
    }

    public SDDialogBase paddingRight(int right)
    {
        linearLayoutRoot.setPadding(linearLayoutRoot.getPaddingLeft(), linearLayoutRoot.getPaddingTop(), right, linearLayoutRoot.getPaddingBottom());
        return this;
    }

    public SDDialogBase paddings(int paddings)
    {
        linearLayoutRoot.setPadding(paddings, paddings, paddings, paddings);
        return this;
    }

    /**
     * 设置窗口上下左右的边距
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @return
     */
    public SDDialogBase padding(int left, int top, int right, int bottom)
    {
        linearLayoutRoot.setPadding(left, top, right, bottom);
        return this;
    }

    private SDDialogBase setDialogView(View view, LayoutParams params)
    {
        contentView = view;
        wrapperView(contentView);
        if (params == null)
        {
            params = new LayoutParams(SDViewUtil.getScreenWidth(), LayoutParams.WRAP_CONTENT);
        }
        padding(DEFAULT_PADDING_LEFT_RIGHT, DEFAULT_PADDING_TOP_BOTTOM, DEFAULT_PADDING_LEFT_RIGHT, DEFAULT_PADDING_TOP_BOTTOM);
        super.setContentView(linearLayoutRoot, params);
        return this;
    }

    private void wrapperView(View view)
    {
        linearLayoutRoot.removeAllViews();
        linearLayoutRoot.addView(view, SDViewUtil.getLayoutParamsLinearLayoutMM());
    }

    protected void dismissAfterClick()
    {
        if (dismissAfterClick)
        {
            dismiss();
        }
    }

    /**
     * 设置高度
     *
     * @param width
     * @return
     */
    public SDDialogBase setWidth(int width)
    {
        SDViewUtil.setViewWidth(linearLayoutRoot, width);
        return this;
    }

    /**
     * 设置宽度
     *
     * @param height
     * @return
     */
    public SDDialogBase setHeight(int height)
    {
        SDViewUtil.setViewHeight(linearLayoutRoot, height);
        return this;
    }

    /**
     * 设置全屏
     *
     * @return
     */
    public SDDialogBase setFullScreen()
    {
        paddings(0);
        setWidth(SDViewUtil.getScreenWidth()).setHeight(SDViewUtil.getScreenHeight() - SDViewUtil.getStatusBarHeight());
        return this;
    }

    // ------------------------setContentView

    @Override
    public void setContentView(int layoutResID)
    {
        View view = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        this.setContentView(view, null);
    }

    public void setContentView(int layoutResID, LayoutParams params)
    {
        View view = LayoutInflater.from(getContext()).inflate(layoutResID, null);
        this.setContentView(view, params);
    }

    @Override
    public void setContentView(View view)
    {
        this.setContentView(view, null);
    }

    @Override
    public void setContentView(View view, LayoutParams params)
    {
        setDialogView(view, params);
    }

    public View getContentView()
    {
        return contentView;
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {

    }

    public void startDismissRunnable(long delay)
    {
        stopDismissRunnable();
        SDHandlerManager.getMainHandler().postDelayed(dismissRunnable, delay);
    }

    public void stopDismissRunnable()
    {
        SDHandlerManager.getMainHandler().removeCallbacks(dismissRunnable);
    }

    private Runnable dismissRunnable = new Runnable()
    {
        @Override
        public void run()
        {
            dismiss();
        }
    };
}
