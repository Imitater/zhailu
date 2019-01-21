package com.mouqu.zhailu.zhailu.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseFragment;
import com.mouqu.zhailu.zhailu.contract.fragment.SendEditContract;
import com.mouqu.zhailu.zhailu.modle.fragment.SendEditModel;
import com.mouqu.zhailu.zhailu.presenter.fragment.SendEditPresenter;
import com.mouqu.zhailu.zhailu.ui.activity.SelectAddressActivity;

import butterknife.BindView;

public class SendEditFragment extends BaseFragment<SendEditPresenter, SendEditModel> implements SendEditContract.View, View.OnClickListener {


    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.address_edit_name)
    EditText addressEditName;
    @BindView(R.id.address_edit_number)
    TextView addressEditNumber;
    @BindView(R.id.address_tv_address)
    TextView addressEditAddress;
    @BindView(R.id.address_edit_address_info)
    EditText addressEditAddressInfo;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_address_edit;
    }

    @Override
    protected void setUpView() {
        //设置title
        actionTitle.setText("寄件地址");
        //设置点击事件
        initListener();
        //返回按键监听
        initBackListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        actionSave.setOnClickListener(this);
        addressEditAddress.setOnClickListener(this);
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
                setBack();
                break;
            case R.id.action_save://保存

                break;
            case R.id.address_tv_address:
                Intent intent = new Intent(getMContext(), SelectAddressActivity.class);//选择地址
                startActivity(intent);
                break;
        }
    }

    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment add = fragmentManager.findFragmentByTag("edit_address");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(add);
        fragmentTransaction.commit();
    }


}
