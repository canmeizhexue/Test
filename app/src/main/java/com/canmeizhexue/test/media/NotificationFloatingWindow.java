package com.canmeizhexue.test.media;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.utils.LogUtil;

/**
 * <p>NotificationFloatingWindow类 概述，提供XXX功能</p>
 *
 * @author silence
 * @version 1.0 (2016-10-27 14:48)
 */
public class NotificationFloatingWindow {
    public  void showNotification(Context context){
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater layoutInflater = LayoutInflater.from(context.getApplicationContext());
        //从popwindow复制过来的
        DecorView decorView = new DecorView(context.getApplicationContext());
        View content = layoutInflater.inflate(R.layout.custom_notification,null);
        decorView.addView(content);
        content = decorView;
        content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("silence","-------click");
            }
        });
        content.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.d("silence","-------onTouch");
                return false;
            }
        });
        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams();
        //窗口类型，app能够使用的类型有限，有些类型还需要权限，有些系统默认会将悬浮窗权限禁止掉
        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST; // type是关键，这里的2002表示系统级窗口，你也可以试试2003。</strong>

        //窗口的额外属性信息

        //FLAG_NOT_TOUCH_MODAL 如果不同时设置FLAG_NOT_FOCUSABLE，那么出现的效果就是，这个窗口之外的touch事件会发送给后面的窗口。
        // 如果同时设置了FLAG_NOT_FOCUSABLE，那么所有的touch事件都会发送给本窗口(实际测试结果是窗口外的依然发送给后面的窗口，窗口内的发送给自己)
//        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        //WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE  无法接收touch事件，所有touch事件会发到后面的窗口
//        wmParams.flags =WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;


        //WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE 主要是针对键盘事件，设置这个标记，这个窗口就不会接收键盘事件。不设置这个标记，键盘事件就会分发给下面其他可以接收键盘事件的窗口
        //一般如果没有键盘事件的话，会隐含不需要输入法，可以和FLAG_ALT_FOCUSABLE_IM配合使用来达到可以使用输入法的目的
        wmParams.flags =WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        wmParams.format = PixelFormat.TRANSLUCENT;
        wmParams.gravity = Gravity.BOTTOM;
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height = 400;
//        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        wmParams.y = 20; // 取决于gravity,可能忽略，表示距离边缘的距离
//         wmParams.windowAnimations = R.style.notification_anim_style;   //还可以自定义弹出，消失动画</strong>
        windowManager.addView(content, wmParams);

/*
        try {
            LayoutInflater inflater = LayoutInflater.from(context);
            View content =   inflater.inflate(R.layout.custom_notification, null);

            WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_APPLICATION,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                            | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    PixelFormat.TRANSLUCENT);
            WindowManager windowManager = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            windowManager.addView(content, lp);
            content.setVisibility(View.VISIBLE);
        }catch (Exception e){

        }*/
    }

    private class DecorView extends FrameLayout {


        public DecorView(Context context) {
            super(context);
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent event) {
            LogUtil.d("silence","---dispatchKeyEvent--");

            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                if (getKeyDispatcherState() == null) {
                    return super.dispatchKeyEvent(event);
                }

                if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                    final KeyEvent.DispatcherState state = getKeyDispatcherState();
                    if (state != null) {
                        state.startTracking(event, this);
                    }
                    return true;
                } else if (event.getAction() == KeyEvent.ACTION_UP) {
                    final KeyEvent.DispatcherState state = getKeyDispatcherState();
                    if (state != null && state.isTracking(event) && !event.isCanceled()) {
//                        dismiss();
//                        return true;
                    }
                }
                return super.dispatchKeyEvent(event);
            } else {
                return super.dispatchKeyEvent(event);
            }
        }

        @Override
        public boolean dispatchTouchEvent(MotionEvent ev) {
            return super.dispatchTouchEvent(ev);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();

            if ((event.getAction() == MotionEvent.ACTION_DOWN)
                    && ((x < 0) || (x >= getWidth()) || (y < 0) || (y >= getHeight()))) {

//                dismiss();
                return true;
            } else if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
//                dismiss();
                return true;
            } else {
                return super.onTouchEvent(event);
            }
        }


    }
}
