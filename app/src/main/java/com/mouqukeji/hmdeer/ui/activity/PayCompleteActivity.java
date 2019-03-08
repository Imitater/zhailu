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

public class PayCompleteActivity extends BaseActivity implements View.OnClickListener {
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
        //设置title
        actionTitle.setText("支付成功");
        actionBack.setOnClickListener(this);

        //发送消息 通知 支付成功
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_J, 0);
        post(eventMessage);
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
                finish();
                break;
            case R.id.paycomplete_bt_right:
                finish();
                if (cate_id.equals("11")) {
                    if (HelpTakeActivity.instance != null)
                        HelpTakeActivity.instance.finish();
                    intent = new Intent(this, TakeIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                } else if (cate_id.equals("12")) {
                    if (HelpBuyActivity.instance != null)
                        HelpBuyActivity.instance.finish();
                    intent = new Intent(this, BuyIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                } else if (cate_id.equals("13")) {
                    if (HelpSendActivity.instance != null)
                        HelpSendActivity.instance.finish();
                    intent = new Intent(this, SendIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                } else if (cate_id.equals("14")) {
                    if (HelpDeliverActivity.instance != null)
                        HelpDeliverActivity.instance.finish();
                    intent = new Intent(this, DeliverIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                } else {
                    if (HelpUniversalActivity.instance != null)
                        HelpUniversalActivity.instance.finish();
                    intent = new Intent(this, UniversalIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                }
                startActivity(intent);
                break;
            case R.id.action_back:
                finish();
                break;
        }
    }



}
