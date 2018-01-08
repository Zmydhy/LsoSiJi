package com.zmy.laosiji.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.zmy.laosiji.R;
import com.zmy.laosiji.utils.ConstantUtil;

/**
 * Created by Administrator on 2018/1/4.
 */


public class SunLightVIew extends View {
    private Paint OnePaint;
    private Paint TwoPanint;
    private ValueAnimator valueAnimator;
    private int x = 400;

    public SunLightVIew(Context context) {
        this(context, null);
    }

    public SunLightVIew(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SunLightVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        OnePaint = new Paint();
        OnePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        OnePaint.setColor(getResources().getColor(R.color.btn_green_normal));
        OnePaint.setAntiAlias(true);

        TwoPanint = new Paint();
        TwoPanint.setStyle(Paint.Style.FILL_AND_STROKE);
        TwoPanint.setColor(getResources().getColor(R.color.gray));
        TwoPanint.setAntiAlias(true);
        initAnimatot();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(400, 400, 200, OnePaint);
        canvas.drawCircle(x, 400, 200, TwoPanint);
    }

    private void initAnimatot() {
//        valueAnimator = ValueAnimator.ofInt(400, 0, 800);
        valueAnimator = ValueAnimator.ofInt(400, 0);
        valueAnimator.setDuration(3000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int s = (int) animation.getAnimatedValue();
                ConstantUtil.log_e("平移：" + s);
                x = s;
                postInvalidate();

            }
        });
        valueAnimator.start();
    }

    public void stopAnimator() {
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
    }
}
