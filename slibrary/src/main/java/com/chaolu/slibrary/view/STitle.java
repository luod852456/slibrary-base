package com.chaolu.slibrary.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chaolu.slibrary.R;

public class STitle extends LinearLayout implements OnClickListener {
    public enum TitleMode {
        THEME,
        WHITE_FA
    }

    private Context context;
    private View view;
    private ImageView ivLeft;
    private TextView tvRight;
    private ImageView ivRight;
    private TextView tvTitle;
    private FrameLayout flTitleBg;
    private View vLine;
    private OnTitleListener onTitleListener;
    private TitleMode mode = TitleMode.THEME;

    public STitle(Context context) {
        this(context, null);
    }

    public STitle(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.view_title, null);
        this.addView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        initView(view);
        register();
        setTitleStyle(mode);
    }

    public void setTitleStyle(TitleMode mode) {
        switch (mode) {
            case THEME:
                setTheme();
                break;
            case WHITE_FA:
                setWhiteFa();
                break;
        }
    }

    private void setWhiteFa() {
        setBackgroundColor(ContextCompat.getColor(context, R.color.color_fafafa));
        showLine();
    }

    private void setTheme() {
        setBackgroundColor(ContextCompat.getColor(context, R.color.theme_backgroud));
        invisibleLine();
    }

    public void show() {
        setVisibility(VISIBLE);
    }

    public void hide() {
        setVisibility(GONE);
    }

    public void invisible() {
        setVisibility(INVISIBLE);
    }

    public void showLine() {
        vLine.setVisibility(VISIBLE);
    }

    public void invisibleLine() {
        vLine.setVisibility(INVISIBLE);
    }

    public void hideLine() {
        vLine.setVisibility(GONE);
    }

    public void setBackgroundColor(int color) {
        flTitleBg.setBackgroundColor(color);
    }

    public void setLeftImage(int resId) {
        getLeftImageView().setImageResource(resId);
    }

    public void setRightImage(int resId) {
        getRightImageView().setImageResource(resId);
    }

    public void setTitleText(String s) {
        if (!TextUtils.isEmpty(s)) {
            getTitleTextView().setText(s);
        }
    }

    public void setRightText(String s) {
        if (!TextUtils.isEmpty(s)) {
            getRightTextView().setText(s);
        }
    }

    public View getTitleBackground() {
        return flTitleBg;
    }

    public ImageView getLeftImageView() {
        return ivLeft;
    }

    public TextView getTitleTextView() {
        return tvTitle;
    }

    public TextView getRightTextView() {
        return tvRight;
    }

    public ImageView getRightImageView() {
        return ivRight;
    }

    public View getLine() {
        return vLine;
    }

    private void register() {
        ivLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        ivRight.setOnClickListener(this);
    }

    private void initView(View view) {
        flTitleBg = view.findViewById(R.id.fl_title_bg);
        ivLeft = view.findViewById(R.id.iv_left);
        tvRight = view.findViewById(R.id.tv_right);
        ivRight = view.findViewById(R.id.iv_right);
        tvTitle = view.findViewById(R.id.tv_title);
        vLine = view.findViewById(R.id.v_line);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.iv_left) {
            if (onTitleListener != null) {
                onTitleListener.onCLickLeft(v);
            }
        } else if (i == R.id.tv_right) {
            if (onTitleListener != null) {
                onTitleListener.onCLickRight(v);
            }
        } else if (i == R.id.iv_right) {
            if (onTitleListener != null) {
                onTitleListener.onCLickRight(v);
            }
        }
    }

    public interface OnTitleListener {
        public void onCLickLeft(View v);

        public void onCLickRight(View v);
    }

    public void setOnTitleListener(OnTitleListener onTitleListener) {
        this.onTitleListener = onTitleListener;
    }

}