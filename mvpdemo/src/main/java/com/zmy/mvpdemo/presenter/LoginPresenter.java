package com.zmy.mvpdemo.presenter;

import android.os.Handler;

import com.zmy.mvpdemo.model.ILogin;
import com.zmy.mvpdemo.model.LoginM;
import com.zmy.mvpdemo.model.LoginModel;
import com.zmy.mvpdemo.view.ILoginView;

/**
 * Created by Michael on 2017/9/13.
 */

public class LoginPresenter {
    private ILoginView loginView;
    private ILogin login;
    private Handler handler=new Handler();

    public LoginPresenter(ILoginView iLoginView){
        //构造器进行初始化对象的时候进行初始化数据使用的，
        this.loginView=iLoginView;
        login=new LoginM();
    }

    /**
     * 登陆
     */
    public  void login(){
        loginView.showLoading();//显示loading
        login.login(loginView.getUsername(), loginView.getPassword(), new ILogin.OnLoginListener() {
            @Override
            public void onLoginSuccess(final LoginModel model) {
                //登陆成功进行更新 ui
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.toMainActivity(model);
                        loginView.hideLoading();
                    }
                });
            }

            @Override
            public void onFailed() {

                //同样进行更新ui
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.showFailedError();
                        loginView.hideLoading();
                    }
                });
            }
        });
    }
    /**
     * 进行clear
     */
    public void clear(){
        loginView.clearUsername();
        loginView.clearPassword();
    }

}
