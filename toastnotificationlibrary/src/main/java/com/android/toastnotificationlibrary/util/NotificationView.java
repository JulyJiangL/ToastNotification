package com.android.toastnotificationlibrary.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.toastnotificationlibrary.R;
import com.android.toastnotificationlibrary.entity.NotificationEntity;
import com.android.toastnotificationlibrary.event.NotificationClickEvent;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.simple.eventbus.EventBus;

public class NotificationView {

    private static View mContentView;
    private static int page = 0;
    private static float x1 = 0;
    private static float x2 = 0;
    private static float y1 = 0;
    private static float y2 = 0;

    private static void showView(final Activity mActivity, final int notificationId, final View view) {
        mContentView = view;
        /*创建提示消息View*/
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, getStatusBarHeight(), 0, 0);
        view.setLayoutParams(layoutParams);
        /*创建属性动画,从显示到隐藏*/
        ObjectAnimator bottomToTop = ObjectAnimator.ofFloat(view, "alpha", 1, 0).setDuration(500);
        /*创建属性动画,从隐藏到显示*/
        ObjectAnimator topToBottom = ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(500);
        /*初始化动画组合器*/
        AnimatorSet animator = new AnimatorSet();
        /*组合动画*/
        animator.play(bottomToTop).after(topToBottom).after(2000);
        /*添加动画结束回调*/
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                /*删除View*/
                hideView(mActivity, view);
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickContentView(mActivity,view.getContext(),notificationId);
                hideView(mActivity,view);
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
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
                            hideView(mActivity,view);
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
        /*添加View到当前显示的Activity*/
        showVie(mActivity, view);
        /*启动动画*/
        animator.start();
    }

    /**
     * 隐藏View
     *
     * @param view 需要从Activity中移除的视图
     */
    private static void hideView(Activity mActivity, View view) {
        /*Activity不为空并且没有被释放掉*/
        if (mActivity != null && !mActivity.isFinishing()) {
            /*获取Activity顶层视图*/
            ViewGroup root = ((ViewGroup) mActivity.getWindow().getDecorView());
            /*如果Activity中存在View对象则删除*/
            if (root.indexOfChild(view) != -1) {
                /*从顶层视图中删除*/
                root.removeView(view);
                mContentView = null;
            }
        }
    }

    /**
     * 显示View
     *
     * @param view 需要显示到Activity的视图
     */
    private static void showVie(Activity mActivity, View view) {
        /*Activity不为空并且没有被释放掉*/
        if (mActivity != null && !mActivity.isFinishing()) {
            /*获取Activity顶层视图,并添加自定义View*/
            ((ViewGroup) mActivity.getWindow().getDecorView()).addView(view);
        }
    }

    /**
     * 从dp单位转换为px
     *
     * @param dp dp值
     * @return 返回转换后的px值
     */
    private static int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private static int getStatusBarHeight() {
        Resources resources = Utils.getApp().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    public static void toastTime(final Activity mActivity, final NotificationEntity entity) {
        Log.e("entry-view",entity.get_$0().toString());
        int number = entity.get_$0().size() * 2600;
        /**
         * CountDownTimer timer = new CountDownTimer(number, 2600)中，
         * 第一个参数表示总时间，第二个参数表示间隔时间。
         * 意思就是每隔一秒会回调一次方法onTick，然后2.6秒之后会回调onFinish方法。
         */
        CountDownTimer timer = new CountDownTimer(number, 2600) {
            public void onTick(long millisUntilFinished) {
                if (page >= entity.get_$0().size()) return;
                View inflate = mActivity.getLayoutInflater().inflate(R.layout.view_notification_toast, null);
                Glide.with(Utils.getApp()).load(entity.get_$0().get(page).getPortrait()).apply(new RequestOptions().circleCrop()).into((ImageView) inflate.findViewById(R.id.notification_icon));
                TextView notification_title = inflate.findViewById(R.id.notification_title);
                notification_title.setText(entity.get_$0().get(page).getNick_name());
                TextView notification_content = inflate.findViewById(R.id.notification_content);
                notification_content.setText(entity.get_$0().get(page).getMsg());
                NotificationView.showView(mActivity,entity.get_$0().get(page).getId(),inflate);
                page++;

            }

            public void onFinish() {
                page = 0;
            }
        };
        //调用 CountDownTimer 对象的 start() 方法开始倒计时，也不涉及到线程处理
        timer.start();
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

    private static void clickContentView(Activity mActivity, Context context, int notificationId) {
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

}
