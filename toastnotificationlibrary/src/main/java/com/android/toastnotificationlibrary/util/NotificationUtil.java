package com.android.toastnotificationlibrary.util;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.android.toastnotificationlibrary.entity.NotificationEntity;
import com.blankj.utilcode.util.Utils;

public class NotificationUtil {

    private static NotificationUtil mNotificationUtil;
    private String ChannelName = "channel";

    public static NotificationUtil getInstance() {
        if (mNotificationUtil == null) {
            synchronized (NotificationUtil.class) {
                if (mNotificationUtil == null) {
                    mNotificationUtil = new NotificationUtil();
                }
            }
        }
        return mNotificationUtil;
    }

    public void showNotification(Activity mActivity, int icon, NotificationEntity.NotificationBean bean) {
        if (mActivity != null) {
            int notificationId = bean.getId();
            NotificationManager notificationManager = (NotificationManager) mActivity.getSystemService(mActivity.NOTIFICATION_SERVICE);
            Intent intent = new Intent(Utils.getApp(), mActivity.getClass());
            intent.putExtra("notification", true);
            PendingIntent pendingIntent = PendingIntent.getActivity(mActivity, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(mActivity)
                    .setContentTitle(bean.getNick_name())
                    .setContentText(bean.getMsg())
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(icon)
                    .setVibrate(new long[]{0, 1000, 1000, 1000})//震动
//                    .setAutoCancel(true)
//                    .setFullScreenIntent(pendingIntent,true)
                    .setContentIntent(pendingIntent)
//                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    ;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //ChannelId为"001",ChannelName为"my_channel"
                NotificationChannel channel = new NotificationChannel(String.valueOf(notificationId),
                        ChannelName, NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(true); //是否在桌面icon右上角展示小红点
                channel.setLightColor(Color.GREEN); //小红点颜色
                channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知
                notificationManager.createNotificationChannel(channel);

                builder.setChannelId(String.valueOf(notificationId));

            }

            notificationManager.notify(notificationId, builder.build());

        }

    }
}
