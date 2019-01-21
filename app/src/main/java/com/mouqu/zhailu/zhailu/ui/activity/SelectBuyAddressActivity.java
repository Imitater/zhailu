package com.mouqu.zhailu.zhailu.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.MapView;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseMapActivity;
 import com.mouqu.zhailu.zhailu.contract.activity.SelectBuyAddressContract;
 import com.mouqu.zhailu.zhailu.modle.activity.SelectBuyAddressModel;
 import com.mouqu.zhailu.zhailu.presenter.activity.SelectBuyAddressPresenter;


import butterknife.BindView;

public class SelectBuyAddressActivity extends BaseMapActivity<SelectBuyAddressPresenter, SelectBuyAddressModel> implements SelectBuyAddressContract.View, View.OnClickListener{
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.select_location)
    LinearLayout selectLocation;
    @BindView(R.id.select_search_et)
    EditText selectSearchEt;
    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.select_cancal)
    TextView selectCancal;
    @BindView(R.id.select_recyclerview)
    RecyclerView selectRecyclerview;
    @BindView(R.id.map)
    MapView map;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_select_buy_address;
    }

    @Override
    protected void setUpView() {
        //设置title
        actionTitle.setText("选择地址");

        //设置点击事件监听
        initListener();
        //显示保存按钮
        actionSave.setVisibility(View.VISIBLE);
    }

    private void initListener() {
        selectLocation.setOnClickListener(this);
        selectCancal.setOnClickListener(this);
    }


    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_location://选择市
                break;
            case R.id.select_cancal://取消

                break;
            case R.id.action_save://保存
                break;
        }
    }
}
