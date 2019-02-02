package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.RegisteredBean;
import com.mouqukeji.hmdeer.contract.activity.SignUpContract;
import com.mouqukeji.hmdeer.modle.activity.SignUpModel;
import com.mouqukeji.hmdeer.presenter.activity.SignUpPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CodeUtil;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.LoginStatus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity<SignUpPresenter, SignUpModel> implements SignUpContract.View, View.OnClickListener {
    private static final String TAG = "SignUpActivity";
    @BindView(R.id.action_bar)
    MyActionBar actionBar;
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.button_get_captcha)
    Button buttonGetCaptcha;
    @BindView(R.id.regeister_look)
    ImageView regeisterLook;
    @BindView(R.id.regeister_unlook)
    ImageView regeisterUnlook;
    @BindView(R.id.regeister_islook)
    LinearLayout regeisterIslook;
    @BindView(R.id.button_sign_up)
    Button buttonSignUp;
    @BindView(R.id.singin_weixin)
    ImageView singinWeixin;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;


    private boolean flag = true;
    private String number;
    private String code;
    private String password;


    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void setUpView() {
        //设置点击事件
        initListener();
    }

    private void initListener() {
        buttonGetCaptcha.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        imageButton.setOnClickListener(this);
        regeisterIslook.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_get_captcha:
                number = editText1.getText().toString();
                if (CodeUtil.isPhone(number)) {
                    //获取验证码
                    mMvpPresenter.getCode(this, number, "1", mMultipleStateView);
                } else {
                    Toast.makeText(SignUpActivity.this, "请输入正确格式的手机号码", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_sign_up:
                code = editText2.getText().toString();
                password = editText3.getText().toString();
                //注册
                setRegistered();
                break;
            case R.id.regeister_islook:
                if (flag) {
                    flag = false;
                    regeisterLook.setVisibility(View.INVISIBLE);
                    editText3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    flag = true;
                    regeisterLook.setVisibility(View.VISIBLE);
                    editText3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            case R.id.imageButton:
                finish();
                break;
        }
    }

    private void setRegistered() {
        if (CodeUtil.isPassword(password) && CodeUtil.isVf(code) && CodeUtil.isPhone(number)) {
            //注册
            mMvpPresenter.registered(number, code, password, mMultipleStateView);
        } else {
            if (!CodeUtil.isPhone(number)) {
                Toast toast = Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT);
                toast.setText("请输入正确格式的手机号码");
                toast.show();
            } else if (!CodeUtil.isVf(code)) {
                Toast toast = Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT);
                toast.setText("请输入四位验证码");
                toast.show();
            } else if (!CodeUtil.isPassword(password)) {
                Toast toast = Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT);
                toast.setText("请输入6-16位字母数字混合密码,首位不为数字");
                toast.show();
            } else {
                Log.i(TAG, "onClick: 注册未知错误");
                Toast toast = Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT);
                toast.setText("注册未知错误");
                toast.show();
            }
        }
    }



    @Override
    public void getCode(CodeBean bean) {
        Toast.makeText(SignUpActivity.this, "成功发送短信验证码", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registered(RegisteredBean bean) {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);
        finish();
      }

    @Override
    public void isRegistered() {
        Toast.makeText(this, "手机号已注册", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isSend() {
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
    }


}
