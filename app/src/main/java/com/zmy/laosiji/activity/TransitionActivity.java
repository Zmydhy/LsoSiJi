package com.zmy.laosiji.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.zmy.laosiji.R;

import butterknife.BindView;
import butterknife.OnClick;

public class TransitionActivity extends BaseActivity {

    @BindView(R.id.img_android)
    ImageView imgAndroid;
    @BindView(R.id.img_js)
    ImageView imgJs;
    @BindView(R.id.lin_transltes)
    LinearLayout linTransltes;
    @BindView(R.id.scroll_transltes)
    ScrollView scrollTransltes;
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.img_ios)
    ImageView imgIos;
    @BindView(R.id.lin_translte)
    LinearLayout linTranslte;
    @BindView(R.id.img_other)
    ImageView imgOther;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_transition);
        setTitle("界面动画",null,"超炫酷");
        linTranslte.setVisibility(View.GONE);
        getWindow().setEnterTransition(new android.transition.Slide(Gravity.RIGHT));
    }

    @OnClick({R.id.img_android, R.id.img_js, R.id.img_other})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_android:
                transitionTo(imgAndroid,R.mipmap.bg_android);
                break;
            case R.id.img_js:
                transitionTo(imgJs,R.mipmap.bg_js);
                break;
            case R.id.img_other:
                transitionTo(imgOther,R.mipmap.bg_other);
                break;
            default:
                break;
        }
    }

    protected void transitionTo(ImageView mImgView ,int layoutId) {
        Intent intent = new Intent(this, TransitionToActivity.class);
        intent.putExtra("IMAGE",layoutId);
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(this,
                        mImgView, "testImg");
        startActivity(intent, options.toBundle());
//        overridePendingTransition(0, 0);

    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }


}
