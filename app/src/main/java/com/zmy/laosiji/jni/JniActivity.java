package com.zmy.laosiji.jni;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;

public class JniActivity extends BaseActivity {

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_jni,"NDK开发");

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(JNI.stringFromJNI()+" , "+JNIT.stringFromTEST1());
    }
}
