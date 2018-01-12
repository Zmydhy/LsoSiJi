package com.zmy.laosiji.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Michael on 2018/1/11.
 *  1、
 *            * 获取版本号和版本更新次数
 * 2、
 *           String  获得版本号
 * 3、
 *          * 根据版本号，获得int 在进入应用的时候比较是否需要更新(一般用这个  getIntVersion)
 * 4、
 *       * 将版本号变为int  便于比较
 */

public class VerSionUtils {
    /**
     * 获取版本号和版本更新次数
     *
     * @param context
     * @return
     */
    public static String getVersionCode(Context context, int type) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
            if (type == 1) {
                return String.valueOf(pi.versionCode);
            } else {
                return pi.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * @Description:获得版本号
     * @return 版本号
     * @modified by @time
     */
    public static String getVersionName(Context context) {
        // 用来管理手机的apk
        PackageManager pManager = context.getPackageManager();
        // 得到指定apk的清单文件
        try {
            PackageInfo pInfo = pManager.getPackageInfo(
                    context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据版本号，获得int 在进入应用的时候比较是否需要更新
     * @param context
     * @return
     */
    public static int getIntVersion(Context context) {
        return version2Int(getVersionName(context));
    }

    /**
     * 将版本号变为int  便于比较
     * @param version
     * @return
     */
    public static int version2Int(String version) {
        // 1.0.2
        String[] strVersion = version.split("\\.");
        int intVersion = 0;
        for (int i = 0; i < strVersion.length; i++) {
            intVersion += Integer.parseInt(strVersion[i])
                    * Math.pow(10, strVersion.length - 1 - i);
        }
        return intVersion;
    }
}
