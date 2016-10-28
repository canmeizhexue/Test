package com.canmeizhexue.test.image;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.canmeizhexue.test.ImageLoaderHelper;
import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**http://www.2cto.com/kf/201411/348601.html
 * http://www.wjdiankong.cn/android中的imageview的scaletype属性详解/    只是看下效果，解释的不一定对
 * <p>ImageScaleTypeActivity类 概述，提供XXX功能</p>
 *
 * @author silence
 * @version 1.0 (2016-10-18 9:13)
 */
public class ImageScaleTypeActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.default_type)
    ImageView defaultType;
    /**
     * center保持原图的大小，显示在ImageView的中心。不会进行缩放， 当图片小于imageview大小的时候居中显示完整图片 ,,当原图的size大于ImageView的size，超过部分裁剪处理。
     */
    @Bind(R.id.center)
    ImageView center;
    /**
     * 一般维持图片的宽高比和imageview一样的话，这个显示效果还可以
     *
     * 维持图片原来的宽高比，使得图片的宽高都等于或者大于imageview的宽和高，图片居中显示，当图片大于imageview，显示图片的中间部分
     *
     * 以填满整个ImageView为目的，将原图的中心对准ImageView的中心，等比例放大原图，直到填满ImageView为止（指的是ImageView的宽和高都要填满），原图超过ImageView的部分作裁剪处理。
     *
     */
    @Bind(R.id.centerCrop)
    ImageView centerCrop;

    @Bind(R.id.fitCenter)
    ImageView fitCenter;
    @Bind(R.id.fitEnd)
    ImageView fitEnd;
    @Bind(R.id.fitStart)
    ImageView fitStart;
    /**
     *把原图按照指定的大小在View中显示，拉伸显示图片，不保持原比例，填满ImageView
     * 可以看到完整的图片，但是图片的宽高比和原图可能不一样，可能会变形
     */
    @Bind(R.id.fitXY)
    ImageView fitXY;
    /**
     * 可能图片不会占满整个imageview
     *
     * 维持图片原来的宽高比，使得图片的宽高都等于或者小于imageview的宽和高，图片居中显示,如果图片本来就小于imageview,那么图片不会缩放
     *
     * 以原图完全显示为目的，将图片的内容完整居中显示，通过按比例缩小原图的size宽(高)等于或小于ImageView的宽(高)。如果原图的size本身就小于ImageView的size，则原图的size不作任何处理，居中显示在ImageView。
     */
    @Bind(R.id.centerInside)
    ImageView centerInside;
    /**
     * matrix使用矩阵的方式画图，默认是单元矩阵，从左上角开始，原图超过ImageView的部分作裁剪处理,图片小于imageview时，不会放大
     *
     * 但是这个矩阵可以通过setImageMatrix改变
     */
    @Bind(R.id.matrix)
    ImageView matrix;//默认

    private int imgRes1,imgRes2;
    private int imgRes = R.mipmap.small_length;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scale_type);
        ButterKnife.bind(this);
        defaultType.setImageResource(imgRes);
        center.setImageResource(imgRes);
        ImageLoaderHelper.showImgFromResId(this,centerCrop,imgRes);
//        centerCrop.setImageResource(imgRes);
        fitCenter.setImageResource(imgRes);
        fitEnd.setImageResource(imgRes);
        fitStart.setImageResource(imgRes);
        fitXY.setImageResource(imgRes);
        centerInside.setImageResource(imgRes);
        matrix.setImageResource(imgRes);
    }

    @Override
    protected void onResume() {
        super.onResume();
        defaultType.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("silence",centerCrop.getWidth()+","+centerCrop.getHeight()+","+centerCrop.getMeasuredWidth()+","+centerCrop.getMeasuredHeight());
            }
        },2000);

    }

    @Override
    public void onClick(View v) {

    }
}
