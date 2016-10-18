package com.canmeizhexue.test.image;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by silence on 2016/4/3.
 */
public class MyTextView extends TextView {
    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.d("silence", "---onLayout-------" + left+" = "+top+" = "+right+" = "+bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action=ev.getAction() & MotionEvent.ACTION_MASK;
        String actionString="";
        switch (action){
            case MotionEvent.ACTION_DOWN:
                actionString="ACTION_DOWN";
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
        Log.d("silence", "---MyTextView----dispatchTouchEvent---" + actionString);
        return super.dispatchTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final float xf = event.getX();
        final float yf = event.getY();
        Log.d("silence", "---MyTextView----onTouchEvent---" + xf+",,,,,,===="+yf);
        int action=event.getAction() & MotionEvent.ACTION_MASK;
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
        Log.d("silence", "---MyTextView----onTouchEvent---" + actionString);
        return super.onTouchEvent(event);
    }
}
