package com.zmy.mvpdemo.view;

import com.zmy.mvpdemo.model.LoginModel;

/**
 * Created by Michael on 2017/9/14.
 */

public interface ILoginView {
    String getUsername();//得到username
    String getPassword();//得到password
    void clearUsername();//清除username
    void clearPassword();//清除password
    void showLoading();//显示loading
    void hideLoading();//隐藏loading
    void toMainActivity(LoginModel model);//跳转到mainActivity
    void showFailedError();//显示错误信息
}
