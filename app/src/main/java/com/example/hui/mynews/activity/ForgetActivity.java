package com.example.hui.mynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hui.mynews.R;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yanlongzh on 2017/2/7.
 */
public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    private EditText old_password,new_password,new_password_again,forget_email;
    private Button forget_change_button,forget_password_button;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_layout);
        initView();
    }

    private void initView() {
        old_password = (EditText) findViewById(R.id.old_password);
        new_password = (EditText) findViewById(R.id.new_password);
        new_password_again = (EditText) findViewById(R.id.new_password_again);
        forget_email = (EditText) findViewById(R.id.forget_email);
        forget_change_button = (Button) findViewById(R.id.forget_change_button);
        forget_password_button = (Button) findViewById(R.id.forget_password_button);
        forget_change_button.setOnClickListener(this);
        forget_password_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_change_button:
                String old = old_password.getText().toString().trim();
                String newpass = new_password.getText().toString().trim();
                String new_again= new_password_again.getText().toString().trim();
                if(!TextUtils.isEmpty(old)&!TextUtils.isEmpty(newpass)&!TextUtils.isEmpty(new_again)){
                    if(newpass.equals(new_again)){
                        BmobUser.updateCurrentUserPassword(old, newpass, new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(ForgetActivity.this, "密码修改成功，请重新登陆", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ForgetActivity.this,LoginActivity.class));
                                    finish();
                                }else{
                                    Toast.makeText(ForgetActivity.this,e.toString()+"密码修改失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(ForgetActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ForgetActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.forget_password_button:
                final String email =forget_email.getText().toString().trim();
                if(!TextUtils.isEmpty(email)){
                    BmobUser.resetPasswordByEmail(email, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                Toast.makeText(ForgetActivity.this, "重置密码请求成功，请到 "+
                                        email + "邮箱进行密码重置操作", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(ForgetActivity.this, "失败:" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(ForgetActivity.this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
