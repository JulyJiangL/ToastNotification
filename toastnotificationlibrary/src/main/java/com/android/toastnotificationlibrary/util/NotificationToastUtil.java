package com.android.toastnotificationlibrary.util;

/**
 * 由于不能完全适配所有手机类型的横幅效果，用户手动去开启横幅，得不偿失，所以
 * 模拟利用Toast在顶部弹出Toast窗口产生虚假横幅
 */

import android.app.Activity;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.toastnotificationlibrary.R;
import com.android.toastnotificationlibrary.entity.NotificationEntity;
import com.android.toastnotificationlibrary.event.NotificationClickEvent;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.simple.eventbus.EventBus;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class NotificationToastUtil {

    private static View mContentView;
    private static Object mTnObj;
    private static float x1 = 0;
    private static float x2 = 0;
    private static float y1 = 0;
    private static float y2 = 0;

    public static void show(Activity mActivity,int notificationId, View contentView) {
        if (contentView == null || contentView.getParent() != null) {
            return;
        }
        showByToastToken(mActivity,notificationId, contentView);
    }


    private static void showByToastToken(final Activity mActivity, final int notificationId, View contentView) {
        try {
            mContentView = contentView;
            final Toast toast = Toast.makeText(contentView.getContext(), "", Toast.LENGTH_SHORT);
            Field mTNField = toast.getClass().getDeclaredField("mTN");
            mTNField.setAccessible(true);
            mTnObj = mTNField.get(toast);

            Field paramField = mTnObj.getClass().getDeclaredField("mParams");
            paramField.setAccessible(true);
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) paramField.get(mTnObj);
//
            params.windowAnimations = R.style.Notification_Toast;
//            params.width = WindowManager.LayoutParams.MATCH_PARENT;
//            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
            params.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

            contentView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickContentView(mActivity,v.getContext(), notificationId);
                    hideToast(mTnObj);
                }
            });

            contentView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        //当手指按下的时候
                        x1 = event.getX();
                        y1 = event.getY();
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        //当手指离开的时候
                        x2 = event.getX();
                        y2 = event.getY();
                        if (y1 - y2 > 50) {
//                            ToastUtils.showShort("向上滑");
                            if (isShow() && isTouchPointInView(x1, y1)) {
//                                touchHideWindow();
                                toast.cancel();
                            }
                        } else if (y2 - y1 > 50) {
//                            ToastUtils.showShort("向下滑");
                        } else if (x1 - x2 > 50) {
//                            ToastUtils.showShort("向左滑");
                        } else if (x2 - x1 > 50) {
//                            ToastUtils.showShort("向右滑");
                        }
                    }
                    return false;
                }
            });

            toast.setView(contentView);
            toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int page = 0;

    public static void toastTime(final Activity mActivity, final NotificationEntity entity) {
        Log.e("entry-toast",entity.get_$0().toString());
        int number = entity.get_$0().size() * 2100;
        /**
         * CountDownTimer timer = new CountDownTimer(number, 2100)中，
         * 第一个参数表示总时间，第二个参数表示间隔时间。
         * 意思就是每隔一秒会回调一次方法onTick，然后1秒之后会回调onFinish方法。
         */
        CountDownTimer timer = new CountDownTimer(number, 2100) {
            public void onTick(long millisUntilFinished) {
                if (page >= entity.get_$0().size()) return;
                View inflate = mActivity.getLayoutInflater().inflate(R.layout.view_notification_toast, null);
                Glide.with(mActivity).load(entity.get_$0().get(page).getPortrait()).apply(new RequestOptions().circleCrop()).into((ImageView) inflate.findViewById(R.id.notification_icon));
                TextView notification_title = inflate.findViewById(R.id.notification_title);
                notification_title.setText(entity.get_$0().get(page).getNick_name());
                TextView notification_content = inflate.findViewById(R.id.notification_content);
                notification_content.setText(entity.get_$0().get(page).getMsg());
                Log.e("librany_packname:", mActivity.getPackageName());
                NotificationToastUtil
                        .show(mActivity,entity.get_$0().get(page).getId(), inflate);
                page++;

            }

            public void onFinish() {
                page = 0;
            }
        };
        //调用 CountDownTimer 对象的 start() 方法开始倒计时，也不涉及到线程处理
        timer.start();
    }

    private static void clickContentView(Activity mActivity,Context context, int notificationId) {
        EventBus.getDefault().post(new NotificationClickEvent());
        Intent intentClick = new Intent(Utils.getApp(), mActivity.getClass());
//        intentClick.setAction(PushResultActivity.ACTION_CLICK);
        context.startActivity(intentClick);
        //remove notification
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
//        manager.cancel(notificationId);
        manager.cancelAll();
    }

    private static void hideToast(Object mTnObj) {
        try {
            Method handleHide = mTnObj.getClass()
                    .getDeclaredMethod("handleHide", (Class<?>[]) null);
            handleHide.setAccessible(true);
            handleHide.invoke(mTnObj);
            mContentView = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isShow() {
        if (mContentView != null) {
            return true;
        }
        return false;
    }

    //(x,y)是否在view的区域内
    private static boolean isTouchPointInView(float x, float y) {
        if (mContentView == null) {
            return false;
        }
        int[] location = new int[2];
        mContentView.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + mContentView.getMeasuredWidth();
        int bottom = top + mContentView.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;
    }

    private static void touchHideWindow() {
        if (mTnObj != null) {
            try {
                Method handleHide = mTnObj.getClass()
                        .getDeclaredMethod("handleHide", (Class<?>[]) null);
                handleHide.setAccessible(true);
                handleHide.invoke(mTnObj);
                mContentView = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
