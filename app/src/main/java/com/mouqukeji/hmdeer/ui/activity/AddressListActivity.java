package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.AddressListBean;
import com.mouqukeji.hmdeer.bean.DeleteAddressBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.contract.activity.TakeAddressListContract;
import com.mouqukeji.hmdeer.modle.activity.TakeAddressListModel;
import com.mouqukeji.hmdeer.presenter.activity.TakeAddressListPresenter;
import com.mouqukeji.hmdeer.ui.adapter.TakeAddressRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.fragment.TakeAddressNewReceiverFragment;
import com.mouqukeji.hmdeer.ui.widget.MyLinearLayoutManager;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.SpUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AddressListActivity extends BaseActivity<TakeAddressListPresenter, TakeAddressListModel> implements TakeAddressListContract.View, View.OnClickListener {
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
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.address_list_relativelayout)
    RelativeLayout addressListRelativelayout;
    private List<AddressListBean.AddressBean> addressList = null;
    private int select=-1;
    private TakeAddressRecyclerviewAdapter addressRecyclerviewAdapter;
    private String spUserID;
    private List<AddressListBean.AddressBean> address;


    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getAddressList(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_takeaddress_list;
    }

    @Override
    protected void setUpView() {
        //設置title
        actionTitle.setText("选择地址");
        //設置按鍵監聽
        initListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMvpPresenter.getAddressList(spUserID, mMultipleStateView);
        addressListRelativelayout.invalidate();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
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
            if (addressList.get(i).getIs_default().equals("1")) {
                select = i;
            }
        }
        //设置Adapter
        addressRecyclerviewAdapter = new TakeAddressRecyclerviewAdapter(R.layout.adapter_address_layout, addressList, select);
        addressRecyclerview.setAdapter(addressRecyclerviewAdapter);

        //设置滑动删除
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(addressRecyclerviewAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(addressRecyclerview);

        //设置删除监听
        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {
            @Override
            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {
            }

            @Override
            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

            }

            @Override
            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                //删除接口
                mMvpPresenter.deleteAddress(spUserID, addressList.get(pos).getId(), mMultipleStateView);
            }


            @Override
            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {
            }
        };

        // 开启滑动删除
        addressRecyclerviewAdapter.enableSwipeItem();
        addressRecyclerviewAdapter.setOnItemSwipeListener(onItemSwipeListener);

        //item 点击事件
        addressRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //关闭Activity
                Intent intent = new Intent();
                intent.putExtra("id", addressList.get(position).getId());
                intent.putExtra("name", addressList.get(position).getName());
                intent.putExtra("number", addressList.get(position).getTelephone());
                intent.putExtra("address", addressList.get(position).getAddress());
                intent.putExtra("detail", addressList.get(position).getDetail());
                intent.putExtra("lat", addressList.get(position).getLat());
                intent.putExtra("lng", addressList.get(position).getLng());
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
                if (address.size()==0) {
                    getSupportFragmentManager().beginTransaction().add(R.id.framelayout, TakeAddressNewReceiverFragment.newInstance("1"), "new_address").commit();
                }else{
                    getSupportFragmentManager().beginTransaction().add(R.id.framelayout, TakeAddressNewReceiverFragment.newInstance("0"), "new_address").commit();
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (addressList.size()!=0) {
            //修改默认项
            int position = SpUtils.getInt("position", this);
            if (select != position) {
                mMvpPresenter.editAddress(addressList.get(position).getUser_id(),
                        addressList.get(position).getId(),
                        addressList.get(position).getName(),
                        addressList.get(position).getTelephone(),
                        addressList.get(position).getAddress(),
                        addressList.get(position).getDetail(),
                        "1", mMultipleStateView);
            }
        }
    }

    @Override
    public void getAddressList(AddressListBean bean) {
        address = bean.getAddress();
        addressList = new ArrayList<AddressListBean.AddressBean>();
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

    @Override
    public void deleteAddress(DeleteAddressBean bean) {
        mMvpPresenter.getAddressList(spUserID, mMultipleStateView);
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            mMvpPresenter.getAddressList(spUserID, mMultipleStateView);
        }
    }

    @Override
    public void onBackPressed() {
        setBack();
        super.onBackPressed();
    }

    private void setBack() {
        Intent intent = new Intent();
        intent.putExtra("id", "");
        intent.putExtra("name", "");
        intent.putExtra("number", "");
        intent.putExtra("address", "");
        intent.putExtra("detail", "");
        intent.putExtra("lat", "");
        intent.putExtra("lng", "");
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }

}
