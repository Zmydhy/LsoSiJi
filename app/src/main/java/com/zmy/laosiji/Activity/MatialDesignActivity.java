package com.zmy.laosiji.Activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zmy.laosiji.R;

import butterknife.BindView;

public class MatialDesignActivity extends BaseActivity {

    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.title_md_gone)
    RelativeLayout titleMdGone;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_matial_design);
        setToolBarId(R.id.toolbar);
    }
}
