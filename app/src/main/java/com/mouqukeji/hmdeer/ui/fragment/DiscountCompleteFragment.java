package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.ui.activity.PreferentialListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DiscountCompleteFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.discountcomplete_bt)
    Button discountcompleteBt;
    Unbinder unbinder;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_discountcomplete;
    }

    @Override
    protected void setUpView() {
        initListener();
        //返回按键监听
        initBackListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        discountcompleteBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    private void initBackListener() {
        //返回键监听
        getContentView().setFocusableInTouchMode(true);
        getContentView().requestFocus();
        getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                   getActivity().finish();
                    return true;
                }
                return false;
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.action_back:
                getActivity().finish();
                break;
            case R.id.discountcomplete_bt:
                Intent intent = new Intent(getMContext(), PreferentialListActivity.class);//进入优惠券列表
                getMContext().startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
