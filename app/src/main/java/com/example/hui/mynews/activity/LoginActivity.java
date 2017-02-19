package com.example.hui.mynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hui.mynews.MainActivity;
import com.example.hui.mynews.R;
import com.example.hui.mynews.entity.MyUser;
import com.example.hui.mynews.view.CustomDiglog;
import com.example.hui.mynews.utils.SharedUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by yanlongzh on 2017/2/7.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login,register;
    private CheckBox keepPassword;
    private TextView forgerPassword;
    private EditText username,password;
    private String name;
    private String pass;
    private boolean isFristIn= false;
    private boolean isLogin = false;
    private CustomDiglog mDiglog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        SharedUtils.putBoolean(this,"isFirst",false);
        initView();
        initGuide();
    }

    private void initGuide() {
        isFristIn = SharedUtils.getBoolean(this,"isFirst",true);
        /*
        * 记得修改*/
        if(false){
            Intent mintent = new Intent();
            mintent.setClass(this, GuideActivity.class);
            startActivity(mintent);
        }
    }

    private void initView() {
        mDiglog = new CustomDiglog(this,100,100, R.style.CustomDialog,R.layout.login_dialog,
                Gravity.CENTER,R.style.pop_anim_style);
        mDiglog.setCancelable(false);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        login = (Button) findViewById(R.id.log_button);
        register = (Button) findViewById(R.id.regis_button);
        keepPassword = (CheckBox) findViewById(R.id.keep_password);
        forgerPassword = (TextView) findViewById(R.id.forget_password);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        keepPassword.setOnClickListener(this);
        forgerPassword.setOnClickListener(this);
        if(SharedUtils.getBoolean(this,"ischeck",false)){
            keepPassword.setChecked(true);
            username.setText(SharedUtils.getString(this,"username",""));
            password.setText(SharedUtils.getString(this,"password",""));
        }
    }

  @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.log_button:
                mDiglog.show();
                 name = username.getText().toString().trim();
                 pass = password.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&!TextUtils.isEmpty(pass)){
                   final MyUser myuser = new MyUser();
                        myuser.setUsername(name);
                        myuser.setPassword(pass);
                        myuser.login(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if(e==null){
                                    mDiglog.dismiss();
                                    if(myuser.getEmailVerified()){
                                        isLogin = true;
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                            Toast.makeText(LoginActivity.this, "请到邮箱进行验证", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    mDiglog.dismiss();
                                    Toast.makeText(LoginActivity.this, "账号或密码错误", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    } else {
                    Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    mDiglog.dismiss();
                }
                break;
            case R.id.regis_button:
                startActivity(new Intent(this,RegisiterActivity.class));
                break;
            case R.id.forget_password:
                startActivity(new Intent(this,ForgetActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(keepPassword.isChecked()&isLogin){
            SharedUtils.putBoolean(this,"ischeck",keepPassword.isChecked());
            SharedUtils.putString(this,"username",name);
            SharedUtils.putString(this,"password",pass);
        }
    }
}
