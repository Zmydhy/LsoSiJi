package com.zmy.laosiji.jni;

/**
 * Created by Administrator on 2018/1/22.
 */


public class JNIT {
    static {
        System.loadLibrary("test-lib");
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public static native String stringFromTEST1();
    public static native String stringFromTEST2();
}
