package com.zmy.laosiji.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.zmy.laosiji.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MenuActivity extends AppCompatActivity {


    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.text2)
    TextView text2;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.text4)
    TextView text4;
    @BindView(R.id.text5)
    TextView text5;
    @BindView(R.id.text6)
    TextView text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.text1, R.id.text2, R.id.text3, R.id.text4, R.id.text5, R.id.text6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text1:
                startActivitys(CustomViewActivity.class);
                break;
            case R.id.text2:
                startActivitys(TranslateActivity.class);
                break;
            case R.id.text3:
                startActivitys(ThemeNightActivity.class);
                break;
            case R.id.text4:
                startActivitys(ByclerViewActivity.class);
                break;
            case R.id.text5:
                startActivitys(MatialDesignActivity.class);
                break;
            case R.id.text6:
                break;
            default:
                break;
        }
    }

    private void startActivitys(Class<?> sclass) {
        Intent  intent = new Intent(this,sclass);
        startActivity(intent);
    }


}
