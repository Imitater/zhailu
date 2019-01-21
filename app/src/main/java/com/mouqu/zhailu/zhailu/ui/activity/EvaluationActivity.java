package com.mouqu.zhailu.zhailu.ui.activity;

import android.view.View;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.EvaluationContract;
import com.mouqu.zhailu.zhailu.modle.activity.EvaluationModel;
import com.mouqu.zhailu.zhailu.presenter.activity.EvaluationPresenter;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;


public class EvaluationActivity  extends BaseActivity<EvaluationPresenter, EvaluationModel> implements EvaluationContract.View, View.OnClickListener {
    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_evaluation;
    }

    @Override
    protected void setUpView() {
        MyActionBar evaluationActionBar=findViewById(R.id.evaluation_actionbar);
        //设置title
        evaluationActionBar.setTitle("评价");
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {

    }
}
