package com.zmy.laosiji.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

/**
 * Created by Zmy on 2015-10-10.
 * Project: CarWin2.0.
 */
public class SharePreferenceUtils {
    private static SharedPreferences mSharedPreferences;

    // 方式1

    /**
     * 添加Int类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setCustomSharedInt(Context context, String name,
                                          String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);//操作模式为本程序
        sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 获取Int类型
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String name, String key,
                             int defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, defaultValue);
    }




    /**
     * 获取Boolean类型
     *
     * @param context
     * @param key
     * @return
     */
    public static Boolean getBoolean(Context context, String name, String key,
                                     boolean defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    /**
     * 添加String类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String name, String key,
                                  Boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 添加String类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString2encrypt(Context context, String name, String key,
                                         String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        try {
            value = EncryptorUtil.encryptDESStr(value, "83227587");
        } catch (Exception ex) {
            value = "";
        }
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 添加String类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String name, String key,
                                 String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }


    /**
     * 获取String类型
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Context context, String name, String key,
                                   String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 获取String类型
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString2decrypt(Context context, String name, String key,
                                           String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        String value=sharedPreferences.getString(key, defaultValue);
        try{
            value = EncryptorUtil.decryptDESStr(value, "83227587");
        }catch(Exception ex){
            value= "";
        }

        return value;
    }

    /**
     * 获取String类型
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString2decrypt(Context context, String key,
                                           String defaultValue) {
            if (mSharedPreferences == null) {
            init(context);
        }
        String value=mSharedPreferences.getString(key, defaultValue);
        try{
            value = EncryptorUtil.decryptDESStr(value, "83227587");
        }catch(Exception ex){
            value= "";
        }

        return value;
    }

    /**
     * 删除某个键�?内容
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String name, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }

    /**
     * 清空�?��
     *
     * @param context
     */
    public static void clearAll(Context context, String name) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);//只能被本程序访问
        sharedPreferences.edit().clear().commit();


    }

    /**
     * 添加Int类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setInt(Context context, String name, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 添加Int类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void Int(Context context, String name, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key, value).commit();
    }


    // 方式2
    private static void init(Context context) {
        if (mSharedPreferences == null) {
            //用来读取默认的偏好文件
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        }
    }
    /**
     * 添加Int类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setInt(Context context, String key, int value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * 获取Int类型
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return getInt(context, key, -1);
    }

    /**
     * 获取Int类型
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String key,
                             int defaultValue) {
        if (mSharedPreferences == null) {
            init(context);
        }

        return mSharedPreferences.getInt(key, defaultValue);

    }

    /**
     * 添加Long类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setlong(Context context, String key, long value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * 获取Long类型
     *
     * @param context
     * @param key
     * @return
     */
    public static long getlong(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return getlong(context, key, -1L);
    }

    /**
     * 获取Long类型
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getlong(Context context, String key,
                               long defaultValue) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 添加Float类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setFloat(Context context, String key,
                                float value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putFloat(key, value).commit();
    }

    /**
     * 获取Float类型
     *
     * @param context
     * @param key
     * @return
     */
    public static Float getFloat(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return getFloat(context, key, -1f);
    }

    /**
     * 获取Float类型
     *
     * @param context
     * @param key
     * @return
     */
    public static Float getFloat(Context context, String key,
                                 float defaultValue) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * 添加Boolean类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setBoolean(Context context, String key,
                                  boolean value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * 获取Boolean类型
     *
     * @param context
     * @param key
     * @return
     */
    public static Boolean getBoolean(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return getBoolean(context, key, false);
    }

    /**
     * 获取Boolean类型
     *
     * @param context
     * @param key
     * @return
     */
    public static Boolean getBoolean(Context context, String key,
                                     boolean defaultValue) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 添加String类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString2encrypt(Context context, String key,
                                         String value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        // 对密码进行AES加密
        try {
            value = EncryptorUtil.encryptDESStr(value, "83227587");
        } catch (Exception ex) {
            Toast.makeText(context, "加密失败", Toast.LENGTH_SHORT).show();
            Toast.makeText(context,"加密成功", Toast.LENGTH_SHORT).show();
            value = "";
        }
        mSharedPreferences.edit().putString(key, value).commit();
    }
    /**
     * 添加String类型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setString(Context context, String key, String value) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * 获取String类型
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return getString(context, key, null);
    }

    /**
     * 获取String类型
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Context context, String key,
                                   String defaultValue) {
        if (mSharedPreferences == null) {
            init(context);
        }
        return mSharedPreferences.getString(key, defaultValue);//参数二用来在参数一无法读取的情况下使用
    }

    /**
     * 删除某个键�?内容
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().remove(key).commit();
    }

    /**
     * 清空�?��
     *
     * @param context
     */
    public static void clearAll(Context context) {
        if (mSharedPreferences == null) {
            init(context);
        }
        mSharedPreferences.edit().clear().commit();
    }
}
