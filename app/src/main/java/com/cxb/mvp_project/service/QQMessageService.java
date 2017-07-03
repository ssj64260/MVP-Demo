package com.cxb.mvp_project.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.cxb.mvp_project.R;
import com.cxb.mvp_project.utils.ThreadPoolUtil;

import java.util.concurrent.TimeUnit;

/**
 * qq消息服务
 */

public class QQMessageService extends Service {
    private final int REQUEST_CODE_QQ_MESSAGE = 0;//QQ消息通知

    String[] messages = {
            "我在睡觉",
            "我在打龙之谷地狱巢穴",
            "我在写Android代码",
            "私はワンパンマンアニメーションを見ていました"
    };
    private int messageNumber = 0;//消息数量
    private int startId;

    private NotificationManager mNotificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        ThreadPoolUtil.getInstache().scheduledRate(new Runnable() {
            @Override
            public void run() {
                sendQQMessageNotification();
            }
        }, 5, 60, TimeUnit.SECONDS);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        messageNumber = 0;
        this.startId = startId;
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mNotificationManager.cancel(REQUEST_CODE_QQ_MESSAGE);
        ThreadPoolUtil.getInstache().scheduledShutDown(0);
    }

    private void sendQQMessageNotification() {
        if (messageNumber > 2) {
            return;
        }

        messageNumber++;
        String message = messages[(int) (Math.random() * 10) % 4];

        Notification.Builder mBuilder = new Notification.Builder(this);
        mBuilder.setTicker("收到了一条消息")
                .setContentTitle("收到了一条消息")
                .setContentText(message)
                .setSmallIcon(R.drawable.family_avatar)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.family_avatar))
                .setWhen(System.currentTimeMillis())
                .setShowWhen(true)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setLights(0xff00ff00, 2000, 2000)
                .setVibrate(new long[]{0, 300, 200, 300});

        Notification notification = mBuilder.build();
        mNotificationManager.notify(REQUEST_CODE_QQ_MESSAGE, notification);
    }
}
