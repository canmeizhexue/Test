package com.canmeizhexue.test.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;


public class MatrixDemoActivity extends BaseActivity implements View.OnTouchListener {
    private TextView content;
    private Matrix matrix = new Matrix();
    private double angle = Math.PI/2;

    private TransformMatrixView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        view = new TransformMatrixView(this);
        view.setScaleType(ImageView.ScaleType.MATRIX);
        view.setOnTouchListener(this);

        setContentView(view);


/*
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_demo);
        content = (TextView) findViewById(R.id.content);

        content.setText(matrix.isIdentity() + "" + matrix.toString());
        LogUtil.d("silence", "matrix.isIdentity()--" + matrix.toShortString());
*/
/*        matrix.postScale(0, 0);
        LogUtil.d("silence", "matrix.isIdentity()--" + matrix.toShortString());*//*

        */
/**
         * 这些方法是那些特殊矩阵的简写，
         * post代表当前矩阵matrix左乘参数代表的转换矩阵
         * pre代表当前矩阵matrix右乘参数代表的矩阵
         * set代表将当前矩阵替换为参数代表的矩阵
         *//*

        matrix.postRotate(90);
        matrix.postTranslate(100, 100);
        matrix.setTranslate(100,100);
//        matrix.preTranslate(200,200);

        LogUtil.d("silence", "matrix.isIdentity()--" + matrix.toShortString());
        content.setText(matrix.isIdentity()+""+matrix.toString());
*/

    }

    class TransformMatrixView extends ImageView
    {
        private Bitmap bitmap;
        private Matrix matrix;
        public TransformMatrixView(Context context){
            super(context);
            bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.matrix);
            matrix = new Matrix();
        }

        @Override
        protected void onDraw(Canvas canvas){
            // 画出原图像
            canvas.drawBitmap(bitmap, 0, 0, null);
            // 画出变换后的图像
            canvas.drawBitmap(bitmap, matrix, null);
            super.onDraw(canvas);
        }

        @Override
        public void setImageMatrix(Matrix matrix){
            this.matrix.set(matrix);
            super.setImageMatrix(matrix);
        }

        public Bitmap getImageBitmap(){
            return bitmap;
        }
    }

    public boolean onTouch(View v, MotionEvent e){
        if(e.getAction() == MotionEvent.ACTION_UP){
            Matrix matrix = new Matrix();
/*            // 输出图像的宽度和高度(162 x 251)
            LogUtil.e("silence", "image size: width x height = " + view.getImageBitmap().getWidth() + " x " + view.getImageBitmap().getHeight());
            // 1. 平移
            matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
            // 在x方向平移view.getImageBitmap().getWidth()，在y轴方向view.getImageBitmap().getHeight()
            view.setImageMatrix(matrix);*/



          // 2. 旋转(围绕图像的中心点)
          matrix.setRotate(45f, view.getImageBitmap().getWidth() / 2f, view.getImageBitmap().getHeight() / 2f);
          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
          matrix.postTranslate(view.getImageBitmap().getWidth() * 1.5f, 0f);
          view.setImageMatrix(matrix);

//          // 3. 旋转(围绕坐标原点) + 平移(效果同2)
//          matrix.setRotate(45f);
//          matrix.preTranslate(-1f * view.getImageBitmap().getWidth() / 2f, -1f * view.getImageBitmap().getHeight() / 2f);
//          matrix.postTranslate((float)view.getImageBitmap().getWidth() / 2f, (float)view.getImageBitmap().getHeight() / 2f);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate((float)view.getImageBitmap().getWidth() * 1.5f, 0f);
//          view.setImageMatrix(matrix);

//          // 4. 缩放
//          matrix.setScale(2f, 2f);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth(), view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);

//          // 5. 错切 - 水平
//          matrix.setSkew(0.5f, 0f);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth(), 0f);
//          view.setImageMatrix(matrix);

//          // 6. 错切 - 垂直
//          matrix.setSkew(0f, 0.5f);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);

//          7. 错切 - 水平 + 垂直
//          matrix.setSkew(0.5f, 0.5f);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight());
//          view.setImageMatrix(matrix);

//          // 8. 对称 (水平对称)
//          float matrix_values[] = {1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(0f, view.getImageBitmap().getHeight() * 2f);
//          view.setImageMatrix(matrix);

//          // 9. 对称 - 垂直
//          float matrix_values[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getWidth() * 2f, 0f);
//          view.setImageMatrix(matrix);

//          // 10. 对称(对称轴为直线y = x)
//          float matrix_values[] = {0f, -1f, 0f, -1f, 0f, 0f, 0f, 0f, 1f};
//          matrix.setValues(matrix_values);
//          // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
//          matrix.postTranslate(view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth(),
//                  view.getImageBitmap().getHeight() + view.getImageBitmap().getWidth());
//          view.setImageMatrix(matrix);

            printMatrix(matrix);
            view.invalidate();
        }
        return true;
    }
    private void printMatrix(Matrix matrix){
        // 下面的代码是为了查看matrix中的元素
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for(int i = 0; i < 3; ++i){
            String temp = new String();
            for(int j = 0; j < 3; ++j){
                temp += matrixValues[3 * i + j ] + "   \t   ";
            }
            Log.e("silence", temp);
        }
    }
}
