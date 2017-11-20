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

/**
 * Created by Michael on 2017/8/9.
 */

public class MeiZhiAdapter extends AbsRecyclerViewAdapter<MeizhiEntity.ResultsBean> {
    public MeiZhiAdapter(RecyclerView recyclerView) {
        super(recyclerView);
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

            Glide.with(getContext())
                    .load(dailySectionsInfo.getUrl())
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(itemViewHolder.mImageView);
        }

    }

    public class ItemViewHolder extends AbsRecyclerViewAdapter.ClickableViewHolder {

        public ImageView mImageView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mImageView = $(R.id.img_item_meizhi);
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
