package com.zmy.laosiji.Utils;

import android.content.Context;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCardViewInflater;
import skin.support.design.app.SkinMaterialViewInflater;

/**
 * Created by Michael on 2017/8/15.
 */

public class Application extends android.app.Application {
    private Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        SkinCompatManager.withoutActivity(this)                 // 基础控件换肤初始化
                .addInflater(new SkinMaterialViewInflater())    // material design 控件换肤初始化[可选]
                .addInflater(new SkinCardViewInflater())        // CardView v7 控件换肤初始化[可选]
                .loadSkin();
    }


}
