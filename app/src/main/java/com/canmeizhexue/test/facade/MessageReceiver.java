package com.canmeizhexue.test.facade;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * <p>MessageReceiver类 概述，演示消息接收广播</p>
 *
 * @author silence
 * @version 1.0 (2016-10-28 16:12)
 */
public class MessageReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d("silence", intent.getAction());
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        if (km.inKeyguardRestrictedInputMode()) {
            //当前是锁屏状态
            Intent alarmIntent = new Intent(context, QQMessageDialogActivity.class);
            alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(alarmIntent);
        }
    }
}
