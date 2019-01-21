package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.AddressEditContract;
import com.mouqu.zhailu.zhailu.modle.activity.AddressEditModel;
import com.mouqu.zhailu.zhailu.presenter.activity.AddressEditPresenter;

import butterknife.BindView;

public class AddressEditActivity extends BaseActivity<AddressEditPresenter, AddressEditModel> implements AddressEditContract.View, View.OnClickListener{
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
    TextView addressTvAddress;
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
        View actionBack = findViewById(R.id.action_back);

        //设置按钮事件监听
        actionBack.setOnClickListener(this);
        addressTvAddress.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                finish();
                break;
            case R.id.address_tv_address:
                Intent intent = new Intent(AddressEditActivity.this, SelectAddressActivity.class);//选择地址
                startActivity(intent);
                break;
        }
    }

}
