package com.zmy.laosiji.Fragment;

import android.view.View;

import com.zmy.laosiji.CustomView.CircleVIew;
import com.zmy.laosiji.R;

/**
 * Created by Michael on 2017/11/20.
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

public class ViewFragment extends BaseFragment {
    private  CircleVIew fragmentCiecle;


    @Override
    public void initView() {
        View view = setView("自定义对号" , R.layout.fragment_ciecle_duihao);
        fragmentCiecle = (CircleVIew) view.findViewById(R.id.fragment_ciecle);
        fragmentCiecle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentCiecle.startAnimation();
            }
        });
    }


}
