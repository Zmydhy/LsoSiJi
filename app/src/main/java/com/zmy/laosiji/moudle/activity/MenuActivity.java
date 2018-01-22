package com.zmy.laosiji.moudle.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.jni.JniActivity;
import com.zmy.laosiji.rxhttp.RxBus;
import com.zmy.laosiji.utils.ConstantUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class MenuActivity extends BaseActivity {


    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_menu,"Menu菜单");
        getWindow().setEnterTransition(new android.transition.Slide(Gravity.RIGHT));
        setupTransition();
        setToolBarMenuOnclick(onMenuItemClick);
    }

    private void setupTransition() {
        Slide slide = new Slide(Gravity.RIGHT);
        slide.setDuration(1000);
        slide.setInterpolator(new FastOutSlowInInterpolator());
        slide.excludeTarget(android.R.id.statusBarBackground, true);
        slide.excludeTarget(android.R.id.navigationBarBackground, true);
        slide.excludeTarget(R.id.toolbar, true);
        getWindow().setExitTransition(slide);
    }
    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6, R.id.text7, R.id.text8, R.id.text9, R.id.text10 ,R.id.text11,R.id.text12,R.id.text13,R.id.text14,R.id.text15})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                startActivitys(CustomViewActivity.class);
                break;
            case R.id.text2:
                startActivitys(TranslateActivity.class);
                break;
            case R.id.text3:
                startActivitys(ThemeNightActivity.class);
                break;
            case R.id.text4:
                startActivitys(ByclerViewActivity.class);
                break;
            case R.id.text5:
                startActivitys(MatialDesignActivity.class);
                break;
            case R.id.text6:
                Intent intent=new Intent(MenuActivity.this,TransitionActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());;
                break;
            case R.id.text7:
                startActivitys(ScrollActivity.class);
                break;
            case R.id.text8:
                startActivitys(AIDLActivity.class);
                break;
            case R.id.text9:
                startActivitys(PermissionActivity.class);
                break;
            case R.id.text10:
                startActivitys(SunLightActivity.class);
                break;
            case R.id.text11:
                RxBus.getRxBusBehavior().post("ddddddddd");
                RxBus.getRxBusBehavior().post("fffffffff");
                RxBus.getRxBusBehavior().post("eeeeeeeeee");
                startActivitys(PermissionActivity.class);
                break;
            case R.id.text12:
                startActivitys(DialogActivity.class);
                break;
            case R.id.text13:
                startActivitys(PieActivity.class);
                break;
            case R.id.text14:
                startActivitys(JniActivity.class);
                break;
            case R.id.text15:
                break;
            default:
                break;
        }
    }

    private void startActivitys(Class<?> sclass) {
        Intent  intent = new Intent(this,sclass);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_work_space_drawer, menu);
        return true;
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.nav_meizhi:
                    //业务逻辑
                    ConstantUtil.toast("点击了menu");
                    break;
                default:
                    break;
            }
            return true;
        }
    };


}
