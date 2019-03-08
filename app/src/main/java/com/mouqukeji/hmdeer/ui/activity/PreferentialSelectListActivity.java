package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.contract.activity.PreferentialListContract;
import com.mouqukeji.hmdeer.modle.activity.PreferentiaListModel;
import com.mouqukeji.hmdeer.presenter.activity.PreferentialListPresenter;
import com.mouqukeji.hmdeer.ui.adapter.PreferentialSelectRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PreferentialSelectListActivity extends BaseActivity<PreferentialListPresenter, PreferentiaListModel> implements PreferentialListContract.View, View.OnClickListener {
    @BindView(R.id.preferential_recyclerview)
    RecyclerView preferentialRecyclerview;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    private String cate_id;

    @Override
    protected void initViewAndEvents() {
        String spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_preferentiallist;
    }

    @Override
    protected void setUpView() {
        Intent intent = getIntent();
        cate_id = intent.getStringExtra("cate_id");
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
        actionTitle.setText("优惠券");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_back:
                setBack();
                break;
        }
    }

    @Override
    public void getPreferentialList(PreferentialBean bean) {
        //设置列表
        setRecyclerView(bean);
    }

    private void setRecyclerView(final PreferentialBean bean) {
        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        preferentialRecyclerview.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        final PreferentialSelectRecyclerviewAdapter preferentialSelectRecyclerviewAdapter = new PreferentialSelectRecyclerviewAdapter(R.layout.adapter_preferential_layout, bean.getCoupons(), cate_id);
        preferentialRecyclerview.setAdapter(preferentialSelectRecyclerviewAdapter);
        preferentialSelectRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (preferentialSelectRecyclerviewAdapter.getData().get(i).getCate_id().equals(cate_id) || preferentialSelectRecyclerviewAdapter.getData().get(i).getCate_id().equals("10")) {
                    Intent intent = new Intent();
                    intent.putExtra("num", bean.getCoupons().get(i).getNum());
                    intent.putExtra("couponId", bean.getCoupons().get(i).getCoupon_id());
                    setResult(RESULT_OK, intent);
                    //关闭Activity
                    finish();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        setBack();
        super.onBackPressed();
    }

    private void setBack() {
        Intent intent = new Intent();
        intent.putExtra("num", "0");
        intent.putExtra("couponId", "0");
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }

}
