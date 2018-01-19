package com.zmy.laosiji.rxhttp;

import com.zmy.laosiji.base.MyApplication;
import com.zmy.laosiji.moudle.entity.LoginEntiny;
import com.zmy.laosiji.moudle.entity.MeizhiEntity;
import com.zmy.laosiji.utils.ConstantUtil;
import com.zmy.laosiji.utils.NetStateUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
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

public class HttpAPi {
    /**
     * 获取妹纸图片
     * @param fenlei
     * @param count
     * @param page
     * @param mHttpOnNextListener
     */
    public static void getMeiZhi(String fenlei, int count, int page, final HttpOnNextListener mHttpOnNextListener) {
        final Observable<MeizhiEntity> meizhiCall = RetrofitFactory.getInstance().getApi().getMeiZhi(fenlei, count, page);
        goHttp(meizhiCall, mHttpOnNextListener);
    }

    /**
     * 车赢登录接口
     * @param mHttpOnNextListener
     */
    public static void postLogIn(final HttpOnNextListener mHttpOnNextListener) {
        Map<String, String> params = new HashMap<>();
        params.put("UserName", "15820034792");
        params.put("Password", "123456");
        params.put("type", "1");
        final Observable<LoginEntiny> meizhiCall = RetrofitFactory.getInstance().getApi().login2(params);
        goHttp(meizhiCall, mHttpOnNextListener);
    }

    /**
     * 设置网络
     * @param observable
     * @param mHttpOnNextListener
     * @param <T>
     */
    private static<T> void  goHttp(Observable<T> observable, final HttpOnNextListener mHttpOnNextListener){
        if(!NetStateUtils.isNetworkConnect(MyApplication.getContext())){
            ConstantUtil.toast("老司机开车，去联网不迷路！");
        }else {
            observable.compose(RxScheduleMapper.<T>io2main()).subscribe(createHttpObserver(mHttpOnNextListener));
        }

    }


    /**
     * 创建被观察者
     * @param callable
     * @param <T>
     * @return
     */
    public static<T> Observable<T> createObservable(final BaseObservable<T> callable) {
        return  Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                try {
                    T result = callable.subscribe(subscriber);
                    subscriber.onNext(result);
                } catch (Exception e) {
                    subscriber.onError(e);
                }
                subscriber.onComplete();
            }
        });
    }

    /**
     * 创建观察者
     * @param mHttpOnNextListener
     * @param <T>
     * @return
     */
    public static<T> Observer<T> createObserver(final HttpOnNextListener<T> mHttpOnNextListener){
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                mHttpOnNextListener.onStart(d);
            }

            @Override
            public void onNext(T t) {
                mHttpOnNextListener.onNext(t);
            }

            @Override
            public void onError(Throwable e) {
                mHttpOnNextListener.onError(e);
            }

            @Override
            public void onComplete() {
                mHttpOnNextListener.onComplete();
            }
        };
    }
    /**
     * 创建访问网络的观察者，判断网络是否连接
     * @param mHttpOnNextListener
     * @param <T>
     * @return
     */
    public static<T> Observer<T> createHttpObserver(final HttpOnNextListener<T> mHttpOnNextListener){
        return new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {
                mHttpOnNextListener.onStart(d);
            }

            @Override
            public void onNext(T t) {
                mHttpOnNextListener.onNext(t);
            }

            @Override
            public void onError(Throwable e) {
                mHttpOnNextListener.onError(e);
            }

            @Override
            public void onComplete() {
                mHttpOnNextListener.onComplete();
            }
        };
    }

    public static<T> void  createIoFuction(BaseObservable<T> baseObservable, HttpOnNextListener observersListener){
        createObservable(baseObservable).compose(RxScheduleMapper.<T>io2main()).subscribe(createObserver(observersListener));
    }


}
