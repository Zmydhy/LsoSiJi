package com.zmy.laosiji.activity;

import android.os.Bundle;
import android.support.design.internal.SnackbarContentLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.adapter.MeiZhisAdapter;
import com.zmy.laosiji.entity.MeizhiEntity;
import com.zmy.laosiji.interfaces.HttpAPi;
import com.zmy.laosiji.interfaces.HttpOnNextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * recycle
 * 1 分页加载
 * 2 移动指定位置
 * 3 状态保存
 */
public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.behavior_demo_swipe_refresh)
    SwipeRefreshLayout behaviorDemoSwipeRefresh;
    private int PAGE = 1;
    private int COUNT = 10;
    private RecyclerView mRecyclerView;
    private MeiZhisAdapter meiZhiAdapter;
    private Toolbar mToolbar;
    private Snackbar mSnackBarRootView;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionButton mFloatBtn;
    private List<MeizhiEntity.ResultsBean> mLists = new ArrayList<>();
    /**
     * 目标项是否在最后一个可见项之后
     */
    private boolean mShouldScroll;
    /**
     * 记录目标项位置
     */
    private int mToPosition;
    private List<Boolean> mCheacks;

    /**
     * 应为base里已经有toolbar了，当activity中再次含有toolbar是使用settoolbatid设置
     * 同时隐藏base里的toolbar，所有settitle没有作用
     *
     * @param savedInstanceState
     */
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        setToolBarId(R.id.toolbar);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coorlayout_meizhi);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mFloatBtn = (FloatingActionButton) findViewById(R.id.fab_main);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);//设置排列反顺序
        mRecyclerView.setLayoutManager(linearLayoutManager);
        behaviorDemoSwipeRefresh.setOnRefreshListener(this);
    }

    private void initData() {
        HttpAPi.getMeiZhi("福利", 10, 1, new HttpOnNextListener<MeizhiEntity>(this) {
            @Override
            public void onNext(MeizhiEntity meizhiEntity) {
                for (int i = 0; i < meizhiEntity.getResults().size(); i++) {
                    mCheacks.add(false);
                    mLists.add(meizhiEntity.getResults().get(i));
                }
                meiZhiAdapter.notifyDataSetChanged();
            }
        });

        mCheacks = new ArrayList<>();
        meiZhiAdapter = new MeiZhisAdapter(mLists, mCheacks);
        mRecyclerView.setAdapter(meiZhiAdapter);
        meiZhiAdapter.setOnItemClick(new MeiZhisAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClik(View view, int position) {
                if (mCheacks.get(position)) {
                    showSnackBar(view, position, "取消选中");
                } else {
                    showSnackBar(view, position, "选中");
                }
            }
        });
        /**
         * 点击floatbutton置顶
         */
        mFloatBtn.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                smoothMoveToPosition(mRecyclerView, 0);
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()

        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("TAG", "newState:" + newState);

                if (mShouldScroll) {
                    mShouldScroll = false;
                    smoothMoveToPosition(mRecyclerView, mToPosition);
                }

                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                // 当不滑动时
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    // 获取最后一个完全显示的itemPosition
                    int lastItemPosition = manager.findLastCompletelyVisibleItemPosition();
                    int itemCount = manager.getItemCount();

                    // 判断是否滑动到了最后一个Item，并且是向左滑动
                    if (lastItemPosition == (itemCount - 1)) {
                        PAGE++;
                        // 加载更多
                        onLoadMore(PAGE);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.i("TAG", "dx:" + dx + ",dy:" + dy);
            }
        });

    }

    public void showSnackBar(View view, int position, String ischeck) {
        mSnackBarRootView = Snackbar.make(mCoordinatorLayout, ischeck + "第" + (position + 1) + "item", Snackbar.LENGTH_INDEFINITE);
        mSnackBarRootView.setDuration(1000);
        changeSnackBarBackgroundColor(mSnackBarRootView);
        changeSnackBarMessageViewTextColor(mSnackBarRootView);
        mSnackBarRootView.show();
    }

    private void changeSnackBarBackgroundColor(Snackbar snackbar) {
        View view = snackbar.getView();
        view.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
    }

    private void changeSnackBarMessageViewTextColor(Snackbar snackbar) {
        ViewGroup viewGroup = (ViewGroup) snackbar.getView();
        SnackbarContentLayout contentLayout = (SnackbarContentLayout) viewGroup.getChildAt(0);
        TextView textView = (TextView) contentLayout.getChildAt(0);
        textView.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
    }

    /**
     * 加载更多
     */
    private void onLoadMore(int page) {
        HttpAPi.getMeiZhi("福利", COUNT, PAGE, new HttpOnNextListener<MeizhiEntity>(this) {
            @Override
            public void onNext(MeizhiEntity meizhiEntity) {
                behaviorDemoSwipeRefresh.setRefreshing(false);
                for (int i = 0; i < meizhiEntity.getResults().size(); i++) {
                    mCheacks.add(false);
                    mLists.add(meizhiEntity.getResults().get(i));
                }
                meiZhiAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 滑动到指定位置
     *
     * @param mRecyclerView
     * @param position
     */
    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        if (position < firstItem) {
            // 如果跳转位置在第一个可见位置之前，就smoothScrollToPosition可以直接跳转
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            // 跳转位置在第一个可见项之后，最后一个可见项之前
            // smoothScrollToPosition根本不会动，此时调用smoothScrollBy来滑动到指定位置
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);
            }
        } else {
            // 如果要跳转的位置在最后可见项之后，则先调用smoothScrollToPosition将要跳转的位置滚动到可见位置
            // 再通过onScrollStateChanged控制再次调用smoothMoveToPosition，执行上一个判断中的方法
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;
        }
    }


    @Override
    public void onRefresh() {
        onLoadMore(1);
    }
}
