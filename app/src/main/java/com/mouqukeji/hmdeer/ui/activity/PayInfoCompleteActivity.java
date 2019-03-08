package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class PayInfoCompleteActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.paycomplete_bt_left)
    Button paycompleteBtLeft;
    @BindView(R.id.paycomplete_bt_right)
    Button paycompleteBtRight;
    Unbinder unbinder;
    private String task_id;
    private String cate_id;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_paycomplete;
    }

    @Override
    protected void setUpView() {
        Intent intent = getIntent();
        task_id = intent.getStringExtra("task_id");
        cate_id = intent.getStringExtra("cate_id");
        //支付成功
        //发送消息 通知 支付成功
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_J, 0);
        post(eventMessage);
         //设置title
        actionTitle.setText("支付成功");
        actionBack.setOnClickListener(this);
        //设置时间监听
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        paycompleteBtLeft.setOnClickListener(this);
        paycompleteBtRight.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.paycomplete_bt_left://返回首页
                backHome();
                break;
            case R.id.paycomplete_bt_right:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
                break;
            case R.id.action_back://回首页
                backHome();
                break;
        }
    }

    private void backHome() {
        if (cate_id.equals("11")){
            TakeIngOrderInfoActivity.instance.finish();
        }else if (cate_id.equals("12")){
            BuyIngOrderInfoActivity.instance.finish();
        }else if (cate_id.equals("13")){
            SendIngOrderInfoActivity.instance.finish();
        }else if (cate_id.equals("14")){
            DeliverIngOrderInfoActivity.instance.finish();
        }else if (cate_id.equals("15")){
            UniversalIngOrderInfoActivity.instance.finish();
        }
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_H, 0);
        post(eventMessage);
        finish();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }
}
