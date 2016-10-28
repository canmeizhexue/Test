package com.canmeizhexue.test.facade;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.canmeizhexue.test.R;
import com.canmeizhexue.test.base.BaseActivity;
import com.canmeizhexue.test.media.LightActivity;
import com.canmeizhexue.test.utils.LogUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * <p>QQMessageDialogActivity类 概述，qq锁屏聊天对话框,,,可以将屏幕壁纸设置为这个页面的背景</p>
 *
 * @author silence
 * @version 1.0 (2016-10-28 16:06)
 */
public class QQMessageDialogActivity extends BaseActivity {
    @Bind(R.id.send)
    TextView send;
    @Bind(R.id.root)
    LinearLayout root;
    BroadcastReceiver receiver;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("silence","QQMessageDialogActivity  onCreate");
        final Window win = getWindow();
        //四个标志位顾名思义，分别是锁屏状态下显示，解锁，保持屏幕长亮，打开屏幕
        win.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        setContentView(R.layout.activity_qq_message_dialog);
        ButterKnife.bind(this);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtil.d("silence","关闭页面");
                finish();
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QQMessageDialogActivity.this, LightActivity.class));
                finish();
            }
        });

        receiver = new ScreenLightReceiver();
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(receiver,intentFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //这个页面没关闭，新消息到来了，，更新页面，，，

        //防止用户没点击页面，直接又按锁屏页面了
        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        if (!pm.isScreenOn()) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP |
                    PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "bright");
            wl.acquire();
            wl.release();
        }
    }

    public class ScreenLightReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null){
                if(Intent.ACTION_SCREEN_OFF.equals(intent.getAction())){
                    LogUtil.d("silence","屏幕变暗");
                    send.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            LogUtil.d("silence","发送广播");
                            Intent intent = new Intent("com.canmeizhexue.test.messageReceiver");
                            sendBroadcast(intent);
                        }
                    },5000);
                }
                if(Intent.ACTION_SCREEN_ON.equals(intent.getAction())){
                    LogUtil.d("silence","屏幕变亮");
                }
            }

        }
    }
}
