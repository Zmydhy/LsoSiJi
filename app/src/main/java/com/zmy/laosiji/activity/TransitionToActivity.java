package com.zmy.laosiji.activity;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.zmy.laosiji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransitionToActivity extends AppCompatActivity {


    @BindView(R.id.btn_transition)
    Button btnTransition;
    @BindView(R.id.btn_transition1)
    Button btnTransition1;
    @BindView(R.id.tv_tansition)
    TextView tvTansition;
    @BindView(R.id.img_transition_to)
    ImageView imgTransitionTo;
    @BindView(R.id.lin_transiton)
    LinearLayout linTransiton;
    @BindView(R.id.tv_tansition1)
    TextView tvTansition1;
    @BindView(R.id.tv_tansition2)
    TextView tvTansition2;
    @BindView(R.id.btn_transition2)
    Button btnTransition2;
    @BindView(R.id.btn_transition3)
    Button btnTransition3;
    @BindView(R.id.lin_btn)
    LinearLayout linBtn;
    private boolean visible = true;
    private final int white = R.color.white;
    private final int grey = R.color.black;
    private final int green = R.color.btn_green_normal;
    String secText = " Second Text Second Text Second Text Second Text";
    String firstText = "First Text First Text First Text First Text First Text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStatusBarUpperAPI21();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().setAllowEnterTransitionOverlap(true);
        setContentView(R.layout.activity_transition_to);
        ButterKnife.bind(this);
        int imageId = getIntent().getIntExtra("IMAGE", 0);
        imgTransitionTo.setBackgroundResource(imageId);
    }


    @OnClick()
    public void onViewClicked() {
    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.btn_transition, R.id.img_transition_to, R.id.btn_transition1, R.id.btn_transition2, R.id.btn_transition3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_transition:
                //设置动画时间，设置动画加速度，设置动画延时
//                Fade fade = new Fade();
//                fade.setDuration(300);
//                fade.setInterpolator(new FastOutSlowInInterpolator());
//                fade.setStartDelay(200);
//                TransitionManager.beginDelayedTransition(linTransiton, fade);
//
//                if (tvTansition.getVisibility() == View.VISIBLE) {
//                    tvTansition.setVisibility(View.GONE);
//                } else {
//                    tvTansition.setVisibility(View.VISIBLE);
//                }
                Fade fade1 = new Fade();
                fade1.setDuration(300);
                //设置目标控件
                fade1.excludeTarget(tvTansition, true);
//                fade1.setInterpolator(new FastOutSlowInInterpolator());
//                fade1.setStartDelay(100);

                Slide slide = new Slide(Gravity.RIGHT);
                slide.setDuration(300);
//                slide.setInterpolator(new FastOutSlowInInterpolator());
//                slide.setStartDelay(200);

                TransitionSet set = new TransitionSet()
                        .addTransition(slide)
                        .addTransition(fade1)
                        .setInterpolator(tvTansition.getVisibility() == View.VISIBLE ? new LinearOutSlowInInterpolator() :
                                new FastOutLinearInInterpolator());

                TransitionManager.beginDelayedTransition(linTransiton, set);

                if (tvTansition2.getVisibility() == View.VISIBLE) {
                    tvTansition.setVisibility(View.GONE);
                    tvTansition1.setVisibility(View.GONE);
                    tvTansition2.setVisibility(View.GONE);
                } else {
                    tvTansition.setVisibility(View.VISIBLE);
                    tvTansition1.setVisibility(View.VISIBLE);
                    tvTansition2.setVisibility(View.VISIBLE);
                }

                break;
            case R.id.btn_transition1:
                Recolor recolor = new Recolor();
                recolor.setDuration(500);
                TransitionManager.beginDelayedTransition(linBtn, new Recolor());
                TransitionManager.beginDelayedTransition(linBtn, new Rotate());
                TransitionManager.beginDelayedTransition(linTransiton,
                        new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));


                if (visible) {
                    visible =false;
                    btnTransition2.setRotation( 135 );
                    //            btnRecolor.setBackgroundColor(visible ? green : white); // 无动画效果
                    btnTransition1.setTextColor(getResources().getColor(white));
                    btnTransition1.setBackgroundDrawable(new ColorDrawable(getResources().getColor(green)));
                    tvTansition.setText(firstText);
                } else {
                    visible =true;
                    btnTransition2.setRotation( 0 );
                    //            btnRecolor.setBackgroundColor(visible ? green : white); // 无动画效果
                    btnTransition1.setTextColor(getResources().getColor(grey));
                    btnTransition1.setBackgroundDrawable(new ColorDrawable( getResources().getColor(white)));
                    tvTansition.setText(secText);
                }
                break;
            case R.id.btn_transition2:
                break;
            case R.id.btn_transition3:
                break;
            case R.id.img_transition_to:
                //翻转过度
                supportFinishAfterTransition();
                break;
            default:
                break;
        }
    }
}
