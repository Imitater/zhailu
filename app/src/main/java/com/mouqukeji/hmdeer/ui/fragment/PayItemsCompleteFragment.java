package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.bean.EvaluationBean;
import com.mouqukeji.hmdeer.contract.fragment.PayItemsCompleteContract;
import com.mouqukeji.hmdeer.modle.fragment.PayItemsCompleteModel;
import com.mouqukeji.hmdeer.presenter.fragment.PayItemsCompletePresenter;
import com.mouqukeji.hmdeer.ui.activity.EvaluationActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpBuyActivity;

import butterknife.BindView;


public class PayItemsCompleteFragment extends BaseFragment<PayItemsCompletePresenter, PayItemsCompleteModel> implements PayItemsCompleteContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.paycomplete_bt_left)
    Button paycompleteBtLeft;
    @BindView(R.id.paycomplete_bt_right)
    Button paycompleteBtRight;
    private int index;
    private String order_id;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_paycomplete_items;
    }

    @Override
    protected void setUpView() {
        order_id = getArguments().getString("order_id");
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
                HelpBuyActivity.instance.finish();
                getActivity().finish();
                break;
            case R.id.paycomplete_bt_right:
                HelpBuyActivity.instance.finish();
                getActivity().finish();
                Intent intent = new Intent(getMContext(), EvaluationActivity.class);
                intent.putExtra("order_id",order_id);
                startActivity(intent);
                break;
            case R.id.action_back:
                HelpBuyActivity.instance.finish();
                getActivity().finish();
                break;
        }
    }


    public static PayItemsCompleteFragment newInstance(String order_id) {
        Bundle args = new Bundle();
        args.putString("order_id",order_id);
        PayItemsCompleteFragment fragment = new PayItemsCompleteFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
