package com.canmeizhexue.test.image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**主要用来学习touch事件
 * Created by silence on 2016/4/3.
 */
public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action=ev.getAction() & MotionEvent.ACTION_MASK;
        String actionString="";
        switch (action){
            case MotionEvent.ACTION_DOWN:
                actionString="ACTION_DOWN";
//                return true;
                break;
            case MotionEvent.ACTION_MOVE:
                actionString="ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                actionString="ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                actionString="ACTION_CANCEL";
                break;
        }
        Log.d("silence","---MyLinearLayout----dispatchTouchEvent---"+actionString);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action=ev.getAction() & MotionEvent.ACTION_MASK;
        String actionString="";
        switch (action){
            case MotionEvent.ACTION_DOWN:
                actionString="ACTION_DOWN";
                break;
//                return true;
            case MotionEvent.ACTION_MOVE:
                actionString="ACTION_MOVE";
                break;
//                LogUtil.d("silence","---MyLinearLayout----onInterceptTouchEvent---"+actionString);
//                            return true;
            case MotionEvent.ACTION_UP:
                actionString="ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                actionString="ACTION_CANCEL";
                break;
        }
        Log.d("silence","---MyLinearLayout----onInterceptTouchEvent---"+actionString);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action=event.getAction() & MotionEvent.ACTION_MASK;
        String actionString="";
        switch (action){
            case MotionEvent.ACTION_DOWN:
                actionString="ACTION_DOWN";
                Log.d("silence","---MyLinearLayout----onTouchEvent---"+actionString);
//                break;
                return true;
            case MotionEvent.ACTION_MOVE:
                actionString="ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                actionString="ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                actionString="ACTION_CANCEL";
                break;
        }
        Log.d("silence","---MyLinearLayout----onTouchEvent---"+actionString);
        return super.onTouchEvent(event);
    }
}
