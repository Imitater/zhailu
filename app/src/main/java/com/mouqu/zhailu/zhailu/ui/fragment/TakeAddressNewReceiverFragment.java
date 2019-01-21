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
import com.mouqu.zhailu.zhailu.contract.fragment.TakeAddressNewReceiverContract;
import com.mouqu.zhailu.zhailu.modle.fragment.TakeAddressNewReceiverModel;
import com.mouqu.zhailu.zhailu.presenter.fragment.TakeAddressNewReceiverPresenter;
import com.mouqu.zhailu.zhailu.ui.activity.SelectAddressActivity;
import com.mouqu.zhailu.zhailu.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class TakeAddressNewReceiverFragment extends BaseFragment<TakeAddressNewReceiverPresenter, TakeAddressNewReceiverModel> implements TakeAddressNewReceiverContract.View, View.OnClickListener {


    Unbinder unbinder;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
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
        //設置title
        actionTitle.setText("选择收货地址");
        //設置按鍵監聽
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
                Fragment add = fragmentManager.findFragmentByTag("new_address");
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.remove(add);
                fragmentTransaction.commit();
                break;
            case R.id.receiver_address:
                Intent intent = new Intent(getMContext(), SelectAddressActivity.class);//选择地址
                startActivity(intent);
                break;
            case R.id.receive_add_bt:
                mMvpPresenter.addAddress(spUserID,receiveNameEd.getText().toString(),receiveNumberEt.getText().toString(),receiverAddress.getText().toString(),receiveDetailedAddressEd.getText().toString(),"1",mMultipleStateView);
                break;
        }
    }


    @Override
    public void addAddress(AddAddressBean bean) {
        Toast.makeText(getContext(),"添加地址成功",Toast.LENGTH_SHORT).show();
    }
}
