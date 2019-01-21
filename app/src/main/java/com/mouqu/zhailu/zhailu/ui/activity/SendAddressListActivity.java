package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.SendAddressListContract;
import com.mouqu.zhailu.zhailu.modle.activity.SendAddressListModel;
import com.mouqu.zhailu.zhailu.presenter.activity.SendAddressListPresenter;
import com.mouqu.zhailu.zhailu.ui.adapter.SendAddressRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.fragment.SendAddressNewReceiverFragment;
import com.mouqu.zhailu.zhailu.ui.widget.MyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class SendAddressListActivity extends BaseActivity<SendAddressListPresenter, SendAddressListModel> implements SendAddressListContract.View, View.OnClickListener {
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
    private List list = new ArrayList();


    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sendaddress_list;
    }

    @Override
    protected void setUpView() {

        //设置title
        actionTitle.setText("选择寄货地址");


        //设置事件监听
        initListener();
        //设置假数据
        initData();
        //设置recyclerview
        initRecyclerview();
    }

    @Override
    protected void setUpData() {

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
        //设置Adapter
        SendAddressRecyclerviewAdapter addressRecyclerviewAdapter = new SendAddressRecyclerviewAdapter(R.layout.adapter_address_layout, list);
        addressRecyclerview.setAdapter(addressRecyclerviewAdapter);
        addressRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                setBack();
            }
        });
    }

    private void initData() {
        //假数据设置
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    private void initListener() {
        addressBt.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.address_bt:
                //添加新地址
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new SendAddressNewReceiverFragment(), "new_address").commit();
                break;
            case R.id.action_back:
                setBack();
                break;
        }
    }

    private void setBack() {
        Intent intent = new Intent();
        intent.putExtra("result", "");
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }

    @Override
    public void onBackPressed() {
        setBack();
        super.onBackPressed();
    }


}
