package com.zmy.laosiji.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.zmy.laosiji.R;

/**
 * Created by Michael on 2017/10/18.
 * 刮刮乐效果
 */

public class ShaderView extends View {
    private Bitmap mDst ;
    private Bitmap mSrc;
    private Paint mPanint  = new Paint();
    private Path  mPath ;
    Canvas mCavas ;
    public ShaderView(Context context) {
        this(context,null);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPanint.setAlpha(0);
        mPanint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPanint.setStyle(Paint.Style.STROKE);
        mPanint.setStrokeWidth(50);
        mPanint.setStrokeCap(Paint.Cap.ROUND);
        mPanint.setStrokeJoin(Paint.Join.ROUND);
        mPath = new Path();
        mDst = BitmapFactory.decodeResource(getResources(), R.mipmap.dst);
        mSrc = Bitmap.createBitmap(mDst.getWidth(),mDst.getHeight(),Bitmap.Config.ARGB_8888);
        mCavas = new Canvas(mSrc);
        mCavas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;

        }
        mCavas.drawPath(mPath,mPanint);
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mDst,0,0,null);
        canvas.drawBitmap(mSrc,0,0,null);
    }
}
