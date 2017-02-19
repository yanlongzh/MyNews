package com.example.hui.mynews.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hui.mynews.R;
import com.example.hui.mynews.entity.MyUser;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by yanlongzh on 2017/2/10.
 */
public class PersonalActivity extends BaseActivity implements View.OnClickListener {
    private EditText person_introduce,person_sex,person_age,person_name;
    private Button person_bt,person_change;
    private ImageView person_icon;
    private boolean is;
    private boolean isChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);
        initView();
    }

    private void initView() {
        person_introduce= (EditText)findViewById(R.id.person_introduce);
        person_sex= (EditText)findViewById(R.id.person_sex);
        person_age= (EditText)findViewById(R.id.person_age);
        person_name= (EditText)findViewById(R.id.person_name);
        person_bt = (Button)findViewById(R.id.person_bt);
        person_change= (Button)findViewById(R.id.person_change);
        useEdit(false);

        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        person_age.setText(userInfo.getAge()+"");
        person_name.setText(userInfo.getUsername());
        boolean isMan = userInfo.getSex();
        if(isMan){
            person_sex.setText("man");
        }else {
            person_sex.setText("girl");
        }

        person_bt.setOnClickListener(this);
        person_change.setOnClickListener(this);
    }

    //    更改用户编辑资料状态
    private void useEdit(boolean is){
        person_introduce.setEnabled(is);
        person_sex.setEnabled(is);
        person_age.setEnabled(is);
        person_name.setEnabled(is);
        // 改变编辑资料按钮状态
        isChange = is;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.person_bt:
                BmobUser.logOut();
                BmobUser currentUser = BmobUser.getCurrentUser();
                startActivity(new Intent(this,LoginActivity.class));
               finish();
            case R.id.person_change:
                if(isChange){
                    final String edit_introduce = person_introduce.getText().toString().trim();
                    final String edit_sex = person_sex.getText().toString().trim();
                    final String edit_age = person_age.getText().toString().trim();
                    String edit_name = person_name.getText().toString().trim();
                    if(!TextUtils.isEmpty(edit_sex)&!TextUtils.isEmpty(edit_name)&!TextUtils.isEmpty(edit_age)){
                        final MyUser user = new MyUser();
                        if(!TextUtils.isEmpty(edit_introduce)){
                            user.setIntroduce(edit_introduce);
                        }
                        user.setAge(Integer.parseInt(edit_age));
                        user.setUsername(edit_name);
                        if (edit_sex.equals("man")){
                            user.setSex(true);
                        }else if(edit_sex.equals("girl")){
                            user.setSex(false);
                        }
                        BmobUser bmobUser = BmobUser.getCurrentUser();
                        user.update(bmobUser.getObjectId(),new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if(e==null){
                                    Toast.makeText(PersonalActivity.this,"更新用户信息成功", Toast.LENGTH_SHORT).show();
                                    person_change.setText("编辑资料");
                                    useEdit(false);
                                }else{
                                    Toast.makeText(PersonalActivity.this, "更新用户信息失败" +
                                            e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    person_change.setText("保存更改");
                    useEdit(true);
                }
        }
    }
}
