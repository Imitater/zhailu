package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.contract.fragment.PayCompleteContract;
import com.mouqukeji.hmdeer.modle.fragment.PayCompleteModel;
import com.mouqukeji.hmdeer.presenter.fragment.PayCompletePresenter;
import com.mouqukeji.hmdeer.ui.activity.BuyIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpBuyActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpDeliverActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpSendActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpTakeActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpUniversalActivity;
import com.mouqukeji.hmdeer.ui.activity.SendIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalIngOrderInfoActivity;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;


public class PayCompleteFragment extends BaseFragment<PayCompletePresenter, PayCompleteModel> implements PayCompleteContract.View, View.OnClickListener {
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
    private String item;
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
        //发送消息 已下单 刷新列表
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_L, 1);
        post(eventMessage);
        task_id = getArguments().getString("task_id");
        cate_id = getArguments().getString("cate_id");
        item = getArguments().getString("item");
        //设置title
        actionTitle.setText("支付成功");
        actionBack.setOnClickListener(this);
        //设置时间监听
        initListener();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
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
                if (item.equals("1")) {
                    HelpTakeActivity.instance.finish();
                }else if (item.equals("2")){
                    HelpBuyActivity.instance.finish();
                }else if (item.equals("3")){
                    HelpSendActivity.instance.finish();
                }else if (item.equals("4")){
                    HelpDeliverActivity.instance.finish();
                }else{
                    HelpUniversalActivity.instance.finish();
                }
                getActivity().finish();
                break;
            case R.id.paycomplete_bt_right:
                getActivity().finish();
                if (item.equals("1")) {
                    HelpTakeActivity.instance.finish();
                    intent = new Intent(getMContext(), TakeIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);

                }else if (item.equals("2")){
                    HelpBuyActivity.instance.finish();
                    intent = new Intent(getMContext(), BuyIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                }else if (item.equals("3")){
                    HelpSendActivity.instance.finish();
                    intent = new Intent(getMContext(), SendIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                }else if (item.equals("4")){
                    HelpDeliverActivity.instance.finish();
                    intent = new Intent(getMContext(), DeliverIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                }else{
                    HelpUniversalActivity.instance.finish();
                    intent = new Intent(getMContext(), UniversalIngOrderInfoActivity.class);
                    intent.putExtra("taskId", task_id);
                    intent.putExtra("cateId", cate_id);
                }
                startActivity(intent);
                break;
            case R.id.action_back:
                if (item.equals("1")) {
                    HelpTakeActivity.instance.finish();
                }else if (item.equals("2")){
                    HelpBuyActivity.instance.finish();
                }else if (item.equals("3")){
                    HelpSendActivity.instance.finish();
                }else if (item.equals("4")){
                    HelpDeliverActivity.instance.finish();
                }else{
                    HelpUniversalActivity.instance.finish();
                }
                getActivity().finish();
                break;
        }
    }
    public static PayCompleteFragment newInstance(String task_id,String cate_id,String item) {
        Bundle args = new Bundle();
        args.putString("task_id",task_id);
        args.putString("cate_id",cate_id);
        args.putString("item",item);
        PayCompleteFragment fragment = new PayCompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
