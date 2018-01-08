package com.zmy.laosiji.moudle.activity;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.moudle.service.MediaService;
import com.zmy.laosiji.utils.ConstantUtil;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
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
        MusicActivityPermissionsDispatcher.getPermissonWithCheck(this);
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
        if(mMyBinder != null){
            mMyBinder.closeMedia();
        }
        unbindService(mServiceConnection);
        //我们的handler发送是定时1000s发送的，如果不关闭，MediaPlayer release掉了还在获取getCurrentPosition就会爆IllegalStateException错误
        mHandler.removeCallbacks(mRunnable);
    }



    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void getPermisson() {
        //够了绑定播放音乐的服务
        MediaServiceIntent = new Intent(this, MediaService.class);
        bindService(MediaServiceIntent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MusicActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
