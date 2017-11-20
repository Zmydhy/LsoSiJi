package com.zmy.laosiji.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zmy.laosiji.Adapter.MeiZhiAdapter;
import com.zmy.laosiji.Entity.MeizhiEntity;
import com.zmy.laosiji.R;
import com.zmy.laosiji.Utils.AbsRecyclerViewAdapter;
import com.zmy.laosiji.Utils.RetrofitFactory;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MeiZhiAdapter meiZhiAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mToolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        meiZhiAdapter = new MeiZhiAdapter(mRecyclerView);
        init();
    }

    private void init() {
        Observable<MeizhiEntity> meizhiCall = RetrofitFactory.getInstance().getApi().getMeiZhi("福利", 50, 1);
        meizhiCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MeizhiEntity>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull MeizhiEntity meizhiEntity) {
                meiZhiAdapter.setDataSources(meizhiEntity.getResults());
                mRecyclerView.setAdapter(meiZhiAdapter);

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });



        meiZhiAdapter.setOnItemClickListener(new AbsRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, final AbsRecyclerViewAdapter.ClickableViewHolder holder) {
                //设置点击动画
                holder.itemView.animate()
                        .translationZ(15f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                holder.itemView.animate()
                                        .translationZ(1f)
                                        .setDuration(500)
                                        .start();
                            }
                        }).start();

            }
        });
//        meiZhiAdapter.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                Log.i("TAG","newState:"+newState );
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                Log.i("TAG","dx:"+dx +",dy:"+dy);
//            }
//        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("TAG","newState:"+newState );
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("TAG","dx:"+dx +",dy:"+dy);
            }
        });

    }


//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.right_in_back, R.anim.right_out_back);
//    }
//
//    @Override
//    public void startActivity(Intent intent) {
//        super.startActivity(intent);
//        overridePendingTransition(R.anim.right_in, R.anim.right_out);
//    }
}
