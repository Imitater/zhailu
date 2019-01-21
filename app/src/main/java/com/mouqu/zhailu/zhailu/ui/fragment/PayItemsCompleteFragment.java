package com.mouqu.zhailu.zhailu.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseFragment;
import com.mouqu.zhailu.zhailu.contract.fragment.PayItemsCompleteContract;
import com.mouqu.zhailu.zhailu.modle.fragment.PayItemsCompleteModel;
import com.mouqu.zhailu.zhailu.presenter.fragment.PayItemsCompletePresenter;
import com.mouqu.zhailu.zhailu.ui.activity.EvaluationActivity;
import com.mouqu.zhailu.zhailu.ui.activity.HelpBuyActivity;

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

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_paycomplete_items;
    }

    @Override
    protected void setUpView() {
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
                startActivity(intent);
                break;
            case R.id.action_back:
                HelpBuyActivity.instance.finish();
                getActivity().finish();
                break;
        }
    }





}
