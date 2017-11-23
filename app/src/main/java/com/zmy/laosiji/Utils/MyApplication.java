package com.zmy.laosiji.Utils;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by Michael on 2017/11/23.
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

public class MyApplication extends android.app.Application{
    private static MyApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        SkinCompatManager.withoutActivity(this)                 // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())    // material design 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())        // CardView v7 控件换肤初始化[可选]
                .loadSkin();
    }
    public static synchronized MyApplication getInstance() {
        return  sInstance;
    }
}
