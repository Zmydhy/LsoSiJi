package com.zmy.laosiji.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Selection;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import static com.zmy.laosiji.base.MyApplication.getContext;

/**
 * @Description 处理Resources的工具类
 * 1、
 *     inflate 获取布局
 * 2、
 *    获取String
 * 3、
 *      获取String数组
 * 4、
 *      获取dimen
 * 5、
 *      获取drawable
 * 6、
 *         获取颜色
 * 7、
 *      获取颜色选择器
 *
 */

public class ResourcesUtils {


    /**
     * @Description 获取布局
     */
    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * @Description 获取资源
     */
    private static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * @Description 获取String
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * @Description 获取String数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * @Description 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * @Description 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * @Description 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * @Description 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }



}
