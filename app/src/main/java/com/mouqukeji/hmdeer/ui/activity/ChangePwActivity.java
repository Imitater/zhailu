package com.mouqukeji.hmdeer.ui.activity;

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
import com.mouqukeji.hmdeer.bean.ChangePasswordBean;
import com.mouqukeji.hmdeer.contract.activity.ChangePwContract;
import com.mouqukeji.hmdeer.modle.activity.ChangePwModel;
import com.mouqukeji.hmdeer.presenter.activity.ChangePwPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CodeUtil;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.LoginQuit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePwActivity extends BaseActivity<ChangePwPresenter, ChangePwModel> implements ChangePwContract.View, View.OnClickListener {
    @BindView(R.id.imageButton)
    ImageView imageButton;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.editText1)
    EditText editText1;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.editText2)
    EditText editText2;
    @BindView(R.id.view7)
    View view7;
    @BindView(R.id.button_change)
    Button buttonChange;
    @BindView(R.id.action_bar)
    MyActionBar actionBar;
    private String oldPassword;
    private String newPassword;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_change_pw;
    }

    @Override
    protected void setUpView() {
        setListener();
    }

    @Override
    protected void setUpData() {

    }

    public void setListener() {
        imageButton.setOnClickListener(this);
        buttonChange.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.imageButton:
                finish();
                break;
            case R.id.button_change:
                setChangePassword();
                break;
        }
    }

    private void setChangePassword() {
        oldPassword = editText1.getText().toString();
        newPassword = editText2.getText().toString();
        GetSPData getSPData = new GetSPData();
        String userId = getSPData.getSPUserID(ChangePwActivity.this);
        if (CodeUtil.isPassword(oldPassword) & CodeUtil.isPassword(newPassword)) {
            /*
             * 密码格式正确
             * 开始修改密码
             * */
            mMvpPresenter.changePassword(ChangePwActivity.this, userId, oldPassword, newPassword, mMultipleStateView);
        } else {
            Toast.makeText(ChangePwActivity.this, "请输入6-16位字母数字混合密码,首位不为数字", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void changePassword(ChangePasswordBean bean) {
        Toast.makeText(ChangePwActivity.this, "密码修改成功", Toast.LENGTH_SHORT).show();
        new LoginQuit().loginQuit(ChangePwActivity.this, 1);
    }


}
