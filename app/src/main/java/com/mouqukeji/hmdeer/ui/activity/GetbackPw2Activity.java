package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.CodeCheckBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw2Contract;
import com.mouqukeji.hmdeer.modle.activity.GetbackPw2Model;
import com.mouqukeji.hmdeer.presenter.activity.GetbackPw2Presenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.CountDownTimerUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetbackPw2Activity extends BaseActivity<GetbackPw2Presenter, GetbackPw2Model> implements GetbackPw2Contract.View, View.OnClickListener {

    @BindView(R.id.action_bar)
    MyActionBar actionBar;
    @BindView(R.id.checkcode_back)
    Button checkcodeBack;
    @BindView(R.id.checked_et1)
    EditText checkedEt1;
    @BindView(R.id.checked_et2)
    EditText checkedEt2;
    @BindView(R.id.checked_et3)
    EditText checkedEt3;
    @BindView(R.id.checked_et4)
    EditText checkedEt4;
    @BindView(R.id.checkcode_number)
    TextView checkcodeNumber;
    @BindView(R.id.checkcode_time)
    Button checkcodeTime;
    private String telephone;
    private String code = "";
    private MyHandler myHandler;
    private CountDownTimerUtil timer;

    public class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    myHandler.sendEmptyMessageDelayed(1, 2000);
                    if (!TextUtils.isEmpty(checkedEt1.getText().toString())&&!TextUtils.isEmpty(checkedEt2.getText().toString())&&!TextUtils.isEmpty(checkedEt3.getText().toString())&&!TextUtils.isEmpty(checkedEt4.getText().toString())){
                        mMvpPresenter.checkCode(telephone, code, mMultipleStateView);//判断各个et 是否为空
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void initViewAndEvents() {
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_getback_pw2;
    }

    @Override
    protected void setUpView() {
        Intent intent = getIntent();
        telephone = intent.getStringExtra("telephone");
        myHandler = new MyHandler();
        myHandler.sendEmptyMessage(1);//开启handler监听
        checkcodeNumber.setText(telephone);
        //设置点击事件
        setListener();
        //监听 edittext
        setEditListener();
        //设置倒计时
        setClock();
    }

    private void setClock() {
        timer = new CountDownTimerUtil(60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    checkcodeTime.setEnabled(false);
                    checkcodeTime.setText(millisUntilFinished / 1000 + "s后重新发送");
                    checkcodeTime.setBackgroundColor(getResources().getColor(R.color.consumption_gry));
                    checkcodeTime.setTextColor(getResources().getColor(R.color.checkcode_gray));
            }

            @Override
            public void onFinish() {
                    checkcodeTime.setEnabled(true);
                    checkcodeTime.setBackgroundColor(getResources().getColor(R.color.blue));
                    checkcodeTime.setText("重新获取验证码");
                    checkcodeTime.setTextColor(getResources().getColor(R.color.white));
            }
        }.start();
    }

    private void setEditListener() {
        checkedEt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    checkedEt2.requestFocus();
                    int e2 = checkedEt2.getText().toString().length();
                    int e3 = checkedEt3.getText().toString().length();
                    int e4 = checkedEt4.getText().toString().length();
                    if (e2 == 1 && e3 == 1 && e4 == 1) {
                        code = checkedEt1.getText().toString() + checkedEt2.getText().toString() + checkedEt3.getText().toString() + checkedEt4.getText().toString();
                    }
                }
            }
        });
        checkedEt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    checkedEt3.requestFocus();
                    int e1 = checkedEt1.getText().toString().length();
                    int e3 = checkedEt3.getText().toString().length();
                    int e4 = checkedEt4.getText().toString().length();
                    if (e1 == 1 && e3 == 1 && e4 == 1) {
                        code = checkedEt1.getText().toString() + checkedEt2.getText().toString() + checkedEt3.getText().toString() + checkedEt4.getText().toString();
                    }
                }
            }
        });
        checkedEt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    checkedEt4.requestFocus();
                    int e1 = checkedEt1.getText().toString().length();
                    int e2 = checkedEt2.getText().toString().length();
                    int e4 = checkedEt4.getText().toString().length();
                    if (e1 == 1 && e2 == 1 && e4 == 1) {
                        code = checkedEt1.getText().toString() + checkedEt2.getText().toString() + checkedEt3.getText().toString() + checkedEt4.getText().toString();
                    }
                }
            }
        });
        checkedEt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //这里做网络请求的判断
                if (s.length() == 1) {
                    checkedEt4.requestFocus();
                    int e1 = checkedEt1.getText().toString().length();
                    int e2 = checkedEt2.getText().toString().length();
                    int e3 = checkedEt3.getText().toString().length();
                    if (e1 == 1 && e2 == 1 && e3 == 1) {
                        code = checkedEt1.getText().toString() + checkedEt2.getText().toString() + checkedEt3.getText().toString() + checkedEt4.getText().toString();
                    }
                }
            }
        });
    }

    private void setListener() {
        checkcodeTime.setOnClickListener(this);
        checkcodeBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkcode_time:
                timer.start();//再次开启倒计时
                mMvpPresenter.checkCode(telephone, "2", mMultipleStateView);
                break;
            case R.id.checkcode_back:
                finish();
                break;
        }
    }


    @Override
    public void checkCode(CodeCheckBean bean) {
        Intent intent = new Intent(GetbackPw2Activity.this, GetbackPw3Activity.class);
        intent.putExtra("telephone", telephone);
        intent.putExtra("code", code);
        startActivity(intent);
        finish();
    }

    @Override
    public void getCode(CodeBean bean) {

    }

    @Override
    public void isSend() {
        Toast.makeText(this,"验证码已发送",Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeMessages(1);//移除循环信息
        if(timer!=null){
            timer.cancel();
        }
    }
}
