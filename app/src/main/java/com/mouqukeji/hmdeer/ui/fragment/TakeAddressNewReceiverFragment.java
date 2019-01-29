package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.bean.AddAddressBean;
import com.mouqukeji.hmdeer.contract.fragment.TakeAddressNewReceiverContract;
import com.mouqukeji.hmdeer.modle.fragment.TakeAddressNewReceiverModel;
import com.mouqukeji.hmdeer.presenter.fragment.TakeAddressNewReceiverPresenter;
import com.mouqukeji.hmdeer.ui.activity.SelectAddressActivity;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;
import butterknife.Unbinder;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;


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
    private String select_addressdata;
    private String select_point_lat;
    private String select_point_lon;


    @Override
    protected void initViewAndEvents() {

    }
    @Override
    protected boolean isRegisteredEventBus() {
        return true;
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
                setBack();
                break;
            case R.id.receiver_address:
                this.startActivityForResult(new Intent(getMContext(), SelectAddressActivity.class), 9);
                break;
            case R.id.receive_add_bt:
                mMvpPresenter.addAddress(spUserID, receiveNameEd.getText().toString(), receiveNumberEt.getText().toString(),
                        select_addressdata, receiveDetailedAddressEd.getText().toString(), "0",select_point_lat,select_point_lon, mMultipleStateView);
                //发送消息
                EventMessage eventMessage = new EventMessage(EventCode.EVENT_B, "1");//通知添加成功   刷新页面
                post(eventMessage);
                setBack();
                 break;
        }
    }

    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment add = fragmentManager.findFragmentByTag("new_address");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(add);
        fragmentTransaction.commit();
    }


    @Override
    public void addAddress(AddAddressBean bean) {
        Toast.makeText(getMContext(),"添加地址成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
