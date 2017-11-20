package com.zmy.laosiji.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.zmy.laosiji.CustomView.CircleVIew;
import com.zmy.laosiji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CustomViewActivity extends AppCompatActivity {

    @BindView(R.id.btn_anmitor)
    Button btnAnmitor;
    @BindView(R.id.cireview)
    CircleVIew cireview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_in_back, R.anim.right_out_back);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.right_out);
    }


    @OnClick(R.id.btn_anmitor)
    public void onViewClicked() {
        cireview.startAnimation();
//        ObjectAnimator animator = ObjectAnimator.ofFloat(
//                cireview, "translationX", 200);
//        ObjectAnimator animators = ObjectAnimator.ofFloat(
//                cireview, "translationX", 0);
//        AnimatorSet dd = new AnimatorSet();
//        dd.play(animator).before(animators);
//        dd.setDuration(800);
//        dd.start();
    }
}

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int rawx = (int) event.getRawX();
//        int  rawy = (int) event.getRawY();
//        switch (event.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                lastx = rawx;
//                lasty = rawy;
//                Log.i("TAG","latx:"+lastx+",lasty:"+lasty+",getWidth:"+view.getWidth()+",view.getHeight():"+view.getHeight());
//                view.layout(lastx-view.getWidth()/2,lasty-view.getHeight(),lastx+view.getWidth()/2,lasty);
//                break;
//
//        }
//        return false;
//    }
//        mBtn = (Button) findViewById(R.id.btn_valueanimtor);
//        view = (MyView) findViewById(R.id.myview);
//        view2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ObjectAnimator anim = ObjectAnimator.ofObject(view2, "color", new ColorEvaluator(),
//                        "#0000FF", "#FF0000", "#00ff00");
//                // 设置自定义View对象、背景颜色属性值 & 颜色估值器
//                // 本质逻辑：
//                // 步骤1：根据颜色估值器不断 改变 值
//                // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
//                // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果
//                anim.setRepeatCount(ValueAnimator.INFINITE);
//                anim.setDuration(6000);
//                anim.start();
//
//
//            }
//        });
//        mBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isUp) {
//                    isUp = false;
////                    setaLPha();
//                    downButton();
//                } else {
//                    isUp = true;
//                    upButton();
//                }
//
//            }
//
//        });
//    private void upButton() {
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(mBtn.getLayoutParams().width, 500);
//        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
//        // 结束值 = 500
//        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
//        // 即默认设置了如何从初始值150 过渡到 结束值500
//
//        // 步骤2：设置动画的播放各种属性
//        valueAnimator.setDuration(1000);
//        // 设置动画运行时长:1s
//
//        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
//        // 设置更新监听器：即数值每次变化更新都会调用该方法
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animator) {
//
//                int currentValue = (Integer) animator.getAnimatedValue();
//                // 获得每次变化后的属性值
//                System.out.println(currentValue);
//                // 输出每次变化后的属性值进行查看
//
//                mBtn.getLayoutParams().width = currentValue;
//                // 每次值变化时，将值手动赋值给对象的属性
//                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化
//
//                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
//                mBtn.requestLayout();
//
//            }
//        });
//        // 启动动画
//        valueAnimator.start();
//    }

//    public void downButton() {
//        ValueAnimator valueAnimator = ValueAnimator.ofInt(mBtn.getLayoutParams().width, DpUtils.dp2px(this, 150));
//        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
//        // 结束值 = 500
//        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
//        // 即默认设置了如何从初始值150 过渡到 结束值500
//
//        // 步骤2：设置动画的播放各种属性
//        valueAnimator.setDuration(1000);
//        // 设置动画运行时长:1s
//
//        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
//        // 设置更新监听器：即数值每次变化更新都会调用该方法
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animator) {
//
//                int currentValue = (Integer) animator.getAnimatedValue();
//                // 获得每次变化后的属性值
//                System.out.println(currentValue);
//                // 输出每次变化后的属性值进行查看
//
//                mBtn.getLayoutParams().width = currentValue;
//                // 每次值变化时，将值手动赋值给对象的属性
//                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化
//
//                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
//                mBtn.requestLayout();
//
//            }
//        });
//        // 启动动画
//        valueAnimator.start();
//
//    }
//
//    /**
//     * ObjectAnimator animator = ObjectAnimator.ofFloat(Object object, String property, float ....values);
//     * <p>
//     * // ofFloat()作用有两个
//     * // 1. 创建动画实例
//     * // 2. 参数设置：参数说明如下
//     * // Object object：需要操作的对象
//     * // String property：需要操作的对象的属性
//     * // 第二个参数一般传入：Alpha， TransactionX,TransactionY;ScaleX，ScaleY，Roation,RotationX,RotationY
//     * // ******************************************************************************************************************
//     * // float ....values：动画初始值 & 结束值（不固定长度）
//     * // 若是两个参数a,b，则动画效果则是从属性的a值到b值
//     * // 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
//     * // 以此类推
//     * // 至于如何从初始值 过渡到 结束值，同样是由估值器决定，此处ObjectAnimator.ofFloat（）是有系统内置的浮点型估值器FloatEvaluator，同ValueAnimator讲解
//     * <p>
//     * anim.setDuration(500);
//     * // 设置动画运行的时长
//     * <p>
//     * anim.setStartDelay(500);
//     * // 设置动画延迟播放时间
//     * <p>
//     * anim.setRepeatCount(0);
//     * // 设置动画重复播放次数 = 重放次数+1
//     * // 动画播放次数 = infinite时,动画无限重复
//     * <p>
//     * anim.setRepeatMode(ValueAnimator.RESTART);
//     * // 设置重复播放动画模式
//     * // ValueAnimator.RESTART(默认):正序重放
//     * //ValueAnimator.REVERSE:倒序回放
//     */
//    public void setaLPha() {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mBtn, "alpha", 1f, 0f);
//        // 表示的是:
//        // 动画作用对象是mButton
//        // 动画作用的对象的属性是透明度alpha
//        // 动画效果是:常规 - 全透明 - 常规
//        animator.setDuration(2000);
//        animator.setRepeatCount(10);
//        animator.setRepeatMode(ValueAnimator.RESTART);
//        animator.start();
//
//    }
//
//    public void setRoatation() {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mBtn, "rotation", 1f, 0f);
//        // 表示的是:
//        // 动画作用对象是mButton
//        // 动画作用的对象的属性是旋转alpha
//        // 动画效果是:0 - 360
//        animator.setDuration(5000);
//        animator.start();
//    }
//
//    public void setTranslationX() {
//        float curTranslationX = mBtn.getTranslationX();
//        // 获得当前按钮的位置
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mBtn, "translationX", curTranslationX, 300, curTranslationX);
//
//
//        // 表示的是:
//        // 动画作用对象是mButton
//        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
//        // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
//        animator.setDuration(5000);
//        animator.start();
//    }
//
//    public void setScale() {
//        ObjectAnimator animator = ObjectAnimator.ofFloat(mBtn, "scaleX", 1f, 3f, 1f);
//        // 表示的是:
//        // 动画作用对象是mButton
//        // 动画作用的对象的属性是X轴缩放
//        // 动画效果是:放大到3倍,再缩小到初始大小
//        animator.setDuration(5000);
//        animator.start();
//
//    }
//
//    /**
//     * 组合动画
//     * AnimatorSet.play(Animator anim)   ：播放当前动画
//     * AnimatorSet.after(long delay)   ：将现有动画延迟x毫秒后执行
//     * AnimatorSet.with(Animator anim)   ：将现有动画和传入的动画同时执行
//     * AnimatorSet.after(Animator anim)   ：将现有动画插入到传入的动画之后执行
//     * AnimatorSet.before(Animator anim) ：  将现有动画插入到传入的动画之前执行
//     */
//
//    public void setAnimationSet() {
//        float curTranslationX = mBtn.getTranslationX();
//        // 步骤1：设置需要组合的动画效果
//        ObjectAnimator translation = ObjectAnimator.ofFloat(mBtn, "translationX", curTranslationX, 300, curTranslationX);
//// 平移动画
//        ObjectAnimator rotate = ObjectAnimator.ofFloat(mBtn, "rotation", 0f, 360f);
//// 旋转动画
//        ObjectAnimator alpha = ObjectAnimator.ofFloat(mBtn, "alpha", 1f, 0f, 1f);
//// 透明度动画
//
//// 步骤2：创建组合动画的对象
//        AnimatorSet animSet = new AnimatorSet();
//
//// 步骤3：根据需求组合动画
//        animSet.play(translation).with(rotate).before(alpha);
//        animSet.setDuration(5000);
//
//// 步骤4：启动动画
//        animSet.start();
//    }
//
//    /**    //备用方法
//     *特别注意：每次监听必须4个方法都重写。
//     * Animation.addListener(new AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//            //动画开始时执行
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            //动画重复时执行
//            }
//
//            @Override
//            public void onAnimationCancel()(Animation animation) {
//            //动画取消时执行
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//            //动画结束时执行
//            }
//        });
//     ********************或者****************************************
//        这里不实现全部方法也不会报错
//             anim.addListener(new AnimatorListenerAdapter() {
//             // 向addListener()方法中传入适配器对象AnimatorListenerAdapter()
//             // 由于AnimatorListenerAdapter中已经实现好每个接口
//             /  / 所以这里不实现全部方法也不会报错
//             @Override public void onAnimationStart(Animator animation) {
//             // 如想只 想监听动画开始时刻，就只需要单独重写该方法就可以
//             }
//             });
//     */

