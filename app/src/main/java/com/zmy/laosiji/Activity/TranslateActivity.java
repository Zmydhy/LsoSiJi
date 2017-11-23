package com.zmy.laosiji.Activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zmy.laosiji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TranslateActivity extends BaseActivity {

    @BindView(R.id.img_android)
    ImageView imgAndroid;
    @BindView(R.id.img_js)
    ImageView imgJs;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.img_ios)
    ImageView imgIos;
    @BindView(R.id.lin_translte)
    LinearLayout linTranslte;
    @BindView(R.id.lin_transltes)
    LinearLayout linTransltes;
    @BindView(R.id.scroll_transltes)
    ScrollView scrollTransltes;

    private float mDensity;
    private int mHiddenViewHeight;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_translate);
//
//    }

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_translate);
        setTitle("移动布局");//设置标题

        //获取像素密度
        mDensity = getResources().getDisplayMetrics().density;
        mHiddenViewHeight = (int) (mDensity * 200 + 0.5);
    }


    private void closeAnimation(final ImageView imgIoss) {
        ValueAnimator animator = ValueAnimator.ofInt(mHiddenViewHeight, 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams laparams = imgIoss.getLayoutParams();
                laparams.height = value;
                imgIoss.setLayoutParams(laparams);
                if (value == 0) {
                    imgIos.setVisibility(View.GONE);
                }
            }
        });
        animator.setDuration(150);
        animator.start();


    }

    private void statAnimation(final ImageView imgIos) {
        imgIos.setVisibility(View.VISIBLE);
        ValueAnimator animator = ValueAnimator.ofInt(0, mHiddenViewHeight);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int value = (int) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams laparams = imgIos.getLayoutParams();
                laparams.height = value;
                imgIos.setLayoutParams(laparams);
            }
        });
        animator.setDuration(100);
        animator.start();
    }

    @OnClick({R.id.btn_start, R.id.img_ios})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                if (imgIos.getVisibility() == View.GONE) {
                    //开启动画
                    statAnimation(imgIos);
                    linTranslte.setBackgroundColor(getResources().getColor(R.color.translte));
                    scrollTransltes.setClickable(false);
                } else {
                    //关闭动画
                    closeAnimation(imgIos);
                    linTranslte.setBackgroundColor(getResources().getColor(R.color.untranslte));
                    scrollTransltes.setClickable(true);
                }
                break;
            case R.id.img_ios:
                //关闭动画
                closeAnimation(imgIos);
                linTranslte.setBackgroundColor(getResources().getColor(R.color.untranslte));
                scrollTransltes.setEnabled(true);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }



}
