package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
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
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.contract.activity.SignInContract;
import com.mouqukeji.hmdeer.modle.activity.SigninModel;
import com.mouqukeji.hmdeer.presenter.activity.SignInPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CodeUtil;
import com.mouqukeji.hmdeer.util.LoginStatus;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;


public class SignInActivity extends BaseActivity<SignInPresenter, SigninModel> implements SignInContract.View, View.OnClickListener {
    private static final String TAG = "SignInActivity";
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.ll_cancel_signin)
    LinearLayout llCancelSignin;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.imageView_zhengkai)
    ImageView imageViewZhengkai;
    @BindView(R.id.imageView_bishang)
    ImageView imageViewBishang;
    @BindView(R.id.button_sign_in)
    Button buttonSignIn;
    @BindView(R.id.textview_sign_up)
    TextView textviewSignUp;
    @BindView(R.id.textview_forget)
    TextView textviewForget;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.circle_weixin)
    CircleImageView circleWeixin;
    @BindView(R.id.action_bar)
    MyActionBar actionBar;
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
        textviewSignUp.setOnClickListener(this);
        textviewForget.setOnClickListener(this);
        buttonSignIn.setOnClickListener(this);
        llCancelSignin.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview_sign_up:
                finish();
                Intent intent1 = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent1);
                break;
            case R.id.textview_forget:
                Intent intent2 = new Intent(SignInActivity.this, GetbackPw1Activity.class);
                startActivity(intent2);
                break;
            case R.id.button_sign_in:
                //格式判断
                number = editText1.getText().toString();
                password = editText2.getText().toString();
                checkCode();
                break;
            case R.id.ll_cancel_signin:
                finish();
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
        Toast.makeText(this,"账号或密码错误",Toast.LENGTH_SHORT).show();
    }

}
