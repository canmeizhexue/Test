package com.canmeizhexue.test.image;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;


/**主要考虑全屏，非全屏，对话框,popupWindow中获取这些坐标
 * View.getLocalVisibleRect获取View自身可见的坐标区域，坐标以自己的左上角为原点(0,0)，另一点为可见区域右下角相对自己(0,0)点的坐标
 * View.getGlobalVisibleRect
 * View.getLocationOnScreen
 *
 * Created by pc on 2016/3/6.
 */
public class CoordinateActivity extends BaseActivity implements View.OnClickListener{
    View view1,view2;
    Rect rect = new Rect();
    int[] array=new int[2];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);
        view1 = findViewById(R.id.view1);
        view1.setOnClickListener(this);;
        view2 = findViewById(R.id.view2);
        view2.setOnClickListener(this);
        findViewById(R.id.container).setOnClickListener(this);
/*        //获取屏幕区域的宽高等尺寸获取,与是否有状态栏无关
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;
        LogUtil.d("silence","屏幕区域  widthPixels="+widthPixels+"  heightPixels="+heightPixels);*/


        //获取View自身可见的坐标区域，坐标以自己的左上角为原点(0,0)，另一点为可见区域右下角相对自己(0,0)点的坐标
/*        view1.getLocalVisibleRect(rect);
        LogUtil.d("silence", "view1.getLocalVisibleRect(rect)=" + rect);
        view2.getLocalVisibleRect(rect);
        LogUtil.d("silence", "view2.getLocalVisibleRect(rect)=" + rect);*/



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){

        }
    }

    @Override
    public void onClick(View v) {
/*        v.getLocalVisibleRect(rect);
        LogUtil.d("silence", "v.getLocalVisibleRect(rect)=" + rect);*/
/*        v.getGlobalVisibleRect(rect);
        LogUtil.d("silence", "v.getGlobalVisibleRect(rect)=" + rect);*/
/*        v.getLocationOnScreen(array);
        LogUtil.d("silence", "v.getLocationOnScreen(rect)=" + array[0]+","+array[1]);*/
        v.getLocationInWindow(array);
        Log.d("silence", "v.getLocationInWindow(rect)=" + array[0] + "," + array[1]);
        Log.d("silence","left,top,right,bottom="+v.getLeft()+","+v.getTop()+","+v.getRight()+","+v.getBottom());
        if(v==view1){
/*            //应用程序App区域宽高等尺寸获取
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            LogUtil.d("silence", "App区域  rect=" + rect);*/
/*            //获取状态栏高度
            Rect rectangle= new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rectangle);
            int statusBarHeight = rectangle.top;
            LogUtil.d("silence", "状态栏高度 statusBarHeight=" + statusBarHeight);*/
            new MyDialog(this).show();

   /*         Rect rect = new Rect();
            view1.getDrawingRect(rect);
            LogUtil.d("silence", "view1  getDrawingRect=" + rect);*/
        }
        if(v==view2){
            // 会移动整个view，改变view在父容器中的位置信息
/*            LogUtil.d("silence","left,top,right,bottom="+v.getLeft()+","+v.getTop()+","+v.getRight()+","+v.getBottom());
            v.offsetTopAndBottom(40);
            LogUtil.d("silence","left,top,right,bottom="+v.getLeft()+","+v.getTop()+","+v.getRight()+","+v.getBottom());*/


/*            LogUtil.d("silence", "v==view2=");
         View contentVIew = View.inflate(this,R.layout.item_single_text,null);*/

 /*           PopupWindow popupWindow = new PopupWindow(contentVIew, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            popupWindow.showAsDropDown(view1);*/
            PopupWindow popupWindow = new MyPopWindow(this);
            popupWindow.setWidth(800);
            popupWindow.setHeight(300);
            popupWindow.showAsDropDown(view1, 0, 0);
//             popupWindow.showAtLocation(view1, Gravity.LEFT|Gravity.TOP,0,150);
/*            Rect rect = new Rect();
            view2.getDrawingRect(rect);
            LogUtil.d("silence", "view2  getDrawingRect=" + rect);
            LogUtil.d("silence", "view2  getDrawingRect=" + view2.getLeft()+","+view2.getTop()+","+view2.getRight()+","+view2.getBottom());*/
        }

    }
    class MyDialog extends Dialog implements View.OnClickListener{

        public MyDialog(Context context) {
            super(context);
            getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.item_single_text);

            findViewById(R.id.text).setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Rect rect = new Rect();

/*            v.getLocalVisibleRect(rect);
            LogUtil.d("silence", "MyDialog.getLocalVisibleRect(rect)=" + rect);*/
/*
            v.getGlobalVisibleRect(rect);
            LogUtil.d("silence", "v.getGlobalVisibleRect(rect)=" + rect);*/
            v.getLocationOnScreen(array);
            Log.d("silence", "MyDialog----v.getLocationOnScreen(rect)=" + array[0] + "," + array[1]);
            v.getLocationInWindow(array);
            Log.d("silence", "v.getLocationInWindow(rect)=" + array[0] + "," + array[1]);
            Log.d("silence", "v  position=" + v.getLeft() + "," + v.getTop() + "," + v.getRight() + "," + v.getBottom());
            PopupWindow popupWindow = new MyPopWindow(getContext());
            popupWindow.setWidth(800);
            popupWindow.setHeight(300);
            WindowManager.LayoutParams p=new WindowManager.LayoutParams();
            findDropDownPosition(v, p,0,0);
            popupWindow.showAsDropDown(v, 0, 0);
/*            //应用程序App区域宽高等尺寸获取
            Rect rect = new Rect();
            // 这个地方的是dialog的window
            v.getWindowVisibleDisplayFrame(rect);
            LogUtil.d("silence", "App区域  rect=" + rect);
            //获取状态栏高度
            v.getWindowVisibleDisplayFrame(rect);
            int statusBarHeight = rect.top;
            LogUtil.d("silence", "状态栏高度 statusBarHeight=" + statusBarHeight);*/
        }
    }
    class MyPopWindow extends PopupWindow implements View.OnClickListener{
        public MyPopWindow(Context context) {
            super(context);
            View contentView = View.inflate(context, R.layout.item_single_text, null);
            ViewGroup.LayoutParams lp = contentView.getLayoutParams();
            Log.d("silence", "lp=" + lp);
            contentView.setBackgroundColor(Color.BLUE);
            contentView.findViewById(R.id.text).setOnClickListener(this);
            setContentView(contentView);
        }

        @Override
        public void onClick(View v) {
            Rect rect = new Rect();
/*            v.getLocalVisibleRect(rect);
            LogUtil.d("silence", "MyPopWindow.getLocalVisibleRect(rect)=" + rect);*/

/*            v.getGlobalVisibleRect(rect);
            LogUtil.d("silence", "MyPopWindow,,,v.getGlobalVisibleRect(rect)=" + rect);*/
/*            v.getLocationOnScreen(array);
            LogUtil.d("silence", "MyPopWindow--v.getLocationOnScreen(rect)=" + array[0]+","+array[1]);*/
            v.getLocationInWindow(array);
            Log.d("silence", "v.getLocationInWindow(rect)=" + array[0] + "," + array[1]);
            Rect rectangle= new Rect();
            v.getWindowVisibleDisplayFrame(rectangle);
            Log.d("silence", "---recgetWindowVisibleDisplayFrame="+rectangle);

/*            //应用程序App区域宽高等尺寸获取
            Rect rect = new Rect();
            getContentView().getWindowVisibleDisplayFrame(rect);
            LogUtil.d("silence", "App区域  rect=" + rect);
            //获取状态栏高度
            Rect rectangle= new Rect();
            getContentView().getWindowVisibleDisplayFrame(rectangle);
            int statusBarHeight = rectangle.top;
            LogUtil.d("silence", "状态栏高度 statusBarHeight=" + statusBarHeight);*/
        }
    }

    private int[]mDrawingLocation =new int[2];
    private int[]mScreenLocation =new int[2];
    private int mPopupWidth=800,mPopupHeight=300;
    private boolean findDropDownPosition(View anchor, WindowManager.LayoutParams p,
                                         int xoff, int yoff) {

        anchor.getLocationInWindow(mDrawingLocation);//左上方点在当前窗口的位置
        p.x = mDrawingLocation[0] + xoff;
        p.y = mDrawingLocation[1] + anchor.getHeight() + yoff;

        boolean onTop = false;

        p.gravity = Gravity.LEFT | Gravity.TOP;

        anchor.getLocationOnScreen(mScreenLocation);//左上方点在屏幕上的位置
        final Rect displayFrame = new Rect();
        anchor.getWindowVisibleDisplayFrame(displayFrame);

        final View root = anchor.getRootView();
        if (p.y + mPopupHeight > displayFrame.bottom || p.x + mPopupWidth - root.getWidth() > 0) {
            // if the drop down disappears at the bottom of the screen. we try to
            // scroll a parent scrollview or move the drop down back up on top of
            // the edit box
            int scrollX = anchor.getScrollX();
            int scrollY = anchor.getScrollY();
            Rect r = new Rect(scrollX, scrollY,  scrollX + mPopupWidth + xoff,
                    scrollY + mPopupHeight + anchor.getHeight() + yoff);
            anchor.requestRectangleOnScreen(r, true);

            // now we re-evaluate the space available, and decide from that
            // whether the pop-up will go above or below the anchor.
            anchor.getLocationInWindow(mDrawingLocation);
            p.x = mDrawingLocation[0] + xoff;
            p.y = mDrawingLocation[1] + anchor.getHeight() + yoff;

            // determine whether there is more space above or below the anchor
            anchor.getLocationOnScreen(mScreenLocation);

            onTop = (displayFrame.bottom - mScreenLocation[1] - anchor.getHeight() - yoff) <
                    (mScreenLocation[1] - yoff - displayFrame.top);
            if (onTop) {
                p.gravity = Gravity.LEFT | Gravity.BOTTOM;
                p.y = root.getHeight() - mDrawingLocation[1] + yoff;
            } else {
                p.y = mDrawingLocation[1] + anchor.getHeight() + yoff;
            }
        }

        p.gravity |= Gravity.DISPLAY_CLIP_VERTICAL;

        return onTop;
    }
}
