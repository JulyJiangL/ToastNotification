package com.android.toastnotificationlibrary;

import android.app.Activity;

import com.android.toastnotificationlibrary.entity.NotificationEntity;
import com.android.toastnotificationlibrary.util.NotificationToastUtil;
import com.android.toastnotificationlibrary.util.NotificationUtil;
import com.android.toastnotificationlibrary.util.NotificationView;

public class NotificationToast {

    private static NotificationToast mNotificationUtil;

    public static NotificationToast getInstance() {
        if (mNotificationUtil == null) {
            synchronized (NotificationToast.class) {
                if (mNotificationUtil == null) {
                    mNotificationUtil = new NotificationToast();
                }
            }
        }
        return mNotificationUtil;
    }

    /**
     * 调用ToastUtilNotification
     */
    public void NotificationToast(Activity mActivity, int icon, NotificationEntity entity, Boolean isToast) {
        if (entity.get_$0() != null && entity.get_$0().size() > 0) {
            for (NotificationEntity.NotificationBean bean : entity.get_$0()) {
                NotificationUtil.getInstance().showNotification(mActivity, icon, bean);
            }
//            if (isToast) {
//                NotificationToastUtil.toastTime(mActivity, entity);
//            } else {
            NotificationView.toastTime(mActivity, entity);
//            }
        }
    }
}
