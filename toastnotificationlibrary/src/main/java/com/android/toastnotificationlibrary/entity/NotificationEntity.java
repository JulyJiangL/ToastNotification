package com.android.toastnotificationlibrary.entity;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NotificationEntity {


    @SerializedName("0")
    private List<NotificationBean> _$0;

    public List<NotificationBean> get_$0() {
        return _$0;
    }

    public void set_$0(List<NotificationBean> _$0) {
        this._$0 = _$0;
    }

    public static class NotificationBean {

        private int percent;
        private int id;
        private int type;
        private String nick_name;
        private String portrait;
        private int gender;
        private String about_me;
        private String nationality;
        private int online_setting;
        private boolean online_status;
        private int age;
        private int fans_num;
        private String msg;

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public String getAbout_me() {
            return about_me;
        }

        public void setAbout_me(String about_me) {
            this.about_me = about_me;
        }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }

        public int getOnline_setting() {
            return online_setting;
        }

        public void setOnline_setting(int online_setting) {
            this.online_setting = online_setting;
        }

        public boolean isOnline_status() {
            return online_status;
        }

        public void setOnline_status(boolean online_status) {
            this.online_status = online_status;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getFans_num() {
            return fans_num;
        }

        public void setFans_num(int fans_num) {
            this.fans_num = fans_num;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "NotificationBean{" +
                    "percent=" + percent +
                    ", id=" + id +
                    ", type=" + type +
                    ", nick_name='" + nick_name + '\'' +
                    ", portrait='" + portrait + '\'' +
                    ", gender=" + gender +
                    ", about_me='" + about_me + '\'' +
                    ", nationality='" + nationality + '\'' +
                    ", online_setting=" + online_setting +
                    ", online_status=" + online_status +
                    ", age=" + age +
                    ", fans_num=" + fans_num +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }
}
