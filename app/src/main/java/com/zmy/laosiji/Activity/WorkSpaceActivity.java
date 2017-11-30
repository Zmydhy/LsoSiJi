package com.zmy.laosiji.Activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.zmy.laosiji.Adapter.MeiZhiAdapter;
import com.zmy.laosiji.Adapter.MeiZhiDuoAdapter;
import com.zmy.laosiji.Adapter.MeiZhiWorkAdapter;
import com.zmy.laosiji.Entity.MeizhiEntity;
import com.zmy.laosiji.R;
import com.zmy.laosiji.Utils.RetrofitFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    private int PAGE = 1;
    private final int COUNT = 50;
    private List<MeizhiEntity.ResultsBean> mLists = new ArrayList<>();
    private MeiZhiWorkAdapter meiZhiWorkAdapter;
    private MeiZhiAdapter meiZhiAdapter;
    private MeiZhiDuoAdapter meiZhiDuoAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_work_space);
        ButterKnife.bind(this);
        toolbarWork.setTitle("带你装逼老司机");
        setSupportActionBar(toolbarWork);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbarWork, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navView.setNavigationItemSelectedListener(this);
        initDatas();
    }

    private void initDatas() {
        recyclerViewWork.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final Observable<MeizhiEntity> meizhiCall = RetrofitFactory.getInstance().getApi().getMeiZhi("福利", COUNT, PAGE);
        meizhiCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MeizhiEntity>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull MeizhiEntity meizhiEntity) {
                mLists = meizhiEntity.getResults();
                meiZhiWorkAdapter = new MeiZhiWorkAdapter(mLists, R.layout.item_work_meizhi);
                recyclerViewWork.setAdapter(meiZhiWorkAdapter);
                meiZhiWorkAdapter.setOnItemClick(new MeiZhiWorkAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClik(View view, int position) {
                        transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mLists.get(position).getUrl(), "妹纸在手，天下我有！");
                    }
                });
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


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
        // Handle action bar item_vlayout clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_ver) {
            recyclerViewWork.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            meiZhiWorkAdapter = new MeiZhiWorkAdapter(mLists, R.layout.item_work_meizhi);
            recyclerViewWork.setAdapter(meiZhiWorkAdapter);
            meiZhiWorkAdapter.setOnItemClick(new MeiZhiWorkAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mLists.get(position).getUrl(), "妹纸在手，天下我有！");
                }
            });
            return true;
        }
        if (id == R.id.action_hor) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
//            gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
            recyclerViewWork.setLayoutManager(gridLayoutManager);
            meiZhiWorkAdapter = new MeiZhiWorkAdapter(mLists, R.layout.item_work_grid);
            recyclerViewWork.setAdapter(meiZhiWorkAdapter);
            meiZhiWorkAdapter.setOnItemClick(new MeiZhiWorkAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mLists.get(position).getUrl(), "妹纸在手，天下我有！");
                }
            });
            return true;
        }
        if (id == R.id.action_pubu) {
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            recyclerViewWork.setLayoutManager(staggeredGridLayoutManager);
            meiZhiAdapter = new MeiZhiAdapter(mLists, R.layout.item_work_grids);
            recyclerViewWork.setAdapter(meiZhiAdapter);
            meiZhiAdapter.setOnItemClick(new MeiZhiAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mLists.get(position).getUrl(), "妹纸在手，天下我有！");
                }
            });
            return true;
        }
        if (id == R.id.action_duobuju) {
            recyclerViewWork.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            meiZhiDuoAdapter = new MeiZhiDuoAdapter(mLists, R.layout.item_work_meizhi);
            recyclerViewWork.setAdapter(meiZhiDuoAdapter);
            meiZhiDuoAdapter.setOnItemClick(new MeiZhiDuoAdapter.OnItemClickListener() {
                @Override
                public void onItemClik(View view, int position) {
                    transitionTo((ImageView) view.findViewById(R.id.item_work_meizhi), (TextView) view.findViewById(R.id.item_work_text), mLists.get(position).getUrl(), "妹纸在手，天下我有！");
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


        } else if (id == R.id.nav_dev) {
            Intent intent = new Intent(WorkSpaceActivity.this, MenuActivity.class);
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
            ;

        } else if (id == R.id.nav_setting) {


        } else if (id == R.id.nav_about) {

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
//        overridePendingTransition(0, 0);

    }

}
