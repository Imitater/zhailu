package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.contract.activity.SignInContract;
import com.mouqukeji.hmdeer.modle.activity.SigninModel;
import com.mouqukeji.hmdeer.presenter.activity.SignInPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CodeUtil;
import com.mouqukeji.hmdeer.util.LoginStatus;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignInActivity extends BaseActivity<SignInPresenter, SigninModel> implements SignInContract.View, View.OnClickListener {
    private static final String TAG = "SignInActivity";
    @BindView(R.id.actionbar)
    MyActionBar actionbar;
    @BindView(R.id.singin_number)
    EditText singinNumber;
    @BindView(R.id.singin_password)
    EditText singinPassword;
    @BindView(R.id.singin_look)
    ImageView singinLook;
    @BindView(R.id.singin_unlook)
    ImageView singinUnlook;
    @BindView(R.id.singin_islook)
    LinearLayout singinIslook;
    @BindView(R.id.singin_in)
    Button singinIn;
    @BindView(R.id.singin_regeister)
    TextView singinRegeister;
    @BindView(R.id.singin_forget)
    TextView singinForget;
    @BindView(R.id.singin_weixin)
    ImageView singinWeixin;
    private boolean isLook = false;
    private String number;
    private String password;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sign_in;
    }

    @Override
    protected void setUpView() {
        //设置点击事件
        initListener();
    }

    private void initListener() {
        singinIn.setOnClickListener(this);
        singinForget.setOnClickListener(this);
        singinWeixin.setOnClickListener(this);
        singinIslook.setOnClickListener(this);
        singinRegeister.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.singin_regeister:
                finish();
                Intent intent1 = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent1);
                break;
            case R.id.singin_forget:
                Intent intent2 = new Intent(SignInActivity.this, GetbackPw1Activity.class);
                startActivity(intent2);
                break;
            case R.id.singin_in:
                //格式判断
                number = singinNumber.getText().toString();
                password = singinPassword.getText().toString();
                checkCode();
                break;
            case R.id.singin_weixin:
                //微信登录
                break;
            case R.id.singin_islook:
                if (isLook) {
                    isLook = false;
                    singinLook.setVisibility(View.INVISIBLE);
                    //默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
                    singinPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                } else {
                    isLook = true;
                    singinLook.setVisibility(View.VISIBLE);
                    //选择状态 显示明文--设置为可见的密码
                    singinPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                break;
        }
    }


    private void checkCode() {
        if (!CodeUtil.isPhone(number)) {
            Toast.makeText(SignInActivity.this, "请输入正确格式的手机号码", Toast.LENGTH_SHORT).show();
        } else if (!CodeUtil.isPassword(password)) {
            Toast.makeText(SignInActivity.this, "请输入6-16位字母数字混合密码,首位不为数字", Toast.LENGTH_SHORT).show();
        } else {
            //格式验证通过,进行后续操作:网络请求
            signIn();
        }
    }

    //登录接口post请求
    public void signIn() {
        mMvpPresenter.signIn(number, password, mMultipleStateView);
    }

    @Override
    public void signIn(SigninBean bean) {
        //这里是登录成功,获取data数据,进行后续业务逻辑代码:登录数据持久化
        String user_id = bean.getUser_id();
        String token = bean.getToken();
        //静态数据存储
        LoginStatus.loginStatus(SignInActivity.this, token, user_id, number);
    }

    @Override
    public void error() {
        Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
    }



}
