package com.zmy.laosiji.CustomView;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zmy.laosiji.R;
import com.zmy.laosiji.Utils.MiscUtil;

/**
 * Created by Michael on 2017/10/24.
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

public class CircleVIew extends View {
    private static final int  RADIUS = 150;
    private int  bgWidh =20 ;
    private Paint  mBgPaint ;
    private Paint  huanPaint ;
    private Paint  caiPaint;
    private Paint baiPaint;
    private Paint linePaint;
    private RectF huanRectf ;
    private int sweepAngles = 0;
    private int xRadius = 150;
    private Path linePath ;
    private int  mStartAngle = 0;

    public CircleVIew(Context context) {
        this(context,null);
    }

    public CircleVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void initPaint() {
        mBgPaint = new Paint();
        mBgPaint.setStyle(Paint.Style.STROKE);
        mBgPaint.setStrokeWidth(bgWidh);
        mBgPaint.setColor(getResources().getColor(R.color.gray));
        mBgPaint.setAntiAlias(true);

        caiPaint = new Paint();
        caiPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        caiPaint.setColor(getResources().getColor(R.color.btn_green_normal));
        caiPaint.setAntiAlias(true);

        baiPaint = new Paint();
        baiPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        baiPaint.setColor(getResources().getColor(R.color.gray));
        baiPaint.setAntiAlias(true);

        huanPaint = new Paint();
        huanPaint.setStyle(Paint.Style.STROKE);
        huanPaint.setStrokeCap(Paint.Cap.ROUND);
        huanPaint.setStrokeJoin(Paint.Join.ROUND);
        huanPaint.setStrokeWidth(bgWidh);
        huanPaint.setColor(getResources().getColor(R.color.btn_green_normal));
        huanPaint.setAntiAlias(true);

        linePaint =new Paint();
        linePaint.setColor(getResources().getColor(R.color.white));
        linePaint.setStrokeWidth(MiscUtil.dipToPx(getContext(),2));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);

        huanRectf = new RectF();



    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw  背景
        int  x = getWidth()/2;
        int y  = getHeight()/2;
        canvas.drawCircle(x,y,RADIUS,mBgPaint);
        // draw 彩色圆环
        huanRectf.set(x-RADIUS,y-RADIUS,x+RADIUS,y+RADIUS);
        canvas.drawArc(huanRectf,-90,sweepAngles,false,huanPaint);
        if(sweepAngles == 360){
            canvas.drawCircle(x,y,RADIUS,caiPaint);
            canvas.drawCircle(x,y,xRadius,baiPaint);
        }
        if(xRadius == 0 ){
            xRadius = RADIUS;
            linePath = new Path();
            linePath .moveTo(x-RADIUS/4,y);
            linePath.lineTo(x,y+RADIUS/4);
            linePath.lineTo(x+RADIUS/2,y-RADIUS/2);
            ObjectAnimator scaleAnimatorx = new ObjectAnimator().ofFloat(this,"scaleX",1,1.5f,1);
            ObjectAnimator scaleAnimatory = new ObjectAnimator().ofFloat(this,"scaleY",1,1.5f,1);
            AnimatorSet animatorSets = new AnimatorSet();
            animatorSets.play(scaleAnimatorx).with(scaleAnimatory);
            animatorSets.start();
            canvas.drawPath(linePath,linePaint);

        }

    }

    public void startAnimation(){
        ValueAnimator  huanAnimator = new ValueAnimator().ofInt(mStartAngle,360);
        huanAnimator.setDuration(500);
        huanAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                sweepAngles = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        huanAnimator.start();
        ValueAnimator xiaoAnimator = new ValueAnimator().ofInt(RADIUS,0);
        xiaoAnimator.setDuration(300);
        xiaoAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                xRadius  = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(huanAnimator).before(xiaoAnimator);
        animatorSet.start();
    }




}
