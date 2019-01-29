package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.contract.activity.PreferentialListContract;
import com.mouqukeji.hmdeer.modle.activity.PreferentiaListModel;
import com.mouqukeji.hmdeer.presenter.activity.PreferentialListPresenter;
import com.mouqukeji.hmdeer.ui.adapter.PreferentialRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;


public class PreferentialListActivity extends BaseActivity<PreferentialListPresenter, PreferentiaListModel> implements PreferentialListContract.View, View.OnClickListener {
    @BindView(R.id.preferential_ctionbar)
    MyActionBar preferentialCtionbar;
    @BindView(R.id.preferential_recyclerview)
    RecyclerView preferentialRecyclerview;

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
        preferentialCtionbar.setTitle("优惠劵");
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {

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
        PreferentialRecyclerviewAdapter preferentialRecyclerviewAdapter = new PreferentialRecyclerviewAdapter(R.layout.adapter_preferential_layout,bean.getCoupons() );
        preferentialRecyclerview.setAdapter(preferentialRecyclerviewAdapter);
        preferentialRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent();
                intent.putExtra("num",bean.getCoupons().get(i).getNum());
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
            }
        });
    }

     
}
