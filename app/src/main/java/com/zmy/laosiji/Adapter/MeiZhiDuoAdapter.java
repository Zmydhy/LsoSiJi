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

public class MeiZhiDuoAdapter extends RecyclerView.Adapter<MeiZhiDuoAdapter.ViewHolder> {
    private OnItemClickListener mItemClickListener = null;
    private OnItemLongClickListener mLongClickListener = null;
    private List<MeizhiEntity.ResultsBean> mLists;
    private Context mContext;
    private int layoutId = 0;

    public MeiZhiDuoAdapter(List<MeizhiEntity.ResultsBean> list, int layoutId) {
        this.layoutId = layoutId;
        mLists = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case 0:
            case 1:
            case 2:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_meizhi, parent, false);
                break;
            case 3:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_work_meizhi2, parent, false);
                break;

            default:
                break;

        }
        mContext = parent.getContext();
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MeizhiEntity.ResultsBean mResulrBean = mLists.get(position);

        switch (holder.getItemViewType()) {
            case 0:
            case 1:
            case 2:
                Glide.with(mContext)
                        .load(mResulrBean.getUrl())
                        .placeholder(R.mipmap.error_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView);
                holder.mText.setText("得妹纸，得天下！");

                break;
            case 3:
                Glide.with(mContext)
                        .load(mLists.get(position-1).getUrl())
                        .placeholder(R.mipmap.error_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView1);
                Glide.with(mContext)
                        .load(mResulrBean.getUrl())
                        .placeholder(R.mipmap.error_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView);
                Glide.with(mContext)
                        .load(mLists.get(position+1).getUrl())
                        .placeholder(R.mipmap.error_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imageView3);
                holder.mText.setText("妹子三人组...");
                break;
            default:
                break;

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onItemClik(view,position);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position % 4;
    }


    @Override
    public int getItemCount() {
        return mLists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public ImageView imageView1;
        public ImageView imageView3;

        public TextView mText;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_work_meizhi);
            imageView1 = (ImageView) itemView.findViewById(R.id.item_work_meizhi1);
            imageView3 = (ImageView) itemView.findViewById(R.id.item_work_meizhi3);
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
