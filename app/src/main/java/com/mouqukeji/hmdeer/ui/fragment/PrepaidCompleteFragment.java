package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.contract.fragment.RechargeCompleteContract;
import com.mouqukeji.hmdeer.modle.fragment.RechargeCompleteModel;
import com.mouqukeji.hmdeer.presenter.fragment.RechargeCompletePresenter;
import com.mouqukeji.hmdeer.ui.activity.PackageActivity;

import butterknife.BindView;
import butterknife.Unbinder;

public class PrepaidCompleteFragment extends BaseFragment<RechargeCompletePresenter, RechargeCompleteModel> implements RechargeCompleteContract.View, View.OnClickListener  {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.recharge_left_tv)
    TextView rechargeLeftTv;
    @BindView(R.id.recharge_right_right)
    TextView rechargeRightRight;
    Unbinder unbinder;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_prepaid_complete;
    }

    @Override
    protected void setUpView() {
        //设置title
        actionTitle.setText("充值成功");
        //设置点击事件
        initListener();
        //返回按键监听
        initBackListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        rechargeLeftTv.setOnClickListener(this);
        rechargeRightRight.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge_left_tv://进入我的钱包
                getActivity().finish();
                break;
            case R.id.recharge_right_right://返回界面
                setBack();
                break;
            case R.id.action_back://返回按键
                setBack();
                break;
        }
    }
    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment add = fragmentManager.findFragmentByTag("prepaid_complete");
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
}
