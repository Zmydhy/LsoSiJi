package com.zmy.laosiji.rxhttp;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.zmy.laosiji.R;

import io.reactivex.disposables.Disposable;

/**
 * Created by Michael on 2017/12/21.
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　 ┣┓
 * 　　　　┃　　　　 ┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 */

public abstract class HttpOnNextListener<T> {
    private Context mContext;
    public HttpOnNextListener(Context mContext){
        this.mContext = mContext;
    }
    public HttpOnNextListener(){
    }
    private Dialog mProgressDialog;
    /**
     * 成功后回调方法
     * @param t
     */
    public abstract void onNext(T t);

    /**
     * 緩存回調結果
     */
    public void onStart(Disposable d){
        if(mContext != null){
            showProgressDialog();
        }

    }

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     * @param e
     */
    public  void onError(Throwable e){
        if(mContext != null){
            dismissProgressDialog();
        }
    }

    /**
     * 取消回調
     */
    public void onComplete(){
        if(mContext != null){
            dismissProgressDialog();
        }
    }

    // ********************************加载中动画************************//
    public synchronized void showProgressDialog() {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = new Dialog(mContext, R.style.DialogStyle);
                Window window = mProgressDialog.getWindow();
                window.setContentView(R.layout.layout_pb);
            }
            mProgressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
            dismissProgressDialog();
        }
    }

    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}


