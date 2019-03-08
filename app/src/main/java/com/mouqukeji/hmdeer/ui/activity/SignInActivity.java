package com.mouqukeji.hmdeer.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.PushMesgBean;
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.contract.activity.SignInContract;
import com.mouqukeji.hmdeer.modle.activity.SigninModel;
import com.mouqukeji.hmdeer.presenter.activity.SignInPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CodeUtil;
import com.mouqukeji.hmdeer.util.LoginStatus;
import com.mouqukeji.hmdeer.util.PhoneUtils;
import com.mouqukeji.hmdeer.util.SpUtils;

import butterknife.BindView;
import cn.jpush.android.api.JPushInterface;


public class SignInActivity extends BaseActivity<SignInPresenter, SigninModel> implements SignInContract.View, View.OnClickListener {
    @BindView(R.id.actionbar)
    MyActionBar actionbar;
    @BindView(R.id.singin_number)
    AutoCompleteTextView singinNumber;
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
        SpUtils.putString("isFirst","2",this);
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
        //did
        String phoneMeid = PhoneUtils.getPhoneMeid(this);
        //版本名
        String versionName = PhoneUtils.getVersionName(this);
        //手机型号
        String model = android.os.Build.MODEL;
        //系统版本号
        String systemVersion = PhoneUtils.getSystemVersion();
        mMvpPresenter.signIn(number, password, phoneMeid, "android", versionName, model, systemVersion, mMultipleStateView);
    }

    @Override
    public void signIn(SigninBean bean) {
        //这里是登录成功,获取data数据,进行后续业务逻辑代码:登录数据持久化
        String user_id = bean.getUser().getUser_id();
        String token = bean.getUser().getToken();
        //静态数据存储
        LoginStatus.loginStatus(SignInActivity.this, token, user_id, number);
        //判断之前是否登录 是否发送消息 强制下线
        if (bean.getUser().getDid() != null) {
            if (!bean.getUser().getDid().equals(PhoneUtils.getPhoneMeid(this))) {
                 mMvpPresenter.pushMsg(JPushInterface.getRegistrationID(this),PhoneUtils.getPhoneMeid(this),bean.getUser().getDid(),mMultipleStateView);
            }
        }
    }


    @Override
    public void error() {
        Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pushMsg(PushMesgBean bean) {
        Log.e("ddd","发送消息成功");
    }


}
