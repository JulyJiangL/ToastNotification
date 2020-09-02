package com.android.toastnotificationlibrary.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

/**
 * 文字聊天实体的定义
 */
@Entity()
public class TextMessageEntity extends BaseResponse<List<TextMessageEntity>> implements TextChatType {
    @Id
    private Long id;
    private int userID;
    private int meID;
    private long time;
    private String message_info;
    private int message_type;
    private String image_url;
    private String nick_name;
    private String portrait;
    private int followed;
    private int age;
    private int read;
    private String nationality;
    private String about_me;
    private int online;
    private int self;
    private String date;
    private String other_nick_name;
    private String other_portrait;
    private int during;
    private boolean is_vip;

    @Transient
    private int states;
    @Transient
    private String temp_msg_info;

    @Generated(hash = 1133563130)
    public TextMessageEntity(Long id, int userID, int meID, long time, String message_info,
                             int message_type, String image_url, String nick_name, String portrait, int followed,
                             int age, int read, String nationality, String about_me, int online, int self, String date,
                             String other_nick_name, String other_portrait, int during, boolean is_vip) {
        this.id = id;
        this.userID = userID;
        this.meID = meID;
        this.time = time;
        this.message_info = message_info;
        this.message_type = message_type;
        this.image_url = image_url;
        this.nick_name = nick_name;
        this.portrait = portrait;
        this.followed = followed;
        this.age = age;
        this.read = read;
        this.nationality = nationality;
        this.about_me = about_me;
        this.online = online;
        this.self = self;
        this.date = date;
        this.other_nick_name = other_nick_name;
        this.other_portrait = other_portrait;
        this.during = during;
        this.is_vip = is_vip;
    }

    @Generated(hash = 1725748752)
    public TextMessageEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getMessage_info() {
        return this.message_info;
    }

    public void setMessage_info(String message_info) {
        this.message_info = message_info;
    }

    public int getMessage_type() {
        return this.message_type;
    }

    public void setMessage_type(int message_type) {
        this.message_type = message_type;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getNick_name() {
        return this.nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPortrait() {
        return this.portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getFollowed() {
        return this.followed;
    }

    public void setFollowed(int followed) {
        this.followed = followed;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getRead() {
        return this.read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAbout_me() {
        return this.about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public int getOnline() {
        return this.online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getSelf() {
        return this.self;
    }

    public void setSelf(int self) {
        this.self = self;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOther_nick_name() {
        return other_nick_name;
    }

    public void setOther_nick_name(String other_nick_name) {
        this.other_nick_name = other_nick_name;
    }

    public String getOther_portrait() {
        return other_portrait;
    }

    public void setOther_portrait(String other_portrait) {
        this.other_portrait = other_portrait;
    }

    public int getMeID() {
        return this.meID;
    }

    public void setMeID(int meID) {
        this.meID = meID;
    }

    public int getDuring() {
        return this.during;
    }

    public void setDuring(int during) {
        this.during = during;
    }

    public boolean isIs_vip() {
        return is_vip;
    }

    public void setIs_vip(boolean is_vip) {
        this.is_vip = is_vip;
    }

    public boolean getIs_vip() {
        return this.is_vip;
    }

    public int getStates() {
        return states;
    }

    public void setStates(int states) {
        this.states = states;
    }

    public String getTemp_msg_info() {
        return temp_msg_info;
    }

    public void setTemp_msg_info(String temp_msg_info) {
        this.temp_msg_info = temp_msg_info;
    }

}
