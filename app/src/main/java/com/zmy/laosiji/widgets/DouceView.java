package com.zmy.laosiji.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class DouceView  extends ImageView{
    public DouceView(Context context) {
        this(context,null);
    }

    public DouceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DouceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
