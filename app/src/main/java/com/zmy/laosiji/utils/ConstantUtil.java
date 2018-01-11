package com.zmy.laosiji.utils;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zmy.laosiji.BuildConfig;
import com.zmy.laosiji.R;

import static com.zmy.laosiji.base.MyApplication.getContext;
import static com.zmy.laosiji.utils.MUtils.className;
import static com.zmy.laosiji.utils.MUtils.getMethodNames;
import static com.zmy.laosiji.utils.MUtils.lineNumber;
import static com.zmy.laosiji.utils.MUtils.methodName;

/**
 * Created by Michael on 2017/11/23.
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
 *
 * 1、
 *      Toast工具类
 * 2、
 *      Log_I
 * 3、
 *      log_E
 * 4、
 *      createLog
 */

public class ConstantUtil {

    /**
     * Toast工具类
     */
    private static Toast toast = null;
    public  static void toast(String text){
        if(toast == null){
            toast = new Toast(getContext());
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            ViewGroup layout = new RelativeLayout(getContext());
            View view =  LayoutInflater.from(getContext()).inflate(R.layout.common_toast, null);
            TextView tvToast = (TextView) view.findViewById(R.id.tv_common_toast);
            tvToast.setText(text);
            layout.addView(view);
            toast.setView(layout);
        }else{
            toast.setDuration(Toast.LENGTH_SHORT);
            ViewGroup layout = new RelativeLayout(getContext());
            View view =  LayoutInflater.from(getContext()).inflate(R.layout.common_toast, null);
            TextView tvToast = (TextView) view.findViewById(R.id.tv_common_toast);
            tvToast.setText(text);
            layout.addView(view);
            toast.setView(layout);
        }
        toast.show();
    }


    public static void log_e(String message) {
        if (!isDebuggable()) {
            return;
        }

        // Throwable instance must be created before any methods
        getMethodNames(new Throwable().getStackTrace());
        Log.e(className, createLog(message));
    }


    public static void log_i(String message) {
        if (!isDebuggable()) {
            return;
        }

        getMethodNames(new Throwable().getStackTrace());
        Log.i(className, createLog(message));
    }

    public static boolean isDebuggable() {
        return BuildConfig.DEBUG;
    }

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }



}
