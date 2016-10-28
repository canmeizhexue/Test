package com.canmeizhexue.test.facade;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;
import com.canmeizhexue.test.utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <p>QQMessageTriggerActivity类 概述，触发消息接收器</p>
 *
 * @author silence
 * @version 1.0 (2016-10-28 16:21)
 */
public class QQMessageTriggerActivity extends BaseActivity {
    @Bind(R.id.test)
    TextView test;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qq_message_trigger);
        ButterKnife.bind(this);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("silence","点击了按钮,");
                test.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        LogUtil.d("silence","发送广播");
                        Intent intent = new Intent("com.canmeizhexue.test.messageReceiver");
                        sendBroadcast(intent);
                    }
                },5000);

            }
        });
    }
}
