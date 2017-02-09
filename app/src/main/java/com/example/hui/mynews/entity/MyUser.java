package com.example.hui.mynews.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by yanlongzh on 2017/2/7.
 *
 */

public class MyUser extends BmobUser {
    private String name;
    private int age;
    private boolean sex;
    private String introduce;


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
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
}
