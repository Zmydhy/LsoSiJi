package com.zmy.laosiji.utils.animatorutils;

/**
 * Created by Administrator on 2018/1/4.
 */

public class PathPoint {
    public static final int LINE = 1;
    public static final int MOVE = 0;
    public static final int CIRCLE = 2;

    public float mX;
    public float mY;
    public int mOperation;
    public  float mC0X;
    public  float mC1X;
    public  float mC0Y;
    public  float mC1Y;

    public PathPoint(int type, float x, float y) {
        if (type == PathPoint.MOVE) {
            this.mX = x;
            this.mY = y;
            this.mOperation = PathPoint.MOVE;
        } else {
            this.mX = x;
            this.mY = y;
            this.mOperation = PathPoint.LINE;
        }
    }

    public PathPoint(int type, float x0, float y0,float x1, float y1,float x2, float y2) {
        this.mC0X = x0;
        this.mC0Y = y0;
        this.mC1X = x1;
        this.mC1Y = y1;
        this.mX = x2;
        this.mY = y2;
        this.mOperation = PathPoint.CIRCLE;
    }

    public static PathPoint moveto(float x, float y) {

        return new PathPoint(MOVE, x, y);
    }

    public static PathPoint lineto(float x, float y) {
        return new PathPoint(LINE, x, y);
    }

    public static PathPoint cireto(float x0, float y0,float x1, float y1,float x2, float y2 ) {
        return new PathPoint(CIRCLE, x0, y0, x1,y1,x2,y2);
    }
}
