package com.zmy.laosiji.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.zmy.laosiji.R;

import skin.support.SkinCompatManager;
import skin.support.utils.SkinPreference;

public class ThemeNightActivity extends AppCompatActivity {
    Button mbut;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_night);
        mbut = (Button) findViewById(R.id.btn_theme);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_theme);
        setSupportActionBar(mToolbar);
        mbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(SkinPreference.getInstance().getSkinName())) {
                    SkinCompatManager.getInstance().loadSkin("night.skin", null);
                } else {
                    SkinCompatManager.getInstance().restoreDefaultTheme();
                }
            }
        });
    }

}
