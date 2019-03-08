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
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;


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
    private String cate_id;
    private String spUserID;
    private String server_id;

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
        server_id = intent.getStringExtra("server_id");
        cate_id = intent.getStringExtra("cate_id");
        spUserID = new GetSPData().getSPUserID(this);
        MyActionBar evaluationActionBar = findViewById(R.id.evaluation_actionbar);
        //设置title
        evaluationActionBar.setTitle("评价");
        evaluationBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.evaluation_bt:
                mMvpPresenter.evaluationOrder(spUserID, order_id, starBar.getStarMark() + "", evaluationEt.getText().toString(), mMultipleStateView);
                break;
        }
    }

    @Override
    public void evaluationOrder(EvaluationBean bean) {
        if (cate_id.equals("11")) {
            if (TakeIngOrderInfoActivity.instance != null)
                TakeIngOrderInfoActivity.instance.finish();
        } else if (cate_id.equals("12")) {
            if (BuyIngOrderInfoActivity.instance != null)
                BuyIngOrderInfoActivity.instance.finish();
        } else if (cate_id.equals("13")) {
            if (SendIngOrderInfoActivity.instance != null)
                SendIngOrderInfoActivity.instance.finish();
        } else if (cate_id.equals("14")) {
            if (DeliverIngOrderInfoActivity.instance != null)
                DeliverIngOrderInfoActivity.instance.finish();
        } else if (cate_id.equals("15")) {
            if (UniversalIngOrderInfoActivity.instance != null)
                UniversalIngOrderInfoActivity.instance.finish();
        }
        Toast.makeText(this, "评价成功", Toast.LENGTH_SHORT).show();
        //发送消息 评价成功
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_K, 0);
        post(eventMessage);
        finish();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }
}
