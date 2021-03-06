package com.canmeizhexue.test.image;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;
import com.canmeizhexue.test.utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by silence on 2016/4/20.
 */
public class ImageViewActivity extends BaseActivity {
    @Bind(R.id.root)
    LinearLayout root;
    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.image_100)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        ButterKnife.bind(this);
        imageView.setBackgroundResource(R.drawable.bg_test_padding);
        // 获取到的都是0，因为执行完onResume后才会去测量
        LogUtil.d("silence",imageView.getWidth()+"--getWidth1---"+imageView.getHeight());
        LogUtil.d("silence",imageView.getMeasuredWidth()+"---getWidth1--"+imageView.getMeasuredHeight());
        getWidth1();
        getWidh2();

    }

    @OnClick(R.id.image)
    public void onClick() {
        DisplayMetrics displayMetrics=getResources().getDisplayMetrics();
        Log.d("silence",displayMetrics.density+"------"+displayMetrics.densityDpi);
        Drawable drawable=image.getDrawable();
        if(drawable instanceof BitmapDrawable){
            // 从这个地方可以看到，不管设置imageview为多大，占用的内存大小是一定的
            //图片放大或者缩小的非常厉害的时候，都会严重失真
            //占用内存的计算公式=width * height * 4 * (设备密度dt / 资源夹密度) * (设备密度dt / 资源夹密度)
            // width是图片文件的宽度，不是imageview控件的宽度
            //乘以4，是因为默认的解码格式ARGB_8888中一个像素需要4个字节的内存空间
            //假如设备设备是xhdpi,资源夹密度是xxhdpi，，那么(设备密度dt / 资源夹密度)=4/6
            // mhdpi hdpi xhdpi xxhdpi xxxhdpi
            //2      3    4     6      8
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            Log.d("silence","------"+bitmap.getByteCount());
        }
    }
    private void getWidth1(){
        int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        root.measure(w, h);
        int width =imageView.getMeasuredWidth();
        int height =imageView.getMeasuredHeight();

        LogUtil.d("silence",imageView.getWidth()+"--getWidth1---"+imageView.getHeight());
        LogUtil.d("silence",imageView.getMeasuredWidth()+"---getWidth1--"+imageView.getMeasuredHeight());
    }

    private void getWidh2(){
        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                int height = imageView.getMeasuredHeight();
                int width = imageView.getMeasuredWidth();

                LogUtil.d("silence",imageView.getWidth()+"--onPreDraw---"+imageView.getHeight());
                LogUtil.d("silence",imageView.getMeasuredWidth()+"--onPreDraw---"+imageView.getMeasuredHeight());
                return true;
            }
        });
    }
}
