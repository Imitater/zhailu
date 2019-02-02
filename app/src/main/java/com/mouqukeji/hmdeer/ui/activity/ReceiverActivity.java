package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.AddAddressBean;
import com.mouqukeji.hmdeer.contract.fragment.ReceiverContract;
import com.mouqukeji.hmdeer.modle.fragment.ReceiverModel;
import com.mouqukeji.hmdeer.presenter.fragment.ReceiverPresenter;
import com.mouqukeji.hmdeer.util.GetSPData;


import butterknife.BindView;

public class ReceiverActivity extends BaseActivity<ReceiverPresenter, ReceiverModel> implements ReceiverContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.receive_name_ed)
    EditText receiveNameEd;
    @BindView(R.id.receive_number_et)
    EditText receiveNumberEt;
    @BindView(R.id.receiver_address)
    TextView receiverAddress;
    @BindView(R.id.receive_detailed_address_ed)
    EditText receiveDetailedAddressEd;
    @BindView(R.id.receive_add_bt)
    Button receiveAddBt;
    private String spUserID;
    private String select_addressdata;
    private String select_point_lat;
    private String select_point_lon;



    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_receiver;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(this);
        //设置title
        actionTitle.setText("收件人信息");
        //按键监听
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        receiverAddress.setOnClickListener(this);
        receiveAddBt.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.receiver_address:
                startActivityForResult(new Intent(this, SelectLocationActivity.class), 7);
                break;
            case R.id.receive_add_bt:
                //添加地址接口
                mMvpPresenter.addAddress(spUserID, receiveNameEd.getText().toString(), receiveNumberEt.getText().toString(),
                        select_addressdata, receiveDetailedAddressEd.getText().toString(), "1", select_point_lat, select_point_lon, mMultipleStateView);
                finish();
                break;
            case R.id.action_back:
                finish();
                break;
        }
    }

    @Override
    public void addAddress(AddAddressBean bean) {
        Toast.makeText(this, "添加地址成功", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!TextUtils.isEmpty(data.getStringExtra("select_address"))) {
            //获取地址
            select_addressdata = data.getStringExtra("select_address");
            receiverAddress.setText(select_addressdata);
            //经度
            select_point_lat = data.getStringExtra("select_point_lat");
            //纬度
            select_point_lon = data.getStringExtra("select_point_lon");
        }
    }
}
