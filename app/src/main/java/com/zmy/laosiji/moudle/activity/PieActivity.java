package com.zmy.laosiji.moudle.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.utils.ConstantUtil;
import com.zmy.laosiji.widgets.animatedpieview.AnimatedPieView;
import com.zmy.laosiji.widgets.animatedpieview.AnimatedPieViewConfig;
import com.zmy.laosiji.widgets.animatedpieview.callback.OnPieSelectListener;
import com.zmy.laosiji.widgets.animatedpieview.data.IPieInfo;
import com.zmy.laosiji.widgets.animatedpieview.data.SimplePieInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PieActivity extends BaseActivity {

    private final Random random = new Random();
    private AnimatedPieView mAnimatedPieView;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pie, "饼状图");
        initView();
    }


    private void initView() {
        List<SimplePieInfo> mSimplePieInfos = new ArrayList();
        mSimplePieInfos.add(new SimplePieInfo(20.0f, getColor("FFFFD28C"),"20%"));
        mSimplePieInfos.add(new SimplePieInfo(30f, getColor("FFbb76b4"),"30%"));
        mSimplePieInfos.add(new SimplePieInfo(35.0f, getColor("FFD2008C"),"35%"));
        mSimplePieInfos.add(new SimplePieInfo(15.0f, getColor("ff2bbc80"),"15%"));
        mAnimatedPieView = findViewById(R.id.animatedPieView);
        AnimatedPieViewConfig config = new AnimatedPieViewConfig();
        config.setStartAngle(-90)
                .addDatas(mSimplePieInfos)
                .setTouchExpandAngle(10f)// 点击后圆弧/扇形扩展的角度
                .setTouchShadowRadius(8f)// 点击后的阴影扩散范围
                .setTouchScaleUpDuration(200)// 点击浮现动画时间
                .setTouchScaleDownDuration(200)// 上一个浮现的圆弧回退的动画时间
                .setDrawText(true)
                .setDrawStrokeOnly(false)
                .setDuration(1000)
                .setTextLineStrokeWidth(4)
                .setTextLineTransitionLength(100)
                .setTextLineStartMargin(8)// 设置描述文字的指示线开始距离外圆半径的大小
                .setTextSize(50)
                .setTextMarginLine(50)// 绘制文字与描述线的距离
                .setDirectText(false)
                .setPieRadiusScale(0.8f)
                .setOnPieSelectListener(new OnPieSelectListener<IPieInfo>() {
                    @Override
                    public void onSelectPie(@NonNull IPieInfo pieInfo, boolean isScaleUp) {
                        if (isScaleUp) {
                            ConstantUtil.toast(pieInfo.getDesc());
                        }

                    }
                })
                .setFocusAlphaType(AnimatedPieViewConfig.FOCUS_WITH_ALPHA_REV);
        mAnimatedPieView.applyConfig(config);
        mAnimatedPieView.start();

    }

    private int getColor(String colorStr) {
        if (TextUtils.isEmpty(colorStr)) return Color.BLACK;
        if (!colorStr.startsWith("#")) colorStr = "#" + colorStr;
        return Color.parseColor(colorStr);
    }

}
