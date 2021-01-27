package com.jicanghai.viewpager2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyTouchView extends LinearLayout {

    public static final String TAG = "MyTouchView";
    private boolean mIntercept = false;//是否拦截滑动事件
    private boolean mIsCurrentItem = false;//是否是当前item
    private float mScrallX, mDownX = 0;
    private float mFingerSpace = 50;//手指点击误差值
    private Context mContext;

    public MyTouchView(Context context) {
        super(context);
        this.mContext = context;
    }

    public MyTouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public MyTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
    }

    public MyTouchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (SpUtils.getContralState(mContext)) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownX = ev.getX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mScrallX = ev.getX() - mDownX;
                    //mScrallX > 0 向左，mScrallX < 0 向右
                    if (mIsCurrentItem && mScrallX <= 0 + mFingerSpace) {
                        mIntercept = true;
                        return true;
                    } else {
                        mIntercept = false;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    mIntercept = false;
                    break;
                default:
                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (SpUtils.getContralState(mContext)) {
            if (this.mIntercept) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (SpUtils.getContralState(mContext)) {
            if (this.mIntercept) {
                return true;
            }
        }
        return super.onTouchEvent(event);
    }

    public void setCurrentItem(boolean isCurrentItem) {
        this.mIsCurrentItem = isCurrentItem;
    }
}
