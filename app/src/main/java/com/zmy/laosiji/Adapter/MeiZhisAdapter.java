package com.zmy.laosiji.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zmy.laosiji.Entity.MeizhiEntity;
import com.zmy.laosiji.R;

import java.util.List;

/**
 * Created by Michael on 2017/11/21.
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 */

public class MeiZhisAdapter extends RecyclerView.Adapter<MeiZhisAdapter.ViewHolder> {
    private OnItemClickListener mItemClickListener = null;
    private OnItemLongClickListener mLongClickListener = null ;
    private List<MeizhiEntity.ResultsBean> mLists;
    private List<Boolean> mCheacks;
    private Context mContext;

    public MeiZhisAdapter(List<MeizhiEntity.ResultsBean> list ,List<Boolean> mCheacks) {
        mLists = list;
        this.mCheacks = mCheacks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meizhi, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MeizhiEntity.ResultsBean mResulrBean = mLists.get(position);
        Glide.with(mContext)
                .load(mResulrBean.getUrl())
                .placeholder(R.mipmap.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
        if(mCheacks.size() > 0){
            if (mCheacks.get(position)) {
                holder.views.setVisibility(View.VISIBLE);
            } else {
                holder.views.setVisibility(View.GONE);
            }
        }
        if(mItemClickListener != null){
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClik(holder.imageView,position);
                    if(mCheacks.get(position)){
                        mCheacks.set(position,false);
                        holder.views.setVisibility(View.GONE);
                    }else{
                        mCheacks.set(position,true);
                        holder.views.setVisibility(View.VISIBLE);
                    }
                }
            });
        }else{
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mCheacks.get(position)){
                        mCheacks.set(position,false);
                        holder.views.setVisibility(View.GONE);
                    }else{
                        mCheacks.set(position,true);
                        holder.views.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View views;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_item_meizhi);
            views = itemView.findViewById(R.id.image_item_ischeck);
        }
    }

    public void setOnItemClick(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    public void setOnLongItemClick(OnItemLongClickListener mItemClickListener) {
        this.mLongClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClik(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onLongItemClick(View view, int position);
    }


}
