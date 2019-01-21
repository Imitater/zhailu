package com.mouqu.zhailu.zhailu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseFragment;
import com.mouqu.zhailu.zhailu.bean.AddAddressBean;
import com.mouqu.zhailu.zhailu.contract.fragment.ReceiverContract;
import com.mouqu.zhailu.zhailu.modle.fragment.ReceiverModel;
import com.mouqu.zhailu.zhailu.presenter.fragment.ReceiverPresenter;
import com.mouqu.zhailu.zhailu.ui.activity.SelectAddressActivity;
import com.mouqu.zhailu.zhailu.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ReceivierFragment extends BaseFragment<ReceiverPresenter, ReceiverModel> implements ReceiverContract.View, View.OnClickListener {


    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    Unbinder unbinder;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.receiver_address)
    TextView receiverAddress;
    @BindView(R.id.receive_name_ed)
    EditText receiveNameEd;
    @BindView(R.id.receive_number_et)
    EditText receiveNumberEt;
    @BindView(R.id.receive_detailed_address_ed)
    EditText receiveDetailedAddressEd;
    @BindView(R.id.receive_add_bt)
    Button receiveAddBt;
    Unbinder unbinder1;
    private String spUserID;

    @Override
    protected void initViewAndEvents() {
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_receiver;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(getActivity());
        //设置title
        actionTitle.setText("收件人信息");
        //按键监听
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        receiverAddress.setOnClickListener(this);
        receiveAddBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                  FragmentManager fragmentManager = getFragmentManager();
                Fragment add = fragmentManager.findFragmentByTag("receiver");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(add);
                fragmentTransaction.commit();
                break;
            case R.id.receiver_address:
                Intent intent = new Intent(getMContext(), SelectAddressActivity.class);//选择地址
                startActivity(intent);
                break;
            case R.id.receive_add_bt:
                //添加地址接口
                mMvpPresenter.addAddress(spUserID,receiveNameEd.getText().toString(),receiveNumberEt.getText().toString(),receiverAddress.getText().toString(),receiveDetailedAddressEd.getText().toString(),"1",mMultipleStateView);
                break;
        }
    }

    @Override
    public void addAddress(AddAddressBean bean) {
        Toast.makeText(getContext(),"添加地址成功",Toast.LENGTH_SHORT).show();
    }

}
