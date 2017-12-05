package com.zmy.laosiji.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zmy.laosiji.R;
import com.zmy.laosiji.Utils.ConstantUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Michael on 2017/11/27.
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

public class ScrollActivity extends BaseActivity {
    @BindView(R.id.btn_scroll_1)
    Button btnScroll1;
    @BindView(R.id.btn_scroll_2)
    Button btnScroll2;
    @BindView(R.id.btn_scroll_3)
    Button btnScroll3;
    @BindView(R.id.btn_scroll_4)
    Button btnScroll4;
    @BindView(R.id.lin_scroll)
    LinearLayout linScroll;
    @BindView(R.id.view_scroll_1)
    View viewScroll1;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_scroll);
    }


    @OnClick({R.id.btn_scroll_1, R.id.btn_scroll_2, R.id.btn_scroll_3, R.id.btn_scroll_4, R.id.view_scroll_1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_scroll_1:
                ConstantUtil.toast("点击滑动");
                viewScroll1.scrollTo(800,1000);
                break;
            case R.id.btn_scroll_2:
                viewScroll1.scrollBy(800,800);
                break;
            case R.id.btn_scroll_3:
                break;
            case R.id.btn_scroll_4:
                break;
            case R.id.view_scroll_1:
                break;
                default:
                    break;
        }
    }
}
