package com.zmy.laosiji.widgets;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;

import com.zmy.laosiji.R;
import com.zmy.laosiji.utils.ConstantUtil;
import com.zmy.laosiji.utils.MeasurecUtils;

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
    private static final int RADIUS = 150;
    private int bgWidh = 20;
    private Paint mBgPaint;
    private Paint huanPaint;
    private Paint caiPaint;
    private Paint baiPaint;
    private Paint linePaint;
    private RectF huanRectf;
    private int sweepAngles = 0;
    private int xRadius = 150;
    private Path linePath;
    private int mStartAngle = 0;
    private Path mDstPath;
    private PathMeasure pathMeasure;
    private float animatorValue;
    private float animatorValues;
    //draw  背景
    int x;
    int y;

    private Path linnersquarepath;
    private Path outersquarepath;
    private AnimatorSet animatorSet;
    private  ValueAnimator valueAnimators;

    public CircleVIew(Context context) {
        this(context, null);
    }

    public CircleVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPath() {
        huanRectf = new RectF();
        linePath = new Path();
        mDstPath = new Path();
        linnersquarepath = new Path();
        outersquarepath = new Path();
        pathMeasure = new PathMeasure();

        linePath.moveTo(x - RADIUS / 4, y);
        linePath.lineTo(x, y + RADIUS / 4);
        linePath.lineTo(x + RADIUS / 2, y - RADIUS / 2);

        linnersquarepath.moveTo(x - 2* RADIUS, y - 2 * RADIUS);
        linnersquarepath.lineTo(x + 2* RADIUS, y - 2 * RADIUS);
        linnersquarepath.lineTo(x + 2* RADIUS, y + 2 * RADIUS);
        linnersquarepath.lineTo(x - 2 * RADIUS, y + 2* RADIUS);
        linnersquarepath.lineTo(x - 2* RADIUS, y - 2 * RADIUS);

        outersquarepath.moveTo(x - 1.5f* RADIUS, y - 1.5f * RADIUS);
        outersquarepath.lineTo(x + 1.5f* RADIUS, y - 1.5f * RADIUS);
        outersquarepath.lineTo(x + 1.5f* RADIUS, y + 1.5f * RADIUS);
        outersquarepath.lineTo(x - 1.5f * RADIUS, y + 1.5f* RADIUS);
        outersquarepath.lineTo(x - 1.5f* RADIUS, y - 1.5f * RADIUS);

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

        huanPaint = new Paint();
        huanPaint.setStyle(Paint.Style.STROKE);
        huanPaint.setStrokeCap(Paint.Cap.ROUND);
        huanPaint.setStrokeJoin(Paint.Join.ROUND);
        huanPaint.setStrokeWidth(bgWidh);
        huanPaint.setColor(getResources().getColor(R.color.btn_green_normal));
        huanPaint.setAntiAlias(true);


        caiPaint = new Paint();
        caiPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        caiPaint.setColor(getResources().getColor(R.color.btn_green_normal));
        caiPaint.setAntiAlias(true);

        baiPaint = new Paint();
        baiPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        baiPaint.setColor(getResources().getColor(R.color.gray));
        baiPaint.setAntiAlias(true);

        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.white));
        linePaint.setStrokeWidth(MeasurecUtils.dipToPx(getContext(), 2));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw  背景
        x = getWidth() / 2;
        y = getHeight() / 2;
        initPath();

        if (sweepAngles != 360) {
            canvas.drawCircle(x, y, RADIUS, mBgPaint);
            // draw 彩色圆环
            huanRectf.set(x - RADIUS, y - RADIUS, x + RADIUS, y + RADIUS);
            canvas.drawArc(huanRectf, -90, sweepAngles, false, huanPaint);
        }
        if (sweepAngles == 360 && animatorValue == 0) {
            canvas.drawCircle(x, y, RADIUS, caiPaint);
            canvas.drawCircle(x, y, xRadius, baiPaint);
        }
        if (xRadius == 0) {
            canvas.drawCircle(x, y, RADIUS, caiPaint);
            mDstPath.reset();
            // 硬件加速的BUG
            mDstPath.lineTo(0, 0);
            pathMeasure.setPath(linnersquarepath,false);
            float ends = pathMeasure.getLength() * animatorValue;
            pathMeasure.getSegment(0f, ends, mDstPath, true);
            linePaint.setColor(getResources().getColor(R.color.btn_blue_normal));
            canvas.drawPath(mDstPath, linePaint);
            mDstPath.reset();
            pathMeasure.setPath(outersquarepath,false);
            float stop = pathMeasure.getLength() * animatorValues;
            float start = (float) (stop - ((0.5 - Math.abs(animatorValues - 0.5)) * pathMeasure.getLength()));
            pathMeasure.getSegment(start, stop, mDstPath, true);
            canvas.drawPath(mDstPath, linePaint);
            mDstPath.reset();
            pathMeasure.setPath(linePath, false);
            float endss = pathMeasure.getLength() * animatorValue;
            pathMeasure.getSegment(0f, endss, mDstPath, true);
            linePaint.setColor(getResources().getColor(R.color.white));
            canvas.drawPath(mDstPath, linePaint);
//            ObjectAnimator scaleAnimatorx = new ObjectAnimator().ofFloat(this, "scaleX", 1, 1.5f, 1);
//            ObjectAnimator scaleAnimatory = new ObjectAnimator().ofFloat(this, "scaleY", 1, 1.5f, 1);
//            AnimatorSet animatorSets = new AnimatorSet();
//            animatorSets.play(scaleAnimatorx).with(scaleAnimatory);
//            animatorSets.start();
        }

    }

    public void startAnimation() {
        sweepAngles = 0;
        xRadius = 150;
        animatorValue = 0f;
        ValueAnimator huanAnimator = new ValueAnimator().ofInt(mStartAngle, 360);
        huanAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                sweepAngles = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        ValueAnimator xiaoAnimator = new ValueAnimator().ofInt(RADIUS, 0);
        xiaoAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                xRadius = (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatorValue = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(1000);

        valueAnimators = ValueAnimator.ofFloat(0, 1);
        valueAnimators.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatorValues = (float) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimators.setRepeatCount(-1);
        valueAnimators.setDuration(1000);
        valueAnimator.setInterpolator(new FastOutSlowInInterpolator());
        animatorSet = new AnimatorSet();
        animatorSet.playSequentially(huanAnimator, xiaoAnimator,valueAnimator);
        animatorSet.play(valueAnimator).with(valueAnimators);
        animatorSet.start();


    }


    public void setCancelAnimation() {
        if(valueAnimators != null){
            valueAnimators.cancel();
            ConstantUtil.log_e("置空animation");
        }
        if(animatorSet != null){
            animatorSet.cancel();
            ConstantUtil.log_e("置空animationSet");
        }

    }
}
