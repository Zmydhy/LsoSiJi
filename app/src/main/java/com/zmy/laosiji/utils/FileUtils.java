package com.zmy.laosiji.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by Michael on 2017/12/5.
 *  1、
 *           zoomBitmap 放大缩小图片
 * 2、
 *          drawableToBitmap 将Drawable转化为Bitmap
 * 3、
 *         getRoundedCornerBitmap 带圆角的图片
 * 4、
 *          saveImage 保存图片到本地
 * 5、
 *          compressImage 调用压缩图片的方法，返回压缩后的图片path
 * 6、
 *         getSmallBitmap 根据路径获得图片信息并按比例压缩，返回bitmap
 * 7、
 *          getSmall2Bitmap 根据路径获得图片信息并按比例压缩，返回bitmap（常用）
 *
 *  8、
 *          getRealFilePath 把图片uri转Path
 */

public class FileUtils {
    private static String SDPATH = "";
    /**
     * @Description:判断sd卡是否存在
     * @modified by @time
     */
    public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
    /**
     * @Description:获取sd卡路径+apkName
     *            apk名称
     * @modified by @time
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 获取项目路径
     *
     * @return
     */
    public static String getProJectPath() {
        SDPATH = Environment.getExternalStorageDirectory() +"/LaoSiJi/";
        return SDPATH;
    }

    /**
     * 在项目文件夹创建文件或文件夹
     *
     * @param fileName
     *            文件名或问文件夹名
     */
    public static void createFile(String fileName) {
        File file = new File(getProJectPath() + fileName);
        if (fileName.indexOf(".") != -1) {
            // 说明包含，即使创建文件, 返回值为-1就说明不包含.,即使文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("创建了文件");
        } else {
            // 创建文件夹
            file.mkdirs();
            System.out.println("创建了文件夹");
        }

    }

    /**
     * 删除文件夹下所有文件
     * @param root
     */
    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
        }
    }


    public  void getEnvironmentDirectories() {
        //:/system
        String rootDir = Environment.getRootDirectory().toString();
        System.out.println("Environment.getRootDirectory()=:" + rootDir);

        //:/data 用户数据目录
        String dataDir = Environment.getDataDirectory().toString();
        System.out.println("Environment.getDataDirectory()=:" + dataDir);

        //:/cache 下载缓存内容目录
        String cacheDir = Environment.getDownloadCacheDirectory().toString();
        System.out.println("Environment.getDownloadCacheDirectory()=:" + cacheDir);

        //:/mnt/sdcard或者/storage/emulated/0或者/storage/sdcard0 主要的外部存储目录
        String storageDir = Environment.getExternalStorageDirectory().toString();
        System.out.println("Environment.getExternalStorageDirectory()=:" + storageDir);

        //:/mnt/sdcard/Pictures或者/storage/emulated/0/Pictures或者/storage/sdcard0/Pictures
        String publicDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        System.out.println("Environment.getExternalStoragePublicDirectory()=:" + publicDir);

        //获取SD卡是否存在:mounted
        String storageState = Environment.getExternalStorageState().toLowerCase();
        System.out.println("Environment.getExternalStorageState()=:" + storageState);

        //设备的外存是否是用内存模拟的，是则返回true。(API Level 11)
        boolean isEmulated = Environment.isExternalStorageEmulated();
        System.out.println("Environment.isExternalStorageEmulated()=:" + isEmulated);

        boolean isRemovable = Environment.isExternalStorageRemovable();
        System.out.println("Environment.isExternalStorageRemovable()=:" + isRemovable);
    }

    public  void getApplicationDirectories(Context context) {

        //获取当前程序路径 应用在内存上的目录 :/data/data/com.mufeng.toolproject/files
        String filesDir = context.getFilesDir().toString();
        System.out.println("context.getFilesDir()=:" + filesDir);

        //应用的在内存上的缓存目录 :/data/data/com.mufeng.toolproject/cache
        String cacheDir = context.getCacheDir().toString();
        System.out.println("context.getCacheDir()=:" + cacheDir);

        //应用在外部存储上的目录 :/storage/emulated/0/Android/data/com.mufeng.toolproject/files/Movies
        String externalFilesDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).toString();
        System.out.println("context.getExternalFilesDir()=:" + externalFilesDir);

        //应用的在外部存储上的缓存目录 :/storage/emulated/0/Android/data/com.mufeng.toolproject/cache
        String externalCacheDir = context.getExternalCacheDir().toString();
        System.out.println("context.getExternalCacheDir()=:" + externalCacheDir);

        //获取该程序的安装包路径 :/data/app/com.mufeng.toolproject-3.apk
        String packageResourcePath = context.getPackageResourcePath();
        System.out.println("context.getPackageResourcePath()=:" + packageResourcePath);

        //获取程序默认数据库路径 :/data/data/com.mufeng.toolproject/databases/mufeng
        String databasePat = context.getDatabasePath("mufeng").toString();
        System.out.println("context.getDatabasePath(\"mufeng\")=:" + databasePat);

    }


}
