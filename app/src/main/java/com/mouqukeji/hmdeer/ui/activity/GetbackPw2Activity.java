package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.CodeCheckBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw2Contract;
import com.mouqukeji.hmdeer.modle.activity.GetbackPw2Model;
import com.mouqukeji.hmdeer.presenter.activity.GetbackPw2Presenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import butterknife.BindView;

public class GetbackPw2Activity extends BaseActivity<GetbackPw2Presenter, GetbackPw2Model> implements GetbackPw2Contract.View, View.OnClickListener {
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
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.button_again)
    Button buttonAgain;
    @BindView(R.id.action_bar)
    MyActionBar actionBar;
    private String responseString;
    private String telephone;
    private String code = "";

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
        //设置点击事件
        setListener();
        //监听 edittext
        setEditListener();
    }

    private void setEditListener() {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    editText2.requestFocus();
                    int e2 = editText2.getText().toString().length();
                    int e3 = editText3.getText().toString().length();
                    int e4 = editText4.getText().toString().length();
                    if (e2 == 1 && e3 == 1 && e4 == 1) {
                        code = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString();
                    }
                }
            }
        });
        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    editText3.requestFocus();
                    int e1 = editText1.getText().toString().length();
                    int e3 = editText3.getText().toString().length();
                    int e4 = editText4.getText().toString().length();
                    if (e1 == 1 && e3 == 1 && e4 == 1) {
                        code = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString();
                    }
                }
            }
        });
        editText3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    editText4.requestFocus();
                    int e1 = editText1.getText().toString().length();
                    int e2 = editText2.getText().toString().length();
                    int e4 = editText4.getText().toString().length();
                    if (e1 == 1 && e2 == 1 && e4 == 1) {
                        code = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString();
                    }
                }
            }
        });
        editText4.addTextChangedListener(new TextWatcher() {
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
                    int e1 = editText1.getText().toString().length();
                    int e2 = editText2.getText().toString().length();
                    int e3 = editText3.getText().toString().length();
                    if (e1 == 1 && e2 == 1 && e3 == 1) {
                        code = editText1.getText().toString() + editText2.getText().toString() + editText3.getText().toString() + editText4.getText().toString();
                    }
                }
            }
        });
    }

    private void setListener() {
        buttonAgain.setOnClickListener(this);
        llCancel.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_again:
                mMvpPresenter.checkCode(telephone, code, mMultipleStateView);
                break;
            case R.id.ll_cancel:
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
    }

}
