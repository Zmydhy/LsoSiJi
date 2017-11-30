package com.zmy.laosiji.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zmy.laosiji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @BindView(R.id.tv_meizhi_item)
    TextView tvMeizhiItem;
    @BindView(R.id.img_meizhi_item)
    ImageView imgMeizhiItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_meizhi_item);
        ButterKnife.bind(this);
        String imageId = getIntent().getStringExtra("IMAGE");
        String text = getIntent().getStringExtra("TEXTS");
        Glide.with(this)
                .load(imageId)
                .placeholder(R.mipmap.error_image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgMeizhiItem);
        tvMeizhiItem.setText(text);
    }


    @OnClick(R.id.img_meizhi_item)
    public void onViewClicked() {
        //翻转过度
        supportFinishAfterTransition();
    }
}
