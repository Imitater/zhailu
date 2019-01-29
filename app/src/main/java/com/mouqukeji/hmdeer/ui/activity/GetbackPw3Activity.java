package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.resetPasswordBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw3Contract;
import com.mouqukeji.hmdeer.modle.activity.GetbackPw3Model;
import com.mouqukeji.hmdeer.presenter.activity.GetbackPw3Presenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CodeUtil;
import com.mouqukeji.hmdeer.util.LoginQuit;

import butterknife.BindView;

public class GetbackPw3Activity extends BaseActivity<GetbackPw3Presenter, GetbackPw3Model> implements GetbackPw3Contract.View, View.OnClickListener {
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.ll_cancel)
    LinearLayout llCancel;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.button_sign_in)
    Button buttonSignIn;
    @BindView(R.id.action_bar)
    MyActionBar actionBar;
    private String telephone;
    private String code;
    private String password;


    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_getback_pw3;
    }

    @Override
    protected void setUpView() {
        Intent intent = getIntent();//获取传递的参数
        telephone = intent.getStringExtra("telephone");//获取number
        code = intent.getStringExtra("code");//获取code

        //设置点击事件
        setListener();
    }

    @Override
    protected void setUpData() {

    }


    public void setListener() {
        buttonSignIn.setOnClickListener(this);
        llCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        int id = v.getId();
        switch (id) {
            case R.id.button_sign_in:
                password = editText1.getText().toString();
                if (CodeUtil.isPassword(password)) {
                    //重置密码
                    mMvpPresenter.resetPassword(GetbackPw3Activity.this, telephone, code, password, mMultipleStateView);
                } else {
                    Toast.makeText(GetbackPw3Activity.this, "请输入6-16位字母数字混合密码,首位不为数字", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_cancel:
                finish();
                break;
        }
    }


    @Override
    public void resetPassword(resetPasswordBean bean) {
        Toast.makeText(GetbackPw3Activity.this, "密码重置成功", Toast.LENGTH_SHORT).show();
        new LoginQuit().loginQuit(GetbackPw3Activity.this, 0);
    }


}
