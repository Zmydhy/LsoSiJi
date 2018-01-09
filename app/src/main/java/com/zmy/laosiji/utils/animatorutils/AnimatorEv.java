package com.zmy.laosiji.utils.animatorutils;

import android.animation.TypeEvaluator;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AnimatorEv implements TypeEvaluator<PathPoint> {
    @Override
    public PathPoint evaluate(float t, PathPoint startpoint, PathPoint endpoint) {
        float x, y;
        if (endpoint.mOperation == PathPoint.MOVE) {
            x = endpoint.mX;
            y = endpoint.mY;
        } else if (endpoint.mOperation == PathPoint.CIRCLE) {
            float oneT = 1-t;
            x= oneT*oneT*oneT*startpoint.mX+
                    3*oneT*oneT*t*endpoint.mC0X+
                    3*oneT*t*t*endpoint.mC1X+
                    t*t*t*endpoint.mX;
            y= oneT*oneT*oneT*startpoint.mY+
                    3*oneT*oneT*t*endpoint.mC0Y+
                    3*oneT*t*t*endpoint.mC1Y+
                    t*t*t*endpoint.mY;
        } else {
            x = startpoint.mX + t * (endpoint.mX - startpoint.mX);
            y = startpoint.mY + t * (endpoint.mY - startpoint.mY);
        }
        return new PathPoint(endpoint.mOperation, x, y);
    }
}
