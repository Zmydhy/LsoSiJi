package com.zmy.laosiji.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Michael on 2017/8/24.
 * 实现view随着手指滑动
 */

public class MyView extends View {
    // 设置需要用到的变量
    public static final int RADIUS = 120;// 圆的半径 = 70
    private Point currentPoint;// 当前点坐标
    private Paint mPaint;// 绘图画笔
    private int lastx= 0;
    private int lasty = 0 ;
    float x;
    float y;
    public  MyView(Context context){
        this(context,null);
    }

    // 构造方法(初始化画笔)
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        init();
    }

    private void init() {
        currentPoint = new Point(RADIUS, RADIUS);
        // 创建一个点对象(坐标是(70,70))

        // 在该点画一个圆:圆心 = (70,70),半径 = 70
        x = currentPoint.x;
        y = currentPoint.y;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getMode(heightMeasureSpec);
        if(widthSMode == MeasureSpec.AT_MOST && heightSMode == MeasureSpec.AT_MOST){
            setMeasuredDimension((int)(2*RADIUS),(int)(2 * RADIUS));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {


            canvas.drawCircle(x, y, RADIUS, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                x = event.getX();
                y = event.getY();
                Log.i("TAG","x:"+x+",y:"+y);
                invalidate();
                break;
        }
        return true;
    }


}
