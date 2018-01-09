package com.zmy.laosiji.moudle.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.rxhttp.HttpOnNextListener;
import com.zmy.laosiji.rxhttp.RxBus;
import com.zmy.laosiji.rxhttp.RxClick;
import com.zmy.laosiji.utils.ConstantUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 这个类 有两个功能
 * 1、  加入了权限请求
 * 2、 运用rxjava实现的防止二次点击
 */

@RuntimePermissions
public class PermissionActivity extends BaseActivity {

    @BindView(R.id.btn_angdou)
    Button btnAngdou;
    @BindView(R.id.tv_persmion)
    TextView tvPersmion;

    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_permission);
         RxBus.getRxBus().toObservable(String.class).subscribe(new Observer<String>() {
             @Override
             public void onSubscribe(Disposable d) {
                 ConstantUtil.log_e("onSubscribe");
             }

             @Override
             public void onNext(String s) {
                 ConstantUtil.log_e(s);
                 tvPersmion.setText(s);
             }

             @Override
             public void onError(Throwable e) {
                 ConstantUtil.log_e("onError");
             }

             @Override
             public void onComplete() {
                 ConstantUtil.log_e("onComplete");
             }
         });
                /*
         * 1. 此处采用了RxBinding：RxView.clicks(button) = 对控件点击进行监听，需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入Button控件，点击时，都会发送数据事件（但由于使用了throttleFirst（）操作符，所以只会发送该段时间内的第1次点击事件）
         **/

        RxClick.setOnClick(btnAngdou, new HttpOnNextListener() {
            @Override
            public void onNext(Object o) {
                Log.d("TAG", "发送了网络请求");
                RxBus.getRxBus().post("caodan1");
                RxBus.getRxBus().post("caodan2");
                RxBus.getRxBus().post("caodan3");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TAG", "对Error事件作出响应" + e.toString());
            }

            @Override
            public void onComplete() {
                Log.d("TAG", "对Complete事件作出响应");
            }

        });
    }

    @OnClick(R.id.btn_getper)
    public void onViewClicked() {
        //申请单个权限
        PermissionActivityPermissionsDispatcher.getCarmerWithCheck(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void getCarmer() {
        ConstantUtil.toast("申请相机权限");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

//    @OnShowRationale(Manifest.permission.CAMERA)
//    void showCarmer(final PermissionRequest request) {
//
//        new AlertDialog.Builder(this)
//                .setMessage("相机权限，下一步将继续请求权限")
//                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        request.proceed();//继续执行请求
//                    }
//                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                request.cancel();//取消执行请求
//            }
//        }).show();
//    }
}
