package com.zmy.laosiji.Activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.zmy.laosiji.R;

import java.util.LinkedList;
import java.util.List;

/**
 * 引入阿里的vlayut布局
 */
public class ByclerViewActivity extends BaseActivity {

    private RecyclerView recycler;
    private static final boolean SIGNLE_LAYOUT = true;
    private static final boolean ONEPLUS_LAYOUT = true;
    private static final boolean OTHERS_LAYOUT = true;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_bycler_view);
        recycler = (RecyclerView) findViewById(R.id.b_recycler_view);

        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recycler.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 20);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, true);

        recycler.setAdapter(delegateAdapter);

        final List<DelegateAdapter.Adapter> adapters = new LinkedList<>();
        if(SIGNLE_LAYOUT){
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            layoutHelper.setBgColor(Color.rgb(135, 225, 90));
            layoutHelper.setAspectRatio(4);
            layoutHelper.setMargin(10, 20, 10, 20);
            layoutHelper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }
        if (ONEPLUS_LAYOUT) {
            GridLayoutHelper helper = new GridLayoutHelper(3);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 3));
        }
        if(SIGNLE_LAYOUT){
            SingleLayoutHelper layoutHelper = new SingleLayoutHelper();
            layoutHelper.setBgColor(Color.rgb(135, 225, 90));
            layoutHelper.setAspectRatio(4);
            layoutHelper.setMargin(10, 20, 10, 20);
            layoutHelper.setPadding(10, 10, 10, 10);
            adapters.add(new SubAdapter(this, layoutHelper, 1, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100)));
        }
        if (ONEPLUS_LAYOUT) {
            GridLayoutHelper helper = new GridLayoutHelper(2);
            helper.setMargin(0, 10, 0, 10);
            adapters.add(new SubAdapter(this, helper, 24));
        }
        delegateAdapter.setAdapters(adapters);
    }

    public class SubAdapter extends DelegateAdapter.Adapter<MainViewHolder>{
        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public int getItemViewType(int position) {
            Log.e("VLAYOUT_TAG","position:"+position);
            return super.getItemViewType(position);
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_vlayout, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }
        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
//            Log.e("VLAYOUT_TAG","position:"+position+",offsetTotal:"+offsetTotal);
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    public class SubAdapter1 extends DelegateAdapter.Adapter<MainViewHolder>{
        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter1(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter1(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_vlayout1, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }
        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            Log.e("VLAYOUT_TAG","position:"+position+",offsetTotal:"+offsetTotal);
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    public class SubAdapter2 extends DelegateAdapter.Adapter<MainViewHolder>{
        private Context mContext;

        private LayoutHelper mLayoutHelper;


        private VirtualLayoutManager.LayoutParams mLayoutParams;
        private int mCount = 0;


        public SubAdapter2(Context context, LayoutHelper layoutHelper, int count) {
            this(context, layoutHelper, count, new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 300));
        }

        public SubAdapter2(Context context, LayoutHelper layoutHelper, int count, @NonNull VirtualLayoutManager.LayoutParams layoutParams) {
            this.mContext = context;
            this.mLayoutHelper = layoutHelper;
            this.mCount = count;
            this.mLayoutParams = layoutParams;
        }

        @Override
        public LayoutHelper onCreateLayoutHelper() {
            return mLayoutHelper;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new MainViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_vlayout2, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            // only vertical
            holder.itemView.setLayoutParams(
                    new VirtualLayoutManager.LayoutParams(mLayoutParams));
        }
        @Override
        protected void onBindViewHolderWithOffset(MainViewHolder holder, int position, int offsetTotal) {
            Log.e("VLAYOUT_TAG","position:"+position+",offsetTotal:"+offsetTotal);
            ((TextView) holder.itemView.findViewById(R.id.title)).setText(Integer.toString(offsetTotal));
        }

        @Override
        public int getItemCount() {
            return mCount;
        }
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        public MainViewHolder(View itemView) {
            super(itemView);
        }


    }
}
