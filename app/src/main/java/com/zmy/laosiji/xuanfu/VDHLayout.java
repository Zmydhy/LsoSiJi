package com.zmy.laosiji.xuanfu;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

public class VDHLayout extends RelativeLayout {
    private View autoBackView;
    private ViewDragHelper mDragger;
    private Point mAutoBackOriginPos=new Point();
    private int mWidth;
    private int mHeight;

    public VDHLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addBackView(View view){
        this.autoBackView=view;
        if(autoBackView!=null){
            addView(autoBackView);
            inintViewDragHelper();
        }
    }

    private void inintViewDragHelper() {
        mDragger=ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child==autoBackView;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int leftBound=getPaddingLeft();
                int rightBound=getWidth()-child.getWidth()-leftBound;
                int newLeft=Math.min(Math.max(left,leftBound),rightBound);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int topBound=getPaddingTop();
                int bottomBound=getHeight()-child.getHeight()-topBound;
                int newTop=Math.min(Math.max(top,topBound),bottomBound);
                return newTop;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                if(changedView==autoBackView){
                    mAutoBackOriginPos.y=top;
                    if(left<mWidth/2){
                        mAutoBackOriginPos.x=0;
                    }else{
                        mAutoBackOriginPos.x=mWidth-autoBackView.getWidth();
                    }
                }
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return getMeasuredWidth()-child.getMeasuredWidth();
            }

            @Override
            public int getViewVerticalDragRange(View child) {
                return getMeasuredHeight()-child.getMeasuredHeight();
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                if(releasedChild==autoBackView){
                    mDragger.settleCapturedViewAt(mAutoBackOriginPos.x,mAutoBackOriginPos.y);
                    invalidate();
                }
            }

        });
    }

    @Override
    public void computeScroll() {
        //  super.computeScroll();
        if(mDragger!=null){
            if(mDragger.continueSettling(true)){
                invalidate();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mDragger!=null){
            mDragger.processTouchEvent(event);
        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mDragger!=null?mDragger.shouldInterceptTouchEvent(ev):super.onInterceptTouchEvent(ev);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mWidth=getWidth();
        mHeight=getHeight();
    }

}
