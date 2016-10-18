package com.canmeizhexue.test;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

/**图片加载帮助类，封装第三方加载库，
 * Created by silence on 2016-10-11.
 */
public class ImageLoaderHelper {
    public static void showImgFromNetwork(Context context,ImageView imageView,String imageUrl){
        show(context,imageView,imageUrl);
    }
    public static void showImgFromResId(Context context,ImageView imageView,int resId){
        show(context,imageView,resId);
    }
    public static void showImgFromSdcard(Context context,ImageView imageView,File file){
        show(context,imageView,file);
    }
    public static void showImgFromUri(Context context,ImageView imageView,Uri uri){
        show(context,imageView,uri);
    }
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    public static Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
    /**
     * 图片显示，直接展示
     *
     * @param context   上下文
     * @param imageView 控件
     * @param url       地址
     * @param <T> 类型
     */
    public static <T> void show(Context context, ImageView imageView, T url) {
        show(context, imageView, url, R.mipmap.img_placeholder,R.mipmap.img_load_error);
    }

    /**
     * 图片显示
     * @param context 上下文
     * @param imageView 控件
     * @param url 地址
     * @param placeImgRes 占位符图片
     * @param errorImgRes 加载异常时的图片
     * @param <T> 类型
     */
    public static <T> void show(Context context, ImageView imageView, T url,
                                int placeImgRes,int errorImgRes) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop() // 图片的宽高比不变，缩放图片
//                .fitCenter()//图片的宽高比改成imageview的宽高比，缩放图片
                .placeholder(placeImgRes)
                .error(errorImgRes)
                .into(imageView);
    }
}
