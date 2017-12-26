package com.zmy.laosiji.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.room.TestEntity;
import com.zmy.laosiji.utils.gilde.GildeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 2017/8/9.
 */

public class MeiZhiAdapter extends RecyclerView.Adapter<MeiZhiAdapter.ViewHolders> {
    private OnItemClickListener mItemClickListener = null;
    private OnItemLongClickListener mLongClickListener = null;
    private List<TestEntity> mLists;
    private Context mContext;
    private int layoutId = 0;
    private List<Integer> mHeights;

    public MeiZhiAdapter(List<TestEntity> list, int layoutId) {
        this.layoutId = layoutId;
        mLists = list;
        mHeights = new ArrayList<>();
    }

    @Override
    public ViewHolders onCreateViewHolder(ViewGroup parent, int position) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        mContext = parent.getContext();
        return new ViewHolders(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolders holder, final int position) {
        // 随机高度, 模拟瀑布效果.
        if (mHeights.size() <= position) {
            mHeights.add((int) (300 + Math.random() * 700));
        }
        ViewGroup.LayoutParams lp =  holder.itemView.getLayoutParams();
        lp.height = mHeights.get(position);
        holder.itemView.setLayoutParams(lp);

        TestEntity mResulrBean = mLists.get(position);
        GildeUtil.setImageView(mResulrBean.getUrl(),holder.imageView);
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

    class ViewHolders extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView mText;
        private LinearLayout item_work_views ;

        public ViewHolders(View itemView) {
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