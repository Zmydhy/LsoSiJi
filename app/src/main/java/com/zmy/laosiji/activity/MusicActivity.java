package com.zmy.laosiji.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.utils.ConstantUtil;
import com.zmy.laosiji.service.MediaService;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MusicActivity extends BaseActivity {


    @BindView(R.id.play_music)
    Button playMusic;
    @BindView(R.id.pause_music)
    Button pauseMusic;
    @BindView(R.id.precious_music)
    Button preciousMusic;
    @BindView(R.id.next_music)
    Button nextMusic;
    @BindView(R.id.seekbar_music)
    SeekBar seekbarMusic;
    @BindView(R.id.text1_music)
    TextView text1Music;
    @BindView(R.id.text1_musics)
    TextView text1Musics;
    @BindView(R.id.bind_start)
    Button bindStart;
    @BindView(R.id.start_start)
    Button startStart;
    @BindView(R.id.start_stop)
    Button startStop;

    private MediaService.MyBinder mMyBinder;
    //“绑定”服务的intent
    Intent MediaServiceIntent;
    Intent intents;

    private Handler mHandler = new Handler();
    //进度条下面的当前进度文字，将毫秒化为m:ss格式
    private SimpleDateFormat time = new SimpleDateFormat("m:ss");

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyBinder = (MediaService.MyBinder) service;
            ConstantUtil.log_e("Service与Activity已连接");
            seekbarMusic.setMax(mMyBinder.getProgress());
            text1Musics.setText(time.format(mMyBinder.getProgress()) + "s");
            mHandler.post(mRunnable);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_music);
        seekbarMusic.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //这里很重要，如果不判断是否来自用户操作进度条，会不断执行下面语句块里面的逻辑，然后就会卡顿卡顿
                if (fromUser) {
                    mMyBinder.seekToPositon(seekBar.getProgress());
                    mMyBinder.getMedia().seekTo(seekBar.getProgress());
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    /**
     * 更新ui的runnable
     */
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            seekbarMusic.setProgress(mMyBinder.getPlayPosition());
            text1Music.setText(time.format(mMyBinder.getPlayPosition()) + "s");
            mHandler.postDelayed(mRunnable, 1000);
        }
    };

    @OnClick({R.id.play_music, R.id.pause_music, R.id.precious_music, R.id.next_music, R.id.bind_start, R.id.start_start, R.id.start_stop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.play_music:
                mMyBinder.playMusic();
                text1Musics.setText(time.format(mMyBinder.getProgress()) + "s");
                break;
            case R.id.pause_music:
                mMyBinder.pauseMusic();
                break;
            case R.id.next_music:
                mMyBinder.nextMusic();
                text1Musics.setText(time.format(mMyBinder.getProgress()) + "s");
                break;
            case R.id.precious_music:
                mMyBinder.preciousMusic();
                text1Musics.setText(time.format(mMyBinder.getProgress()) + "s");
                break;
            case R.id.bind_start:
                MediaServiceIntent = new Intent(this, MediaService.class);

                //判断权限够不够，不够就给
                if (ContextCompat.checkSelfPermission(MusicActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MusicActivity.this, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 1);
                } else {
                    //够了绑定播放音乐的服务
                    bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
                }
                break;
            case R.id.start_start:


                break;
            case R.id.start_stop:
                stopService(intents);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMyBinder.closeMedia();
        unbindService(mServiceConnection);
        //我们的handler发送是定时1000s发送的，如果不关闭，MediaPlayer release掉了还在获取getCurrentPosition就会爆IllegalStateException错误
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
