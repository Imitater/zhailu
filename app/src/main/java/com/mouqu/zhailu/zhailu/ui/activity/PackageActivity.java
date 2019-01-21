package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.bean.PackageBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.contract.activity.AddressEditContract;
import com.mouqu.zhailu.zhailu.contract.activity.PackageContract;
import com.mouqu.zhailu.zhailu.modle.activity.AddressEditModel;
import com.mouqu.zhailu.zhailu.modle.activity.PackageModel;
import com.mouqu.zhailu.zhailu.presenter.activity.AddressEditPresenter;
import com.mouqu.zhailu.zhailu.presenter.activity.PackagePresenter;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.GetSPData;

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
        setListeenr();
    }

    private void setListeenr() {
        packagePreferential.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.package_preferential://优惠券列表
                Intent intent = new Intent(PackageActivity.this, PreferentialListActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    public void getMoney(PackageBean bean) {
        packageBalance.setText(bean.getBalance());
    }
}
