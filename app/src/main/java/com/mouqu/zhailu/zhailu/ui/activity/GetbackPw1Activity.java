package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.contract.activity.GetbackPw1Contract;
import com.mouqu.zhailu.zhailu.modle.activity.GetbackPw1Model;
import com.mouqu.zhailu.zhailu.presenter.activity.GetbackPw1Presenter;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.CodeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GetbackPw1Activity extends BaseActivity<GetbackPw1Presenter, GetbackPw1Model> implements GetbackPw1Contract.View, View.OnClickListener {


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
    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.button_test)
    Button buttonTest;
    @BindView(R.id.action_bar)
    MyActionBar actionBar;
    private String number;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_getback_pw;
    }

    @Override
    protected void setUpView() {
        setListener();
    }

    @Override
    protected void setUpData() {

    }

    public void setListener() {
        buttonNext.setOnClickListener(this);
        buttonTest.setOnClickListener(this);
        llCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                number = editText1.getText().toString();
                if (CodeUtil.isPhone(number)) {
                    Toast.makeText(GetbackPw1Activity.this, "请输入正确格式的手机号码", Toast.LENGTH_SHORT).show();

                } else {
                    //格式验证通过,进行后续操作:网络请求验证码
                    mMvpPresenter.getCode(GetbackPw1Activity.this, number, mMultipleStateView);
                }
                break;
            case R.id.button_test:
                //这里是测试按钮,正式版请删除
                Intent intent = new Intent(GetbackPw1Activity.this, GetbackPw2Activity.class);
                intent.putExtra("telephone", number);
                startActivity(intent);
                break;
            case R.id.ll_cancel:
                finish();
                break;
        }
    }

    @Override
    public void getCode(CodeBean bean) {

    }


}