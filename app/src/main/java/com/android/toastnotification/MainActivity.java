package com.android.toastnotification;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.toastnotificationlibrary.NotificationToast;
import com.android.toastnotificationlibrary.entity.NotificationEntity;
import com.android.toastnotificationlibrary.util.NotificationView;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * percent : 98
     * id : 391272
     * type : 1
     * nick_name : Neisiat Casey
     * portrait : https://lh4.googleusercontent.com/-V5AdYMZ3MQQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZu  ucnfOcsRcf7nu0QVyTu5abd0hPYwHQ/photo.jpg
     * gender : 1
     * about_me :
     * nationality :
     * online_setting : 1
     * online_status : false
     * age : 18
     * fans_num : 0
     * msg : eeeeeeeeeeeeeeeee
     */
    private int percent = 98;
    private int id = 391272;
    private int type = 1;
    private String nick_name = " Neisiat Casey";
    private String portrait = "https://lh4.googleusercontent.com/-V5AdYMZ3MQQ/AAAAAAAAAAI/AAAAAAAAAAA/AMZuucnfOcsRcf7nu0QVyTu5abd0hPYwHQ/photo.jpg";
    private int gender = 1;
    private String about_me;
    private String nationality;
    private int online_setting = 1;
    private boolean online_status = false;
    private int age = 18;
    private int fans_num = 0;
    private String msg = "eeeeeeeeeeeeeeeee";
    private static final String EventName = "NotificationClickEvent";
    List<NotificationEntity.NotificationBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        findViewById(R.id.tv_click_notification).setOnClickListener(this);
        findViewById(R.id.btn_show_db).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_click_notification:
                NotificationEntity entity = new NotificationEntity();
                NotificationEntity.NotificationBean bean = new NotificationEntity.NotificationBean();
                bean.setMsg(msg);
                bean.setNick_name(nick_name);
                bean.setAbout_me(about_me);
                bean.setAge(age);
                bean.setFans_num(fans_num);
                bean.setGender(gender);
                bean.setId(id);
                bean.setNationality(nationality);
                bean.setOnline_setting(online_setting);
                bean.setOnline_status(online_status);
                bean.setPercent(percent);
                bean.setPortrait(portrait);
                bean.setType(type);
                list.add(bean);
                entity.set_$0(list);
                NotificationToast.getInstance().NotificationToast(MainActivity.this, R.drawable.ic_launcher, entity,true);
                break;
            case R.id.btn_show_db:
                List<NotificationEntity.NotificationBean> list2 = new ArrayList<>();
                NotificationEntity entity2 = new NotificationEntity();
                NotificationEntity.NotificationBean bean2 = new NotificationEntity.NotificationBean();
                bean2.setMsg(msg);
                bean2.setNick_name(nick_name);
                bean2.setAbout_me(about_me);
                bean2.setAge(age);
                bean2.setFans_num(fans_num);
                bean2.setGender(gender);
                bean2.setId(id);
                bean2.setNationality(nationality);
                bean2.setOnline_setting(online_setting);
                bean2.setOnline_status(online_status);
                bean2.setPercent(percent);
                bean2.setPortrait(portrait);
                bean2.setType(type);
                list2.add(bean2);
                entity2.set_$0(list2);
                NotificationToast.getInstance().NotificationToast(MainActivity.this, R.drawable.ic_launcher, entity2,false);
                break;
        }
    }

    @Subscriber(tag = EventName)
    private void onEvent() {
        Toast.makeText(this, EventName, Toast.LENGTH_SHORT).show();
        Log.e(MainActivity.this.getPackageName(), EventName);
    }
}
