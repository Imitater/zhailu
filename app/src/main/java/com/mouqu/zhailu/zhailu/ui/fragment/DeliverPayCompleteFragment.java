package com.mouqu.zhailu.zhailu.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseFragment;
import com.mouqu.zhailu.zhailu.contract.fragment.DeliverPayCompleteContract;
import com.mouqu.zhailu.zhailu.modle.fragment.DeliverPayCompleteModel;
import com.mouqu.zhailu.zhailu.presenter.fragment.DeliverPayCompletePresenter;
import com.mouqu.zhailu.zhailu.ui.activity.DeliverOrderInfoActivity;

import butterknife.BindView;
import butterknife.Unbinder;

public class DeliverPayCompleteFragment extends BaseFragment<DeliverPayCompletePresenter, DeliverPayCompleteModel> implements DeliverPayCompleteContract.View, View.OnClickListener {

    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.paycomplete_bt_left)
    Button paycompleteBtLeft;
    @BindView(R.id.paycomplete_bt_right)
    Button paycompleteBtRight;
    Unbinder unbinder;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_deliverpay_complete;
    }

    @Override
    protected void setUpView() {
        //设置title
        actionTitle.setText("支付成功");
        initListener();
        //返回按键监听
        initBackListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        paycompleteBtLeft.setOnClickListener(this);
        paycompleteBtRight.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment add = fragmentManager.findFragmentByTag("send_pay");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(add);
        fragmentTransaction.commit();
    }

    private void initBackListener() {
        //返回键监听
        getContentView().setFocusableInTouchMode(true);
        getContentView().requestFocus();
        getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    setBack();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                getActivity().finish();
                break;
            case R.id.paycomplete_bt_left://返回首页
                getActivity().finish();
                break;
            case R.id.paycomplete_bt_right://查看订单
                Intent intent = new Intent(getActivity(), DeliverOrderInfoActivity.class);
                getMContext().startActivity(intent);
                getActivity().finish();
                break;
        }
    }


}
