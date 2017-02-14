package com.example.hui.mynews.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by yanlongzh on 2017/2/7.
 *
 */

public class MyUser extends BmobUser {
    private String nickName;
    private int age;
    private boolean sex;
    private String introduce;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }


    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
