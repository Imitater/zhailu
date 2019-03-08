package com.mouqukeji.hmdeer.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.IntegralListBean;
import com.mouqukeji.hmdeer.contract.activity.JiFenListContract;
import com.mouqukeji.hmdeer.modle.activity.JiFenListModel;
import com.mouqukeji.hmdeer.presenter.activity.JiFenListPresenter;
import com.mouqukeji.hmdeer.ui.adapter.JiFenRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;

public class JiFenListActivity extends BaseActivity<JiFenListPresenter, JiFenListModel> implements JiFenListContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.jefen_list_recyclerview)
    RecyclerView jefenListRecyclerview;
    private String spUserID;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.jiFenList(spUserID,mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_jefen;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void jiFenList(IntegralListBean bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        jefenListRecyclerview.setLayoutManager(linearLayoutManager);
        JiFenRecyclerviewAdapter jiFenListRecyclerAdapter = new JiFenRecyclerviewAdapter(R.layout.adapter_jifen_layout,bean.getIntegral());
        jefenListRecyclerview.setAdapter(jiFenListRecyclerAdapter);
    }
}
