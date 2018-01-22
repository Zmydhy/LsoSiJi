package com.zmy.laosiji.jni;

/**
 * Created by Administrator on 2018/1/22.
 */


public class JNI {
    static {
        System.loadLibrary("native-lib");
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromJNI();
}
