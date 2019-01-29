package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.EvaluationBean;
import com.mouqukeji.hmdeer.contract.activity.EvaluationContract;
import com.mouqukeji.hmdeer.modle.activity.EvaluationModel;
import com.mouqukeji.hmdeer.presenter.activity.EvaluationPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.ui.widget.StarBar;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EvaluationActivity extends BaseActivity<EvaluationPresenter, EvaluationModel> implements EvaluationContract.View, View.OnClickListener {
    @BindView(R.id.evaluation_actionbar)
    MyActionBar evaluationActionbar;
    @BindView(R.id.starBar)
    StarBar starBar;
    @BindView(R.id.evaluation_bt)
    Button evaluationBt;
    @BindView(R.id.evaluation_et)
    EditText evaluationEt;
    private String order_id;
    private String spUserID;
    private float starMark;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_evaluation;
    }

    @Override
    protected void setUpView() {
        Intent intent = getIntent();
        order_id = intent.getStringExtra("order_id");
        spUserID = new GetSPData().getSPUserID(this);
        MyActionBar evaluationActionBar = findViewById(R.id.evaluation_actionbar);
        //设置title
        evaluationActionBar.setTitle("评价");
        evaluationBt.setOnClickListener(this);
        starMark = starBar.getStarMark();
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.evaluation_bt:
                mMvpPresenter.evaluationOrder(spUserID, order_id,starMark+"",evaluationEt.getText().toString(),mMultipleStateView);
                break;
        }
    }

    @Override
    public void evaluationOrder(EvaluationBean bean) {
        Toast.makeText(this,"评价成功",Toast.LENGTH_SHORT).show();
    }


}
