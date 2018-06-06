package com.zmy.laosiji.moudle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zmy.laosiji.R;
import com.zmy.laosiji.room.TestEntity;
import com.zmy.laosiji.utils.gilde.GildeUtil;

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
public class ItemkAdapter extends RecyclerView.Adapter<ItemkAdapter.ViewHolder> {
    private OnItemClickListener mItemClickListener = null;
    private OnItemLongClickListener mLongClickListener = null ;
    private List<TestEntity> mLists;
    private Context mContext;

    public ItemkAdapter(List<TestEntity> list ) {
        mLists = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle, parent, false);
        mContext = parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.error_image)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        GildeUtil.setImageViewAuto(mLists.get(position).getUrl(), holder.imageView, options);
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
            imageView = (ImageView) itemView.findViewById(R.id.img_meizhi_s);
            mText = (TextView) itemView.findViewById(R.id.tv_meizhi_s);
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
