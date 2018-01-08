package com.zmy.laosiji.moudle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zmy.laosiji.R;
import com.zmy.laosiji.widgets.SunLightVIew;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SunLightActivity extends AppCompatActivity {

    @BindView(R.id.sunlightview)
    SunLightVIew sunlightview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sunlight);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        sunlightview.stopAnimator();
    }
}
