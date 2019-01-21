package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.bean.AddressListBean;
import com.mouqu.zhailu.zhailu.bean.EditAddressBean;
import com.mouqu.zhailu.zhailu.contract.activity.TakeAddressListContract;
import com.mouqu.zhailu.zhailu.modle.activity.TakeAddressListModel;
import com.mouqu.zhailu.zhailu.presenter.activity.TakeAddressListPresenter;
import com.mouqu.zhailu.zhailu.ui.adapter.TakeAddressRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.fragment.TakeAddressNewReceiverFragment;
import com.mouqu.zhailu.zhailu.ui.widget.MyLinearLayoutManager;
import com.mouqu.zhailu.zhailu.util.GetSPData;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TakeAddressListActivity extends BaseActivity<TakeAddressListPresenter, TakeAddressListModel> implements TakeAddressListContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.address_recyclerview)
    RecyclerView addressRecyclerview;
    @BindView(R.id.address_bt)
    Button addressBt;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    private List<AddressListBean.AddressBean> addressList = new ArrayList<AddressListBean.AddressBean>();
    private int select;
    private TakeAddressRecyclerviewAdapter addressRecyclerviewAdapter;

    @Override
    protected void initViewAndEvents() {
        String spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getAddressList(spUserID,mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_takeaddress_list;
    }

    @Override
    protected void setUpView() {
        //設置title
        actionTitle.setText("选择收货地址");
        //設置按鍵監聽
        initListener();

    }



    private void initRecyclerview() {
        //设置Recyclerview 列表
        MyLinearLayoutManager layoutManager = new MyLinearLayoutManager(this);
        //设置Recyclerview禁止滑动
        layoutManager.setScrollEnabled(false);
        //设置布局管理器
        addressRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        for (int i = 0; i < addressList.size(); i++) {
            if (addressList.get(i).getIs_default().equals("1")){
                select = i;
            }
        }
        //设置Adapter
        addressRecyclerviewAdapter = new TakeAddressRecyclerviewAdapter(R.layout.adapter_address_layout, addressList,select);
        addressRecyclerview.setAdapter(addressRecyclerviewAdapter);
        //item 点击事件
        addressRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
            }
        });
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        addressBt.setOnClickListener(this);
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
            case R.id.address_bt:
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new TakeAddressNewReceiverFragment(), "new_address").commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        setBack();
        super.onBackPressed();
    }

    private void setBack() {
        Intent intent = new Intent();
        intent.putExtra("position", "");
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //修改默认项
        if(select!=addressRecyclerviewAdapter.getSelectedPos()){
            mMvpPresenter.editAddress(addressList.get(addressRecyclerviewAdapter.getSelectedPos()).getUser_id(),
                    addressList.get(addressRecyclerviewAdapter.getSelectedPos()).getId(),
                    addressList.get(addressRecyclerviewAdapter.getSelectedPos()).getName(),
                    addressList.get(addressRecyclerviewAdapter.getSelectedPos()).getTelephone(),
                    addressList.get(addressRecyclerviewAdapter.getSelectedPos()).getAddress(),
                    addressList.get(addressRecyclerviewAdapter.getSelectedPos()).getDetail(),
                    "1",mMultipleStateView);
        }
    }

    @Override
    public void getAddressList(AddressListBean bean) {
        for (int i = 0; i < bean.getAddress().size(); i++) {
            addressList.add(bean.getAddress().get(i));
        }
        //设置地址列表
        initRecyclerview();
    }

    @Override
    public void editAddress(EditAddressBean bean) {

    }

    @Override
    public void isEmpty() {
        addressRecyclerview.setVisibility(View.GONE);
    }
}
