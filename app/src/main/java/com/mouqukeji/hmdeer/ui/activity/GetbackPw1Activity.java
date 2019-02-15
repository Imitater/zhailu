package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.mouqukeji.hmdeer.contract.activity.GetbackPw1Contract;
import com.mouqukeji.hmdeer.modle.activity.GetbackPw1Model;
import com.mouqukeji.hmdeer.presenter.activity.GetbackPw1Presenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CodeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GetbackPw1Activity extends BaseActivity<GetbackPw1Presenter, GetbackPw1Model> implements GetbackPw1Contract.View, View.OnClickListener {

    @BindView(R.id.action_bar)
    MyActionBar actionBar;
    @BindView(R.id.imageButton)
    ImageView imageButton;
    @BindView(R.id.ll_cancel)
    LinearLayout llCancel;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.button_next)
    Button buttonNext;
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
        llCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_next:
                number = editText1.getText().toString();
                if (!CodeUtil.isPhone(number)) {
                    Toast.makeText(GetbackPw1Activity.this, "请输入正确格式的手机号码", Toast.LENGTH_SHORT).show();
                } else {
                    //格式验证通过,进行后续操作:网络请求验证码
                    mMvpPresenter.getCode(GetbackPw1Activity.this, number, "2", mMultipleStateView);
                }
                break;
            case R.id.ll_cancel:
                finish();
                break;
        }
    }

    @Override
    public void getCode(CodeBean bean) {
        Intent intent = new Intent(GetbackPw1Activity.this, GetbackPw2Activity.class);
        intent.putExtra("telephone", number);
        startActivity(intent);
        finish();
    }

    @Override
    public void isSend() {
        Toast.makeText(this, "验证码已发送", Toast.LENGTH_SHORT).show();
    }

}