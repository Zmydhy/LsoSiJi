package com.zmy.laosiji.rxhttp;

import com.zmy.laosiji.utils.ConstantUtil;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2018/1/8.
 */

public class RxBus {
    private static volatile RxBus mInstance;
    private Subject<Object> bus;
    private HttpOnNextListener httpOnNextListener;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    private RxBus(String s) {
        if (s.equals("Behavior")) {
            bus = BehaviorSubject.create().toSerialized();
        }
    }

    /**
     * 接收订阅以后的数据
     *
     * @return
     */
    public static RxBus getRxBus() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * 会延时接收订阅之前最后一个发送的数据
     *
     * @return
     */
    public static RxBus getRxBusBehavior() {
        if (mInstance == null) {
            synchronized (RxBus.class) {
                if (mInstance == null) {
                    mInstance = new RxBus("Behavior");
                }
            }
        }
        return mInstance;
    }

    // 发送一个新的事件，所有订阅此事件的订阅者都会收到
    public void post(Object action) {
        bus.onNext(action);
    }


    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者  
    //相当于注册
    private <T> Observable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }

    public <T> void subscribeOn(Class<T> T, HttpOnNextListener httpOnNextListener) {
        this.httpOnNextListener = httpOnNextListener;
        toObservable(T).subscribe(HttpAPi.createObserver(httpOnNextListener));
    }

    public void unSubscribeOn() {
        ConstantUtil.log_e("RxBus解绑");
        httpOnNextListener.getDisposable().dispose();
    }


}
