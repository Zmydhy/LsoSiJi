package com.zmy.laosiji.moudle.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.widgets.SunLightVIew;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SunLightActivity extends BaseActivity {

    @BindView(R.id.sunlightview)
    SunLightVIew sunlightview;


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_sunlight,"日食");
    }

    @Override
    protected void onStop() {
        super.onStop();
        sunlightview.stopAnimator();
    }
}
