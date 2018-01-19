package com.zmy.laosiji.moudle.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding2.view.RxView;
import com.zmy.laosiji.R;
import com.zmy.laosiji.base.BaseActivity;
import com.zmy.laosiji.rxhttp.HttpOnNextListener;
import com.zmy.laosiji.rxhttp.RxBus;
import com.zmy.laosiji.rxhttp.RxClick;
import com.zmy.laosiji.utils.ConstantUtil;
import com.zmy.laosiji.utils.animatorutils.AnimatorPath;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * 这个类 有两个功能
 * 1、  加入了权限请求
 * 2、 运用rxjava实现的防止二次点击
 * 3、添加动画库的使用
 * 4、 RxBus的注册和解绑
 */

@RuntimePermissions
public class PermissionActivity extends BaseActivity {

    @BindView(R.id.btn_angdou)
    Button btnAngdou;
    @BindView(R.id.tv_persmion)
    TextView tvPersmion;
    @BindView(R.id.img_permsion)
    ImageView imgPermsion;
    @Override
    protected void setContentView(Bundle savedInstanceState) {
        setContentLayout(R.layout.activity_permission);
        RxBus.getRxBus().subscribeOn(String.class, new HttpOnNextListener<String>() {
            @Override
            public void onStart(Disposable d) {
                super.onStart(d);
            }

            @Override
            public void onNext(String s) {
                tvPersmion.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ConstantUtil.log_e("onError");
            }

            @Override
            public void onComplete() {
                super.onComplete();

                ConstantUtil.log_e("onComplete");
            }
        });

                /*
         * 1. 此处采用了RxBinding：RxView.clicks(button) = 对控件点击进行监听，需要引入依赖：compile 'com.jakewharton.rxbinding2:rxbinding:2.0.0'
         * 2. 传入Button控件，点击时，都会发送数据事件（但由于使用了throttleFirst（）操作符，所以只会发送该段时间内的第1次点击事件）
         **/

        RxClick.setOnClick(btnAngdou, new

                HttpOnNextListener() {
                    @Override
                    public void onNext(Object o) {
                        Log.d("TAG", "发送了网络请求");
                        RxBus.getRxBus().post("caodan1");
                        RxBus.getRxBus().post("caodan2");
                        RxBus.getRxBus().post("caodan3");
                        AnimatorPath animatorPath = new AnimatorPath();
                        animatorPath.moveto(0, 400);
                        animatorPath.cubto(200, 0, 600, 800, 800, 400);
                        animatorPath.lineto(0, 400);
                        animatorPath.startAnimator(imgPermsion);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        RxView.clicks(btnAngdou).unsubscribeOn(AndroidSchedulers.mainThread());//防止内存泄漏
        //解绑
        RxBus.getRxBus().unSubscribeOn();
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
