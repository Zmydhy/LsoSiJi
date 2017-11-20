package com.zmy.laosiji.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Michael on 2017/11/1.
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 */

public class HoruzontalView extends ViewGroup {
    private  int  lastX;
    private  int  lastY;
    private int currentIndex = 0;//当前子元素
    private int childWidth =  0;
    private Scroller scroller;
    private VelocityTracker tracker;
    private int lastInterceptX = 0;
    private int lastInterceptY = 0;

    public HoruzontalView(Context context) {
        this(context,null);
    }

    public HoruzontalView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HoruzontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode  = MeasureSpec. getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(getChildCount() == 0){
            setMeasuredDimension(0,0);
        }else if(widthMode  == MeasureSpec.AT_MOST && heightMode ==MeasureSpec.AT_MOST){
            View childone = getChildAt(0);
            int chidwidths = childone.getMeasuredWidth();
            int chidheight = childone.getMeasuredHeight();
            setMeasuredDimension(chidwidths*getChildCount(),chidheight);
        }else if(widthMode == MeasureSpec.AT_MOST){
            View childone = getChildAt(0);
            int chidwidths = childone.getMeasuredWidth();
            setMeasuredDimension(chidwidths*getChildCount(),heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            View childone = getChildAt(0);
            int chidheight = childone.getMeasuredHeight();
            setMeasuredDimension(widthSize,chidheight);
        }
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int childcount = getChildCount();
        int left = 0;
        View child;
        for(int s =  0; s < childcount; s++){
            child = getChildAt(s);
            if(child.getVisibility()  != View.GONE){
                int width = child.getMeasuredWidth();
                childWidth  = width;
                child.layout(left,0,left+width,child.getMeasuredHeight());
                left += width;
            }
        }
    }
}
