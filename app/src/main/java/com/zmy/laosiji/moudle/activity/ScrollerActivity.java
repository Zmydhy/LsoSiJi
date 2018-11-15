package com.zmy.laosiji.moudle.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.widgets.LinearLayoutSubClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScrollerActivity extends BaseActivity {
    @BindView(R.id.btn_start_animation)
    Button mBtnStartAnimation;
    @BindView(R.id.btn_reset)
    Button mBtnReset;
    @BindView(R.id.img_gril)
    TextView mImgGril;

    @BindView(R.id.lin_btn)
    LinearLayoutSubClass mLinearLayoutSubClass;
    int x = 0 ;
    int y= 0;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_aac, "AAC");
    }

    @OnClick({R.id.btn_start_animation, R.id.btn_reset,R.id.btn_scroller})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start_animation:
                Toast.makeText(ScrollerActivity.this,"点击test",Toast.LENGTH_SHORT).show();
                mImgGril.scrollBy(20,10);
                 x  =(int) mImgGril.getX();
                 y  =(int) mImgGril.getY();
                break;
            case R.id.btn_reset:
                mImgGril.scrollTo(x,y);
                break;

            case R.id.btn_scroller:
                mLinearLayoutSubClass.beginScroll();
                break;
                default:
                    break;

        }
    }
}
