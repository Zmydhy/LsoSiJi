package com.zmy.laosiji.utils.gilde;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.zmy.laosiji.R;
import com.zmy.laosiji.application.MyApplication;

import jp.wasabeef.glide.transformations.BlurTransformation;

/**
 * Created by Michael on 2017/12/22.
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
 */

public class GildeUtil {

    /**
     * 设置基本图片请求
     * @param url
     * @param imageView
     */
    public static void setImageView(String url , ImageView imageView){
        /**
         * 开启
         * options参数
         * .error(id) 异常占位图
         * .override(200,100) 指定固定大小 若使用原始尺寸则用Target.SIZE_ORIGINAL,不会再自动压缩。（ 默认自动压缩）
         * 缓存
         * 1内存缓存（默认开启） 2硬盘缓存
         * 若不想使用内存 则： .skipMemoryCache(true) 就跳过了内存缓存
         *
         *   .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)  硬盘自动缓存
         * DiskCacheStrategy.NONE： 表示不缓存任何内容。
         * DiskCacheStrategy.DATA： 表示只缓存原始图片。
         * DiskCacheStrategy.RESOURCE： 表示只缓存转换过后的图片。
         * DiskCacheStrategy.ALL ： 表示既缓存原始图片，也缓存转换过后的图片。
         * DiskCacheStrategy.AUTOMATIC： 表示让Glide根据图片资源智能地选择使用哪一种缓存策略（默认选项）。
         *
         * 默认自动加载gif图片
         * Glide.with(this)
         * .asBitmap()
         * .load("http://guolin.tech/test.gif")
         * .into(imageView)   默认第一帧图片  必须在load以上
         *
         * .preload  预加载
         * .transforms()图片变换
         * .centerCrop()
         * .fitCenter()
         * .circleCrop()
         *
         * BlurTransformation 是一个第三方gile图片变幻库  有各种样式的图片
         *
         */
        RequestOptions options = new RequestOptions()
                .placeholder(R.mipmap.error_image)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transforms(new BlurTransformation());
        Glide.with(MyApplication.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
//        SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
//            @Override
//            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
//                imageView.setImageDrawable(resource);
//            }
//        };
    }

    /**
     * 根据自己的需求自己传入options
     * @param url
     * @param imageView
     * @param options
     */
    public static void setImageViewAuto(
                            String url,
                            ImageView imageView,
                            RequestOptions options) {
        Glide.with(MyApplication.getContext())
                .load(url)
                .apply(options)
                .into(imageView);
    }
}
