package com.zmy.laosiji.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Michael on 2018/1/11.
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
 *      这是一个常用的工具类
 */

public class MUtils {

    /**
     * 校验Tag Alias 只能是数字,英文字母和中文
     * @param s
     * @return
     */
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
    /**
     * 将list倒序输出
     * @param rawList
     * @param <T>
     * @return
     */
    public static <T> List<T> reverseList(List<T> rawList) {
        List<T> resultList = new ArrayList<T>();
        ListIterator<T> li = rawList.listIterator();
        // 将游标定位到列表结尾
        for (li = rawList.listIterator(); li.hasNext();) {
            li.next();
        }
        // 逆序输出列表中的元素
        for (; li.hasPrevious();) {
            resultList.add(li.previous());
        }
        return resultList;
    }

    /**
     * 反转数组
     *
     * @param arrays
     * @param <T>
     * @return
     */
    public static <T> T[] reverse(T[] arrays) {
        if (arrays == null) {
            return null;
        }
        int length = arrays.length;
        for (int i = 0; i < length / 2; i++) {
            T t = arrays[i];
            arrays[i] = arrays[length - i - 1];
            arrays[length - i - 1] = t;
        }
        return arrays;
    }

    /**
     * 强制隐藏键盘
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
     * @return 获取进程名字
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
     */
    static String className;
    static String methodName;
    static int lineNumber;
    static void getMethodNames(StackTraceElement[] sElements) {
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
