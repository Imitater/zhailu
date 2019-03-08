package com.mouqukeji.hmdeer.ui.activity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.AddPostBean;
import com.mouqukeji.hmdeer.contract.activity.ApplyForContract;
import com.mouqukeji.hmdeer.modle.activity.ApplyForModel;
import com.mouqukeji.hmdeer.presenter.activity.ApplyForPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import butterknife.BindView;

public class ApplyForActivity extends BaseActivity<ApplyForPresenter, ApplyForModel> implements ApplyForContract.View, View.OnClickListener {
    @BindView(R.id.apply_actionbar)
    MyActionBar applyActionbar;
    @BindView(R.id.apply_name)
    EditText applyName;
    @BindView(R.id.apply_school)
    EditText applySchool;
    @BindView(R.id.apply_grade)
    EditText applyGrade;
    @BindView(R.id.number)
    EditText number;
    @BindView(R.id.apply_remker)
    EditText applyRemker;
    @BindView(R.id.apply_bt)
    Button applyBt;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_applyfor;
    }

    @Override
    protected void setUpView() {
        applyActionbar.setTitle("填写申请");
    }

    @Override
    protected void setUpData() {
        initListener();
    }

    private void initListener() {
        applyBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.apply_bt:
                if (TextUtils.isEmpty(applyName.getText().toString())){
                    Toast.makeText(ApplyForActivity.this,"姓名不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(applySchool.getText().toString())){
                    Toast.makeText(ApplyForActivity.this,"学校不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty( applyGrade.getText().toString())){
                    Toast.makeText(ApplyForActivity.this,"年级不能为空",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(number.getText().toString())){
                    Toast.makeText(ApplyForActivity.this,"号码不能为空",Toast.LENGTH_SHORT).show();
                }else {
                    mMvpPresenter.getMoney(applyName.getText().toString(), applySchool.getText().toString(),
                            applyGrade.getText().toString(), number.getText().toString(), applyRemker.getText().toString(), mMultipleStateView);
                }
                break;
        }
    }

    @Override
    public void addPost(AddPostBean bean) {
        Toast.makeText(ApplyForActivity.this,"提交成功",Toast.LENGTH_SHORT).show();
        finish();
    }
}
