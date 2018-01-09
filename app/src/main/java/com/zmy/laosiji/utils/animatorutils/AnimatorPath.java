package com.zmy.laosiji.utils.animatorutils;

import android.animation.ObjectAnimator;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AnimatorPath {
    ArrayList<PathPoint> mPoints = new ArrayList<>();
    private View  view;
    public void moveto(int x, int y) {
        mPoints.add(PathPoint.moveto(x,y));
    }

    public void lineto(int x, int y) {
        mPoints.add(PathPoint.lineto(x,y));
    }
    public void cubto(float x0, float y0,float x1, float y1,float x2, float y2){
        mPoints.add(PathPoint.cireto(x0,y0,x1,y1,x2,y2));
    }

    public void startAnimator(View view) {
        this.view  = view;
        ObjectAnimator animator = ObjectAnimator.ofObject(this,"translate",new AnimatorEv(),mPoints.toArray());
        animator.setDuration(3000);
        animator.setRepeatCount(10);
        animator.start();
    }

    public void setTranslate(PathPoint point){
        view.setTranslationX(point.mX);
        view.setTranslationY(point.mY);
    }
}
