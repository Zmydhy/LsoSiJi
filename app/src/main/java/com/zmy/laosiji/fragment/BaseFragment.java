package com.zmy.laosiji.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zmy.laosiji.R;

import java.io.Serializable;

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

public abstract class BaseFragment extends Fragment {
    TextView customTitle;
    FrameLayout viewFragment;
    protected Context mContext;//全局context
    private LayoutInflater mInflater;
    private View rootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // Fragment默认是没有Menu true就是可以用
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(rootView != null){
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (null != parent) {
                parent.removeView(rootView);
            }
        }else{
            rootView = inflater.inflate(R.layout.fragment_base_custom, container, false);
            customTitle = (TextView) rootView.findViewById(R.id.custom_title);
            viewFragment = (FrameLayout) rootView.findViewById(R.id.view_fragment);
            mInflater = inflater;
        }
        initView();
        return rootView;
    }
    /**
     * 新建一个视图，并设置
     *
     * @param layoutId
     * @return
     */
    protected  View   setView(String title ,int layoutId ) {
        customTitle.setText(title);
        View view =mInflater.inflate(layoutId,null);
        viewFragment.addView(view);
        return  view;
    }
    protected  View   setView(String title ,View  chilView ) {
        customTitle.setText(title);
        if (viewFragment.getChildCount() > 0) {
            viewFragment.removeAllViews();
        }
        viewFragment.addView(chilView);
        return  chilView;
    }

    /**
     * 用于子类View初始化以及数据的操作
     */
    public abstract void initView();

    /**
     * 启动一个activity不带参数
     *
     * @param pClass 要启动的activity
     */
    protected void startActivity(Class<?> pClass) {
        Intent intent = new Intent(getActivity(), pClass);
        getActivity().startActivity(intent);

    }

    /**
     * 启动一个activity 带有Serializable参数
     *
     * @param pClass 要启动的activity
     * @param name   键名
     * @param value  值名 这里通常是一个实体类
     */
    protected void startActivity(Class<?> pClass, String name, Serializable value) {
        Intent intent = new Intent(getActivity(), pClass);
        intent.putExtra(name, value);
        startActivity(intent);
    }
}
