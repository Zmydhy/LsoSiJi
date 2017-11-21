package com.zmy.laosiji.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zmy.laosiji.Entity.MeizhiEntity;
import com.zmy.laosiji.R;
import com.zmy.laosiji.Utils.AbsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 2017/8/9.
 */

public class MeiZhiAdapter extends AbsRecyclerViewAdapter<MeizhiEntity.ResultsBean> {
    private List<Integer> mHeights;
    private List<Boolean> mCheckList;
    public MeiZhiAdapter(RecyclerView recyclerView) {
        super(recyclerView);
        mHeights = new ArrayList<>();
        mCheckList = new ArrayList<>();

    }

    @Override
    public ClickableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        bindContext(parent.getContext());
        return new ItemViewHolder(
                LayoutInflater.from(getContext()).inflate(R.layout.item_meizhi, parent, false));

    }

    @Override
    public void onBindViewHolder(ClickableViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            MeizhiEntity.ResultsBean dailySectionsInfo = mDataSources.get(position);

//            // 随机高度, 模拟瀑布效果.
//            if (mHeights.size() <= position) {
//                mHeights.add((int) (300 + Math.random() * 700));
//            }
//
//            ViewGroup.LayoutParams lp = ((ItemViewHolder) holder).itemView.getLayoutParams();
//            lp.height = mHeights.get(position);
//
//            ((ItemViewHolder) holder).itemView.setLayoutParams(lp);
            //缩略图
//            DrawableRequestBuilder<String> thumbnailRequest = Glide
//                    .with( getContext() )
//                    .load( dailySectionsInfo.getUrl());
            Glide.with(getContext())
                    .load(dailySectionsInfo.getUrl())
                    .placeholder(R.mipmap.error_image)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(itemViewHolder.mImageView);
        }

    }

    public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

        public ImageView mImageView;
        public  View  isCheck;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = $(R.id.img_item_meizhi);
            isCheck = $(R.id.image_item_ischeck);
        }
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener listener) {
        super.setOnItemClickListener(listener);
    }

    @Override
    public void addOnScrollListener(RecyclerView.OnScrollListener listener) {
        super.addOnScrollListener(listener);
    }
}
