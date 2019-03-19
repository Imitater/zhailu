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

public class PayBuyCompleteActivity extends BaseActivity implements View.OnClickListener {
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
    private Intent intent;

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
            case R.id.paycomplete_bt_left:
                EventMessage eventMessage = new EventMessage(EventCode.EVENT_H, 0);
                post(eventMessage);
                if (BuyOrderInfoActivity.instance != null)
                    BuyOrderInfoActivity.instance.finish();
                finish();
                break;
            case R.id.paycomplete_bt_right:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
                break;
            case R.id.action_back:
                EventMessage eventMessage2 = new EventMessage(EventCode.EVENT_H, 0);
                post(eventMessage2);
                if (BuyOrderInfoActivity.instance != null)
                    BuyOrderInfoActivity.instance.finish();
                finish();
                break;
        }
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }
}
