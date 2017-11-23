package com.zmy.laosiji.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 初始屏幕的正确方式
 * 定义drawable文件，在themem中设置
 * 无需再初始化布局
 * 仿youtube
 * 节省时间
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, WorkSpaceActivity.class);
        startActivity(intent);
        finish();
    }
}
