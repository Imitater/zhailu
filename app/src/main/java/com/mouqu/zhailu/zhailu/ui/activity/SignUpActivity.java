package com.mouqu.zhailu.zhailu.ui.activity;

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

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.bean.RegisteredBean;
import com.mouqu.zhailu.zhailu.contract.activity.SignUpContract;
import com.mouqu.zhailu.zhailu.modle.activity.SignUpModel;
import com.mouqu.zhailu.zhailu.presenter.activity.SignUpPresenter;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.CodeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends BaseActivity<SignUpPresenter, SignUpModel> implements SignUpContract.View, View.OnClickListener {
    private static final String TAG = "SignUpActivity";
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.ll_cancel_signup)
    LinearLayout llCancelSignup;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.button_get_captcha)
    Button buttonGetCaptcha;
    @BindView(R.id.view_click_replace)
    View viewClickReplace;
    @BindView(R.id.imageView_zhengkai)
    ImageView imageViewZhengkai;
    @BindView(R.id.imageView_bishang)
    ImageView imageViewBishang;
    @BindView(R.id.button_sign_up)
    Button buttonSignUp;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.circle_weixin)
    CircleImageView circleWeixin;
    @BindView(R.id.action_bar)
    MyActionBar actionBar;


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
        viewClickReplace.setOnClickListener(this);
        llCancelSignup.setOnClickListener(this);
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
                    mMvpPresenter.getCode(this, number, mMultipleStateView);
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
            case R.id.view_click_replace:
                //这里是密码的可见不可见设置
                if (flag) {
                    //当前代码可见,将其设置为不可见,并且隐藏眼睛图片
                    flag = false;
                    ImageView imageViewZhengkai2 = findViewById(R.id.imageView_zhengkai);
                    imageViewZhengkai2.setVisibility(View.INVISIBLE);
                    editText3.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    //当前代码不可见,将其设置为可见,并且显示眼睛图片
                    flag = true;
                    ImageView imageViewZhengkai2 = findViewById(R.id.imageView_zhengkai);
                    imageViewZhengkai2.setVisibility(View.VISIBLE);
                    editText3.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                break;
            case R.id.ll_cancel_signup:
                finish();
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void setRegistered() {
        if (CodeUtil.isPassword(password) && CodeUtil.isVf(code) && CodeUtil.isPhone(number)) {
            //注册
            mMvpPresenter.registered(number, code, password, mMultipleStateView);
        } else {
            if (!CodeUtil.isPassword(password)) {
                Toast toast = Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT);
                toast.setText("请输入6-16位字母数字混合密码,首位不为数字");
                toast.show();
            } else if (!CodeUtil.isVf(code)) {
                Toast toast = Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT);
                toast.setText("请输入四位验证码");
                toast.show();
            } else if (!CodeUtil.isPhone(number)) {
                Toast toast = Toast.makeText(SignUpActivity.this, "", Toast.LENGTH_SHORT);
                toast.setText("请输入正确格式的手机号码");
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
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }


    @Override
    public void getCode(CodeBean bean) {
        Toast.makeText(SignUpActivity.this, "成功发送短信验证码", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void registered(RegisteredBean bean) {

    }

}
