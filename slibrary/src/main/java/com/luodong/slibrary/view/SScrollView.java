package com.luodong.slibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

public class SScrollView extends ScrollView {

    public SScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init(context);
    }

//    /**
////     * 解决scrollview嵌套HorizontalListView的问题
////     */
//    private GestureDetector mGestureDetector;
//    private void init(Context context) {
//        mGestureDetector = new GestureDetector(context, new YScrollDetector());
//        setFadingEdgeLength(0);
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
//    }
//
//    // Return false if we're scrolling in the x direction
//    class YScrollDetector extends GestureDetector.SimpleOnGestureListener {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            if(Math.abs(distanceY) > Math.abs(distanceX)) {
//                return true;
//            }
//            return false;
//        }
//    }

    /**
     * 解决获取onScrollChanged 需要api23以上的问题
     */
    private OnScrollChangeListener onScrollChangeListener;
    public void setScrollChangeListener(OnScrollChangeListener onScrollChangeListener) {
        this.onScrollChangeListener = onScrollChangeListener;
    }

    public interface OnScrollChangeListener {
        void onScrollChanged(View v, int x, int y, int oldx, int oldy);
    }

    @Override
    public void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (onScrollChangeListener != null) {
            onScrollChangeListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
