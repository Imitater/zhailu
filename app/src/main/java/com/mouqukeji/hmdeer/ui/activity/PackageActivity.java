package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.contract.activity.PackageContract;
import com.mouqukeji.hmdeer.modle.activity.PackageModel;
import com.mouqukeji.hmdeer.presenter.activity.PackagePresenter;
import com.mouqukeji.hmdeer.ui.fragment.ConsumptionListFragment;
import com.mouqukeji.hmdeer.ui.fragment.PrepaidCompleteFragment;
import com.mouqukeji.hmdeer.ui.fragment.RechangeListFragment;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PackageActivity extends BaseActivity<PackagePresenter, PackageModel> implements PackageContract.View, View.OnClickListener {


    @BindView(R.id.package_actionbar)
    MyActionBar packageActionbar;
    @BindView(R.id.package_balance)
    TextView packageBalance;
    @BindView(R.id.package_rechange)
    TextView packageRechange;
    @BindView(R.id.package_rechange_list)
    LinearLayout packageRechangeList;
    @BindView(R.id.package_consumption_list)
    LinearLayout packageConsumptionList;
    @BindView(R.id.package_preferential)
    LinearLayout packagePreferential;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    public static PackageActivity instance = null;
    @Override
    protected void initViewAndEvents() {
        String spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getMoney(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_package;
    }

    @Override
    protected void setUpView() {
        //设置标记
        instance = this;
        packageActionbar.setTitle("我的钱包");
        setListeenr();
    }

    private void setListeenr() {
        packageRechange.setOnClickListener(this);
        packagePreferential.setOnClickListener(this);
        packageRechangeList.setOnClickListener(this);
        packageConsumptionList.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.package_preferential://优惠券列表
                Intent intent = new Intent(PackageActivity.this, PreferentialListActivity.class);
                startActivity(intent);
                break;
            case R.id.package_rechange://充值按钮
                Intent intent1 = new Intent(PackageActivity.this, ReChargeActivity.class);
                intent1.putExtra("rechange_type", "2");
                startActivity(intent1);
                break;
            case R.id.package_rechange_list://充值明细
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RechangeListFragment(), "rechange_list").commit();
                break;
            case R.id.package_consumption_list://消费明细
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new ConsumptionListFragment(), "consumption_list").commit();
                break;
        }
    }


    @Override
    public void getMoney(PackageBean bean) {
        packageBalance.setText(bean.getBalance() + "");
    }



}
