package com.zmy.laosiji.rxhttp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.zmy.laosiji.base.MyApplication.getContext;


/**
 * Created by Michael on 2017/8/8.
 */


public class RetrofitFactory {

    private static final String TAG = "===NET REQUEST===";
    private static RetrofitFactory instance;
    private OkHttpClient client;
    private Retrofit retrofit;
    private LaoSiJiService iapi;
    private static final String BASE_URL = "http://gank.io/";
//    private static final String BASE_URL = "http://carwinapi.ucheying.com/";
    private static final long TIME_OUT = 30;
    private static final OkHttpClient mOkHttpClient = new OkHttpClient();


    public static RetrofitFactory getInstance() {
        if (instance == null) {
            synchronized (RetrofitFactory.class) {
                if (instance == null) {
                    instance = new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    public RetrofitFactory() {
        client = getClient();
        retrofit = getRetrofit();
    }


    // 第二步 2.初始化OkHttp的参数 并拿到Client请求对象
    private OkHttpClient getClient() {
        //缓存文件夹
        File cacheFile = new File(getContext() .getExternalCacheDir(),"cache");
        //缓存大小为10M
        int cacheSize = 10 * 1024 * 1024;
        //创建缓存对象
        Cache cache = new Cache(cacheFile.getAbsoluteFile(),cacheSize);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)//连接超时(单位:秒)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)//写入超时(单位:秒)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)//读取超时(单位:秒)
//                .pingInterval(20, TimeUnit.SECONDS) //websocket轮训间隔(单位:秒)
                .cache(cache)//设置缓存
                // 添加通用的Header
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        Request.Builder builder = chain.request().newBuilder();
//                        builder.addHeader("token", "123");
//                        return chain.proceed(builder.build());
//                    }
//                })
                     /*
                    这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
                    出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
                     */
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {

                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        return okHttpClient;
    }

    // 第二步 3.初始化Retrofit的参数 并将Client设置给它
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .client(client)// 传入OKhttp的client对象
                .addConverterFactory(GsonConverterFactory.create())// 添加gson解析的支持
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 添加支持observable类型
                .baseUrl(BASE_URL)// 传入URL
                .build();
    }

    // 第四步 通过retrofit创建Iapi的实现类对象(retrofit已经帮你实现好了 不需要自己去实现 只要调用方法)
    // 待会model要用来调你写好的Iapi里的接口 接口里定义好了方法为userLogin(放第一步获取的请求体)
    public LaoSiJiService getApi() {
        if (iapi == null) {
            iapi = retrofit.create(LaoSiJiService.class);
        }
        return iapi;
    }
}

