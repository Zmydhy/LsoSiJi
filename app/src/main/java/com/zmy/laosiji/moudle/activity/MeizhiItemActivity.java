package com.zmy.laosiji.moudle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zmy.laosiji.R;
import com.zmy.laosiji.moudle.adapter.ItemkAdapter;
import com.zmy.laosiji.room.TestEntity;
import com.zmy.laosiji.utils.gilde.GildeUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zmy.laosiji.base.MyApplication.getContext;

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

public class MeizhiItemActivity extends AppCompatActivity {

    @BindView(R.id.item_recycleview_s)
    RecyclerView mRecyclerView;

    private int position;
    List<TestEntity> mTestEntityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_meizhi_item);
        ButterKnife.bind(this);
        mTestEntityList = (List<TestEntity>) getIntent().getSerializableExtra("Datas");
        position = getIntent().getIntExtra("POSITION", 0);

        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(llm);
        ItemkAdapter itemkAdapter = new ItemkAdapter(mTestEntityList);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        mRecyclerView.scrollToPosition(position);
        mRecyclerView.setAdapter(itemkAdapter);
        itemkAdapter.setOnItemClick(new ItemkAdapter.OnItemClickListener() {
            @Override
            public void onItemClik(View view, int position) {

                // TODO Auto-generated method stub
                Intent intent = new Intent();
                // 获取用户计算后的结果
                intent.putExtra("three", position); //将计算的值回传回去
                //通过intent对象返回结果，必须要调用一个setResult方法，
                //setResult(resultCode, data);第一个参数表示结果返回码，一般只要大于1就可以，但是
                setResult(2, intent);
                Glide.get(getContext()).clearMemory();
                System.gc();
                //翻转过度
                supportFinishAfterTransition();

            }
        });
        pagerSnapHelper.attachToRecyclerView(mRecyclerView);
    }

}
