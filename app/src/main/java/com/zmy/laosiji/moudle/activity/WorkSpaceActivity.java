package com.zmy.laosiji.moudle.activity;

import android.app.ActivityOptions;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hubert.library.HighLight;
import com.app.hubert.library.NewbieGuide;
import com.zmy.laosiji.R;
import com.zmy.laosiji.moudle.adapter.MeiZhiAdapter;
import com.zmy.laosiji.moudle.adapter.MeiZhiDuoAdapter;
import com.zmy.laosiji.moudle.adapter.MeiZhiWorkAdapter;
import com.zmy.laosiji.moudle.entity.MeizhiEntity;
import com.zmy.laosiji.room.TestDao;
import com.zmy.laosiji.room.TestDataBase;
import com.zmy.laosiji.room.TestEntity;
import com.zmy.laosiji.rxhttp.BaseObservable;
import com.zmy.laosiji.rxhttp.HttpAPi;
import com.zmy.laosiji.rxhttp.HttpOnNextListener;
import com.zmy.laosiji.rxhttp.RxScheduleMapper;
import com.zmy.laosiji.tcp.SocketActivity;
import com.zmy.laosiji.utils.ConstantUtil;
import com.zmy.laosiji.utils.NetStateUtils;
import com.zmy.laosiji.widgets.tdialog.TDialog;
import com.zmy.laosiji.widgets.tdialog.base.BindViewHolder;
import com.zmy.laosiji.widgets.tdialog.listener.OnBindViewListener;
import com.zmy.laosiji.widgets.tdialog.listener.OnViewClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.ObservableEmitter;

import static com.zmy.laosiji.base.MyApplication.getContext;

