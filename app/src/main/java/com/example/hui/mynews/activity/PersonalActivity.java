package com.example.hui.mynews.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hui.mynews.MainActivity;
import com.example.hui.mynews.R;
import com.example.hui.mynews.entity.MyUser;
import com.example.hui.mynews.utils.SharedUtils;
import com.example.hui.mynews.view.CustomDiglog;

import java.io.File;
import java.io.FileNotFoundException;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.hui.mynews.R.id.person_tou;

/**
 * Created by yanlongzh on 2017/2/10.
 */
public class PersonalActivity extends BaseActivity implements View.OnClickListener {
    private EditText person_introduce, person_sex, person_age, person_name;
    private Button person_bt, person_change;
    private ImageView person_icon;
    private boolean isChange;
    private CustomDiglog diaLog;
    private Button btn_pic, btn_cam, btn_cancel;
    private Uri image_uri;
    private static final int REQUST_PIC = 101;
    private static final int REQUST_CAM = 102;
    private static final int REQUST_RESULT = 103;
    private File file;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_layout);
        initView();
    }

    private void initView() {
        file = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
        image_uri = Uri.fromFile(file);
        person_icon = (ImageView) findViewById(person_tou);
        person_introduce = (EditText) findViewById(R.id.person_introduce);
        person_sex = (EditText) findViewById(R.id.person_sex);
        person_age = (EditText) findViewById(R.id.person_age);
        person_name = (EditText) findViewById(R.id.person_name);
        person_bt = (Button) findViewById(R.id.person_bt);
        person_change = (Button) findViewById(R.id.person_change);
        String uri = SharedUtils.getString(this,"Bitmap",null);
        if(uri!=null){
            try {
                person_icon.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(Uri.parse(uri))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        useEdit(false);

        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        person_age.setText(userInfo.getAge() + "");
        person_name.setText(userInfo.getUsername());
        boolean isMan = userInfo.getSex();
        if (isMan) {
            person_sex.setText("man");
        } else {
            person_sex.setText("girl");
        }


        diaLog = new CustomDiglog(this, 0, 0, R.style.CustomDialog, R.layout.person_imgae_layout,
                R.style.pop_anim_style, Gravity.BOTTOM);
        diaLog.setCanceledOnTouchOutside(true);
        btn_pic = (Button) diaLog.findViewById(R.id.btn_picture);
        btn_cam = (Button) diaLog.findViewById(R.id.btn_camera);
        btn_cancel = (Button) diaLog.findViewById(R.id.btn_cancel);
        person_icon.setOnClickListener(this);
        btn_pic.setOnClickListener(this);
        btn_cam.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        person_bt.setOnClickListener(this);
        person_change.setOnClickListener(this);
    }

    //    更改用户编辑资料状态
    private void useEdit(boolean is) {
        person_introduce.setEnabled(is);
        person_sex.setEnabled(is);
        person_age.setEnabled(is);
        person_name.setEnabled(is);
        // 改变编辑资料按钮状态
        isChange = is;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person_tou:
                diaLog.show();
                break;
            case R.id.btn_picture:
                //选择照片
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                //跳转相册
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUST_PIC);
                diaLog.dismiss();
                break;
            case R.id.btn_camera:
                capPic();
                diaLog.dismiss();
                break;
            case R.id.btn_cancel:
                diaLog.dismiss();
                break;
            case R.id.person_bt:
                BmobUser.logOut();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                if(MainActivity.instance!=null){
                    MainActivity.instance.finish();
                }
                break;
            case R.id.person_change:
                if (isChange) {
                    final String edit_introduce = person_introduce.getText().toString().trim();
                    final String edit_sex = person_sex.getText().toString().trim();
                    final String edit_age = person_age.getText().toString().trim();
                    String edit_name = person_name.getText().toString().trim();
                    if (!TextUtils.isEmpty(edit_sex) & !TextUtils.isEmpty(edit_name) & !TextUtils.isEmpty(edit_age)) {
                        final MyUser user = new MyUser();
                        if (!TextUtils.isEmpty(edit_introduce)) {
                            user.setIntroduce(edit_introduce);
                        }
                        user.setAge(Integer.parseInt(edit_age));
                        user.setUsername(edit_name);
                        if (edit_sex.equals("man")) {
                            user.setSex(true);
                        } else if (edit_sex.equals("girl")) {
                            user.setSex(false);
                        }
                        BmobUser bmobUser = BmobUser.getCurrentUser();
                        user.update(bmobUser.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(PersonalActivity.this, "更新用户信息成功", Toast.LENGTH_SHORT).show();
                                    person_change.setText("编辑资料");
                                    useEdit(false);
                                } else {
                                    Toast.makeText(PersonalActivity.this, "更新用户信息失败" +
                                            e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    person_change.setText("保存更改");
                    useEdit(true);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUST_PIC) {
                startCropPic(data.getData());
            } else if (requestCode == REQUST_RESULT) {
                if (data != null) {
                    setImage();
                }
            } else if (requestCode == REQUST_CAM) {
                startCropPic(image_uri);
            }
        }
    }

    //    拍照
    private void capPic() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        拍好的照片放入该路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(intent, REQUST_CAM);
    }

    //设置到头像
    private void setImage() {
        try {
            mBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(image_uri));
            person_icon.setImageBitmap(mBitmap);
            SharedUtils.putString(this,"Bitmap",image_uri+"");
            Intent intent = new Intent("com.example.hui.mynews");
            sendBroadcast(intent);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //裁剪照片
    private void startCropPic(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
//        保存截取后图片路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
//        返回false 返回uri，如果是true，返回bitmap
        intent.putExtra("return-data", false);
        startActivityForResult(intent, REQUST_RESULT);
    }
}
