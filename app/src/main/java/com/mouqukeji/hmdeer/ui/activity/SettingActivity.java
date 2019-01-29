package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.contract.activity.SettingContract;
import com.mouqukeji.hmdeer.modle.activity.SettingModel;
import com.mouqukeji.hmdeer.presenter.activity.SettingPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.LoginQuit;

import butterknife.BindView;

public class SettingActivity extends BaseActivity <SettingPresenter, SettingModel> implements SettingContract.View,View.OnClickListener {
    @BindView(R.id.setting_actionbar)
    MyActionBar settingActionbar;
    @BindView(R.id.setting_changpassword)
    RelativeLayout settingChangpassword;
    @BindView(R.id.setting_changnumber)
    RelativeLayout settingChangnumber;
    @BindView(R.id.setting_question)
    RelativeLayout settingQuestion;
    @BindView(R.id.setting_advice)
    RelativeLayout settingAdvice;
    @BindView(R.id.setting_about)
    RelativeLayout settingAbout;
    @BindView(R.id.setting_exit)
    Button settingExit;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void setUpView() {
        settingActionbar.setTitle("系统设置");
        setListener();
    }

    private void setListener() {
        settingChangpassword.setOnClickListener(this);
        settingChangnumber.setOnClickListener(this);
        settingQuestion.setOnClickListener(this);
        settingAdvice.setOnClickListener(this);
        settingAbout.setOnClickListener(this);
        settingExit.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.setting_changpassword://修改密码
                Intent intent = new Intent(SettingActivity.this, ChangePwActivity.class);
                startActivity(intent);
                break;
            case R.id.setting_changnumber://修改手机号
                Intent intent1 = new Intent(SettingActivity.this, ChangePhoneActivity.class);
                startActivity(intent1);
                break;
            case R.id.setting_question://常见问题
                Intent intent2 = new Intent(SettingActivity.this, QuestionActivity.class);
                startActivity(intent2);
                break;
            case R.id.setting_advice://意见反馈
                Intent intent3 = new Intent(SettingActivity.this, AdviceActivity.class);
                startActivity(intent3);
                break;
            case R.id.setting_about://关于
                Intent intent4 = new Intent(SettingActivity.this, AboutActivity.class);
                startActivity(intent4);
                break;
            case R.id.setting_exit://退出登录
                int type=1;
                new LoginQuit().loginQuit(SettingActivity.this,type);
                break;
        }
    }
}
