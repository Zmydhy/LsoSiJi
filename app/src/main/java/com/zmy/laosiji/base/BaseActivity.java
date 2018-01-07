package com.zmy.laosiji.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zmy.laosiji.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {


    /**
     * subtitle
     */
    private TextView toolbarBaseLeft;
    /**
     * subtitle
     */
    private TextView toolbarBaseRight;
    /**
     * title
     */
    private TextView toolbarBaseTitle;
    private Toolbar baseToolbar;
    private FrameLayout baseFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
        setSupportActionBar(baseToolbar);
        //设置actionBar的标题是否显示，对应ActionBar.DISPLAY_SHOW_TITLE。
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setContentView(savedInstanceState);
        setBackArrow();//设置返回按钮和点击事件

    }

    private void initView() {
        toolbarBaseLeft = (TextView) findViewById(R.id.toolbar_base_left);
        toolbarBaseRight = (TextView) findViewById(R.id.toolbar_base_right);
        toolbarBaseTitle = (TextView) findViewById(R.id.toolbar_base_title);
        baseToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        baseFrame = (FrameLayout) findViewById(R.id.base_frame);
        toolbarBaseLeft.setOnClickListener(this);
        toolbarBaseRight.setOnClickListener(this);
    }

    protected abstract void setContentView(Bundle savedInstanceState);


    /**
     * menu的点击事件
     *
     * @param onclick
     */
    public void setToolBarMenuOnclick(Toolbar.OnMenuItemClickListener onclick) {
        baseToolbar.setOnMenuItemClickListener(onclick);
    }

    /**
     * 隐藏ToolBar，通过setToolBar重新定制ToolBar
     */
    public void hidetoolBar() {
        baseToolbar.setVisibility(View.GONE);
    }

    /**
     * 子类调用，重新设置Toolbar
     *
     * @param layout
     */
    public void setToolBarId(int layout) {
        hidetoolBar();
        baseToolbar = (Toolbar) baseFrame.findViewById(layout);
        setSupportActionBar(baseToolbar);
        //设置actionBar的标题是否显示，对应ActionBar.DISPLAY_SHOW_TITLE。
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public Toolbar getToolbar(){
        if(baseToolbar != null){
            return  baseToolbar;
        }
        return null;
    }

    /**
     * 设置左上角back按钮
     */
    public void setBackArrow() {
//        final Drawable upArrow = getResources().getDrawable(android.R.drawable.btn_plus);
//        //给ToolBar设置左侧的图标
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP 同在style里面修改了按钮的颜色
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回按钮的点击事件
        baseToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    /**
     * 设置toolbar下面内容区域的内容
     *
     * @param layoutId
     */
    public void setContentLayout(int layoutId) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        baseFrame.addView(contentView, params);
        ButterKnife.bind(this,contentView);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            toolbarBaseTitle.setText(title);
        }
    }
    /**
     * 设置标题,左右
     *
     * @param title
     */
    public void setTitle(String title,String left ,String right) {
        if (!TextUtils.isEmpty(title)) {
            toolbarBaseTitle.setText(title);
        }else{
            toolbarBaseTitle.setText("");
        }
        if (!TextUtils.isEmpty(right)) {
            toolbarBaseRight.setText(right);
        }else{
            toolbarBaseRight.setText("");
        }
        if (!TextUtils.isEmpty(left)) {
            toolbarBaseLeft.setText(left);
        }else{
            toolbarBaseLeft.setText("");
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar_base_left:
                break;
            case R.id.toolbar_base_right:
                break;
            default:
                break;
        }
    }
}
