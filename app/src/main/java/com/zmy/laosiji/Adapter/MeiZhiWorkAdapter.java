package com.zmy.laosiji.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zmy.laosiji.Entity.MeizhiEntity;
import com.zmy.laosiji.R;

import java.util.List;

/**
 * Created by Michael on 2017/11/30.
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
public class MeiZhiWorkAdapter extends RecyclerView.Adapter<MeiZhiWorkAdapter.ViewHolder> {
    private OnItemClickListener mItemClickListener = null;
    private OnItemLongClickListener mLongClickListener = null ;
    private List<MeizhiEntity.ResultsBean> mLists;
    private Context mContext;
    private  int layoutId = 0;

    public MeiZhiWorkAdapter(List<MeizhiEntity.ResultsBean> list ,int layoutId) {
        this.layoutId = layoutId;
        mLists = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
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
        holder.mText.setText("得妹纸，得天下！");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClik(view,position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_work_meizhi);
            mText = (TextView) itemView.findViewById(R.id.item_work_text);
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
