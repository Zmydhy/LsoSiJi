package com.zmy.mvpdemo.model;

/**
 * Created by Michael on 2017/9/13.
 */

public interface ILogin {
    void login(String username, String password, final OnLoginListener listener);

    interface OnLoginListener{
        void onLoginSuccess( LoginModel model);
        void onFailed();
    }
}
