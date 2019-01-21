package com.mouqu.zhailu.zhailu.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.bean.ChangePhoneBean;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.contract.activity.ChangePhoneContract;
import com.mouqu.zhailu.zhailu.modle.activity.ChangePhoneModel;
import com.mouqu.zhailu.zhailu.presenter.activity.ChangePhonePresenter;
import com.mouqu.zhailu.zhailu.util.CodeUtil;
import com.mouqu.zhailu.zhailu.util.GetSPData;
import com.mouqu.zhailu.zhailu.util.LoginQuit;

import butterknife.BindView;

public class ChangePhoneActivity extends BaseActivity<ChangePhonePresenter, ChangePhoneModel> implements ChangePhoneContract.View, View.OnClickListener {

    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.ll_cancel)
    LinearLayout llCancel;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.button_get_captcha)
    Button buttonGetCaptcha;
    @BindView(R.id.button_change_phone)
    Button buttonChangePhone;
    private String responseString;
    private String responseString2;
    private String number;
    private String code;


    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_change_phone;
    }

    @Override
    protected void setUpView() {
        setListener();
    }

    private void setListener() {
        llCancel.setOnClickListener(this);
        buttonChangePhone.setOnClickListener(this);
        buttonGetCaptcha.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
        number = editText1.getText().toString();
        code = editText2.getText().toString();
    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.ll_cancel:
                finish();
                break;
            case R.id.button_change_phone:
                setChange();//开始修改phone number
                break;
            case R.id.button_get_captcha:
                if (CodeUtil.isPhone(number)) {
                    //格式验证正确 获取code
                    mMvpPresenter.getCode(this,number,mMultipleStateView);
                } else {
                    //格式验证错误
                    Toast.makeText(ChangePhoneActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void setChange() {
        GetSPData getSPData = new GetSPData();
        String userId = getSPData.getSPUserID(ChangePhoneActivity.this);
        if (CodeUtil.isPhone(number) & CodeUtil.isVf(code)) {
            //格式验证正确 修改手机号
            mMvpPresenter.changePhone(userId, number, code, mMultipleStateView);
        } else {
            //格式验证错误
            if (!CodeUtil.isPhone(number)) {
                Toast.makeText(ChangePhoneActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            } else if (!CodeUtil.isVf(code)) {
                Toast.makeText(ChangePhoneActivity.this, "请输入四位验证码", Toast.LENGTH_SHORT).show();
            }
        }
        return;
    }

    @Override
    public void changePhone(ChangePhoneBean bean) {
        Toast.makeText(ChangePhoneActivity.this, "手机号修改成功", Toast.LENGTH_SHORT).show();
        new LoginQuit().loginQuit(ChangePhoneActivity.this, 1);
    }

    @Override
    public void getCode(CodeBean bean) {
        Toast.makeText(this, "成功发送短信验证码", Toast.LENGTH_SHORT).show();
    }
}
