package com.zmy.laosiji.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michael on 2018/1/11.
 *      这是一个常用的工具类
 *  1、
 *           hideKeyBoard 点击view强制隐藏键盘
 * 2、
 *          hideSoftInput直接隐藏软键盘
 * 3、
 *          setEdtCursor 设置EditText光标在内容之后
 * 4、
 *          setTvCursor 设置TextView光标在内容之后
 * 5、
 *          isBackground 判断app是否在前台还是在后台运行
 * 6、
 *           getProcessName 获取进程名字
 * 7、
 *          获取类名
 *          获取方法名
 *          获取行数
 *          一般传参数如下：
 *          new Throwable().getStackTrace()
 *  8、
 *         isFastDoubleClick（）  防止多次点击
 *  9、
 *          startActivty() 带或者不带参数的 跳转
 */

public class MUtils {


    /**
     * hideKeyBoard 点击view强制隐藏键盘
     * @param context this
     * @param view 点击的view
     */
    public static void hideKeyBoard(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    /**
     * 隐藏软键盘
     * @param activity
     */

    public static void hideSoftInput(Activity activity) {
        View view = activity.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager)activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * @Description setEdtCursor 设置EditText光标在内容之后
     */
    public static void setEdtCursor(EditText edt) {
        CharSequence text = edt.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }

    /**
     * @Description setTvCursor 设置TextView光标在内容之后
     */
    public static void setTvCursor(TextView tv) {
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            Spannable spanText = (Spannable) text;
            Selection.setSelection(spanText, text.length());
        }
    }


    /**
     * @Description isBackground 判断app是否在前台还是在后台运行
     */
    public static boolean isBackground(Context context) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                /*
                 * BACKGROUND=400 EMPTY=500 FOREGROUND=100
				 * GONE=1000 PERCEPTIBLE=130 SERVICE=300 ISIBLE=200
				 */
                Log.i(context.getPackageName(), "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());
                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(context.getPackageName(), "处于后台" + appProcess.processName);
                    return true;
                } else {
                    Log.i(context.getPackageName(), "处于前台" + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * @return getProcessName 获取进程名字
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    /**
     * 获取类名
     * 获取方法名
     * 获取行数
     * 一般传参数如下：
     * new Throwable().getStackTrace()
     */
    static String className;
    static String methodName;
    static int lineNumber;
   public static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    /**
     * 防止多次点击
     */
    private static long lastClickTime;
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if ( 0 < timeD && timeD < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }
    /**
     * 启动一个activity不带参数
     *
     * @param pClass
     *            要启动的activity
     */
    public static void startActivity(Context context, Class<?> pClass) {
        Intent intent = new Intent(context, pClass);
        context.startActivity(intent);
    }

    /**
     * 启动一个activity 带有Serializable参数
     *
     * @param pClass
     *            要启动的activity
     * @param name
     *            键名
     * @param value
     *            值名 这里通常是一个实体类
     */
    public static void startActivity(Context context, Class<?> pClass,
                                     String name, Serializable value) {
        Intent intent = new Intent(context, pClass);
        intent.putExtra(name, value);
        context.startActivity(intent);
    }
    /**
     * 启动一个activity,带有bundle参数
     *
     * @param clazz
     *            要启动的activity
     * @param bundle
     */
    public static void startActivity(Context context, Class<?> clazz,
                                     Bundle bundle) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
