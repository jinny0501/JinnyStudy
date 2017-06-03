package com.example.yujin_000.a22_activity;

import java.io.Serializable;

/**
 * Created by yujin_000 on 2017-06-02.
 */
/*
* Intent에 이 클래스의 객체를 전달하기 위해서는 Serializable을 상속받는다.
* */
public class UserInfo implements Serializable{
    public String user_name;
    public String user_email;
    public String user_phone;
}
