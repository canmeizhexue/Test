package com.canmeizhexue.test.image;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.canmeizhexue.test.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by silence on 2016/4/3.
 */
public class TouchEventActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.scroll)
    TextView scroll;
    @Bind(R.id.container)
    MyLinearLayout container;
    @Bind(R.id.textview)
    MyTextView textview;
    RelativeLayout relative;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);
        ButterKnife.bind(this);
        findViewById(R.id.scroll).setOnClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction() & MotionEvent.ACTION_MASK;
        String actionString = "";
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                actionString = "ACTION_DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                actionString = "ACTION_MOVE";
                break;
            case MotionEvent.ACTION_UP:
                actionString = "ACTION_UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                actionString = "ACTION_CANCEL";
                break;
        }
        Log.d("silence", "---TouchEventActivity----onTouchEvent---" + actionString);
        return super.onTouchEvent(event);
    }

    @OnClick({R.id.scroll, R.id.container,R.id.textview})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scroll:
                container.scrollTo(0, 100);
                Log.d("silence", "container.scrollTo(0,100);");
                break;
            case R.id.container:
//                new Test(this).doLog();
                break;
            case R.id.textview:
                int measureWidth=view.getMeasuredWidth();
                int width = view.getWidth();
                Log.d("silence", "measureWidth="+measureWidth+"       ="+width);
                break;
        }
    }
}
