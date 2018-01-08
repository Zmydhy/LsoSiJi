package com.zmy.laosiji.moudle.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zmy.laosiji.AIDL.IMyAidlInterface;
import com.zmy.laosiji.moudle.service.MyService;
import com.zmy.laosiji.R;

public class AIDLActivity extends AppCompatActivity {
    private IMyAidlInterface iMyAidlInterface;
    private Intent intent;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service); //获取AIDL的接口实现引用
        }
        @Override
        public void onServiceDisconnected(ComponentName className) {
            iMyAidlInterface = null;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
         intent = new Intent();
        intent.setClass(this, MyService.class);
        bindService(intent, connection, Service.BIND_AUTO_CREATE); // 绑定服务
        findViewById(R.id.btn_adil).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Toast.makeText(AIDLActivity.this, iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
                }
                catch (RemoteException e)
                {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(intent);
    }
}
