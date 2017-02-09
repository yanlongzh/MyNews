package com.example.hui.mynews.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hui.mynews.R;
import com.example.hui.mynews.entity.MyUser;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * Created by yanlongzh on 2017/2/7.
 */
public class RegisiterActivity extends BaseActivity implements View.OnClickListener {

    private EditText regisiter_password, regisiter_age, regisiter_introduce, regisiter_pass_again, regisiter_email,regisiter_username;
    private RadioGroup regisiter_RadioGroup;
    private RadioButton check_man,check_girl;
    private Button regisiter_button,login_button;
    private boolean sex = true;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regisiter_layout);
        initView();
    }

    public void initView() {
        regisiter_RadioGroup = (RadioGroup) findViewById(R.id.regisiter_radioGroup);
        check_man = (RadioButton) findViewById(R.id.check_men);
        check_girl = (RadioButton) findViewById(R.id.check_girl);
        regisiter_username = (EditText) findViewById(R.id.regisiter_username);
        regisiter_password = (EditText) findViewById(R.id.regisiter_password);
        regisiter_age = (EditText) findViewById(R.id.regisiter_age);
        regisiter_introduce = (EditText) findViewById(R.id.regisiter_introduce);
        regisiter_pass_again = (EditText) findViewById(R.id.regisiter_pass_again);
        regisiter_email = (EditText) findViewById(R.id.regisiter_email);
        regisiter_button = (Button) findViewById(R.id.regisiter_button);
        regisiter_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regisiter_button:
                String name = regisiter_username.getText().toString().trim();
                String password = regisiter_password.getText().toString().trim();
                String age = regisiter_age.getText().toString().trim();
                String introduce = regisiter_introduce.getText().toString().trim();
                String pass_again = regisiter_pass_again.getText().toString().trim();
                final String myemail = regisiter_email.getText().toString().trim();

                if(!TextUtils.isEmpty(name)&!TextUtils.isEmpty(age)&!
                        TextUtils.isEmpty(password)&!TextUtils.isEmpty(pass_again)&!TextUtils.isEmpty(myemail)){
                    if(pass_again.equals(password)){
                        regisiter_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if(checkedId == R.id.check_men){
                                    sex = true;
                                }else if(checkedId == R.id.check_girl){
                                    sex = false;
                                }
                            }
                        });
                        if(TextUtils.isEmpty(introduce)){
                            regisiter_introduce.setText("这家伙很懒，什么也没有留下");
                        }

                        final MyUser myuser = new MyUser();
                        myuser.setUsername(name);
                        myuser.setAge(Integer.parseInt(age));
                        myuser.setSex(sex);
                        myuser.setPassword(password);
                        myuser.setIntroduce(introduce);
                        myuser.setEmail(myemail);

                        myuser.signUp(new SaveListener<MyUser>() {
                            @Override
                            public void done(MyUser myUser, BmobException e) {
                                if(e==null){
                                    myuser.requestEmailVerify(myemail, new UpdateListener() {
                                        @Override
                                        public void done(BmobException e) {
                                            if(e==null){
                                                Toast.makeText(RegisiterActivity.this, "注册成功，请到" + myemail + "邮箱中进行激活。", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }else{
                                                Toast.makeText(RegisiterActivity.this, "失败", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }else{
                                    Toast.makeText(RegisiterActivity.this,"注册失败"+e.toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