public class WorkSpaceActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.recycler_view_work)
    RecyclerView recyclerViewWork;
    @BindView(R.id.toolbar_work)
    Toolbar toolbarWork;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.float_but_workmain)
    FloatingActionButton floatButWorkmain;
    @BindView(R.id.tv_for_fun)
    TextView tvForFun;
    private int PAGE = 1;
    private final int COUNT = 50;
    private List<MeizhiEntity.ResultsBean> mLists = new ArrayList<>();
    private List<TestEntity> mCacheList = new ArrayList<>();
    private MeiZhiWorkAdapter meiZhiWorkAdapter;
    private MeiZhiAdapter meiZhiAdapter;
    private MeiZhiDuoAdapter meiZhiDuoAdapter;
    private TestDao dao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_work_space);
        ButterKnife.bind(this);
        initView();
        initDatas();
    }

    private void initView() {
        toolbarWork.setTitle("带你装逼老司机");
        setSupportActionBar(toolbarWork);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbarWork, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        recyclerViewWork.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //设置蒙层
        NewbieGuide.with(this)//传入activity
                .setLabel("guide")//设置引导层标示，用于区分不同引导层，必传！否则报错
                .alwaysShow(true)
                .addHighLight(floatButWorkmain, HighLight.Type.CIRCLE)//添加需要高亮的view
                .setLayoutRes(R.layout.view_guide_custom)//自定义的提示layout，不要添加背景色，引导层背景色通过setBackgroundColor()设置
                .setBackgroundColor(getResources().getColor(R.color.transltes))//设置背景色，建议使用有透明度的颜色
                .fullScreen(true)
//                .asPage()//只有一页或者最后一页可以省略
//                .addHighLight(toolbarWork, HighLight.Type.RECTANGLE)
//                .setLayoutRes(R.layout.view_guide_custom)//引导页布局，点击跳转下一页或者消失引导层的空间id
//                .fullScreen(true)//是否全屏，即是否包含状态栏，默认false，设置为true需要Activity设置为全屏或者沉浸式状态栏
                .show();//显示引导层

    }

    private void initDatas() {
        //获取数据库
        dao = TestDataBase.getInstance().testDao();
        if(NetStateUtils.isNetworkConnect(getContext())){
            setCache();
            getCacheList();
        }else {
            getCacheList();
            new TDialog.Builder(getSupportFragmentManager())
                    .setLayoutRes(R.layout.dialog_vb_convert)
                    .setScreenWidthAspect(this, 0.85f)
                    .setCancelOutside(false)
                    .setOnBindViewListener(new OnBindViewListener() {
                        @Override
                        public void bindView(BindViewHolder bindViewHolder) {
                            bindViewHolder.setText(R.id.textView1, "老司机开车" );
                            bindViewHolder.setText(R.id.tv_jiuyuan_content, "连网都没有，开什么车...");
                            bindViewHolder.setText(R.id.tv_jiuyuan_desc, "快去连网吧");
                            bindViewHolder.setText(R.id.tv_confirm, "gogogo");
                        }
                    })
                    .addOnClickListener(R.id.tv_cancel, R.id.tv_confirm)
                    .setOnViewClickListener(new OnViewClickListener() {
                        @Override
                        public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                            switch (view.getId()) {
                                case R.id.tv_cancel:
                                    tDialog.dismiss();
                                    break;
                                case R.id.tv_confirm:
                                    Intent intentSettings;
                                    if(android.os.Build.VERSION.SDK_INT > 10){//判断版本(3.0以上)
                                        intentSettings = new Intent(Settings.ACTION_SETTINGS);
                                    }else{
                                        intentSettings = new Intent();
                                        intentSettings.setClassName("com.android.phone","com.android.phone.MobileNetWorkSettings");
                                    }
                                    startActivity(intentSettings);
                                    tDialog.dismiss();
                                    break;
                            }
                        }
                    })
                    .create()
                    .show();
        }

    }

    /**
     * 插入数据库
     */

    public void setCache() {

        HttpAPi.getMeiZhi("福利", 60, 1, new HttpOnNextListener<MeizhiEntity>(this) {
            @Override
            public void onNext(MeizhiEntity meizhiEntity) {

                HttpAPi.createObservable(new BaseObservable<String>() {

                    @Override
                    public String subscribe(ObservableEmitter<String> subscriber) {
                        mLists = meizhiEntity.getResults();
                        for (int i = 0; i < mLists.size(); i++) {
                            TestEntity testEntity = new TestEntity();
                            testEntity.setid(i);
                            testEntity.setUrl(mLists.get(i).getUrl());
                            dao.insertTest(testEntity);
                        }
                        return "完成";
                    }
                }).compose(RxScheduleMapper.io2main()).subscribe(HttpAPi.createObserver(new HttpOnNextListener<String>() {
                    @Override
                    public void onNext(String s) {
                        ConstantUtil.log_e("插入数据成功");
                        getCacheList();
                    }
                }));
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ConstantUtil.toast("加载失败了！！！");
            }
        });

    }

    public List<TestEntity> getCacheList() {
        HttpAPi.createObservable(new BaseObservable<List<TestEntity>>() {
            @Override
            public List<TestEntity> subscribe(ObservableEmitter<List<TestEntity>> subscriber) {
                List list = Arrays.asList(dao.query());
                return list;
            }
        }).compose(RxScheduleMapper.io2main()).subscribe(HttpAPi.createObserver(new HttpOnNextListener<List<TestEntity>>() {
            @Override
            public void onNext(List<TestEntity> testEntities) {
                mCacheList = testEntities;
                meiZhiWorkAdapter = new MeiZhiWorkAdapter(mCacheList, R.layout.item_work_meizhi);
                recyclerViewWork.setAdapter(meiZhiWorkAdapter);
                meiZhiWorkAdapter.setOnItemClick(new MeiZhiWorkAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClik(View view, int position) {
                        transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mCacheList.get(position).getUrl(), "妹纸在手，天下我有！");
                    }
                });
            }

        }));
        return mCacheList;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.work_space, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_ver) {
            recyclerViewWork.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            meiZhiWorkAdapter = new MeiZhiWorkAdapter(mCacheList, R.layout.item_work_meizhi);
            recyclerViewWork.setAdapter(meiZhiWorkAdapter);
            meiZhiWorkAdapter.setOnItemClick(new MeiZhiWorkAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mCacheList.get(position).getUrl(), "妹纸在手，天下我有！");
                }
            });
            return true;
        }
        if (id == R.id.action_hor) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            recyclerViewWork.setLayoutManager(gridLayoutManager);
            meiZhiWorkAdapter = new MeiZhiWorkAdapter(mCacheList, R.layout.item_work_grid);
            recyclerViewWork.setAdapter(meiZhiWorkAdapter);
            meiZhiWorkAdapter.setOnItemClick(new MeiZhiWorkAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mCacheList.get(position).getUrl(), "妹纸在手，天下我有！");
                }
            });
            return true;
        }
        if (id == R.id.action_pubu) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerViewWork.setLayoutManager(staggeredGridLayoutManager);
            meiZhiAdapter = new MeiZhiAdapter(mCacheList, R.layout.item_work_grids);
            recyclerViewWork.setAdapter(meiZhiAdapter);
            meiZhiAdapter.setOnItemClick(new MeiZhiAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mCacheList.get(position).getUrl(), "妹纸在手，天下我有！");
                }
            });
            return true;
        }
        if (id == R.id.action_duobuju) {
            recyclerViewWork.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            meiZhiDuoAdapter = new MeiZhiDuoAdapter(mCacheList, R.layout.item_work_meizhi);
            recyclerViewWork.setAdapter(meiZhiDuoAdapter);
            meiZhiDuoAdapter.setOnItemClick(new MeiZhiDuoAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mCacheList.get(position).getUrl(), "妹纸在手，天下我有！");
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item_vlayout clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_meizhi) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_duanzi) {
            Intent intent = new Intent(this, MusicActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_dev) {
            Intent intent = new Intent(WorkSpaceActivity.this, MenuActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

        } else if (id == R.id.nav_setting) {
;           Intent intent = new Intent(this, SocketActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about) {//
            //测试线程池
//            new ThreadTest().setTest();

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



    protected void transitionTo(ImageView mImgView, TextView textView, String layoutId, String text) {
        Intent intent = new Intent(this, MeizhiItemActivity.class);
        intent.putExtra("IMAGE", layoutId);
        intent.putExtra("TEXTS", text);

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this,
                new Pair[]{Pair.create(mImgView, "testImg"),
                        Pair.create(textView, "testext")});

        startActivity(intent, options.toBundle());

    }


}
