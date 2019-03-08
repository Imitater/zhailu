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
import com.mouqukeji.hmdeer.ui.adapter.PreferentialRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;


public class PreferentialListActivity extends BaseActivity<PreferentialListPresenter, PreferentiaListModel> implements PreferentialListContract.View, View.OnClickListener {
    @BindView(R.id.preferential_recyclerview)
    RecyclerView preferentialRecyclerview;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;

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
        actionTitle.setText("优惠劵");
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_back:
                finish();
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
        PreferentialRecyclerviewAdapter preferentialRecyclerviewAdapter = new PreferentialRecyclerviewAdapter(R.layout.adapter_preferential_layout, bean.getCoupons());
        preferentialRecyclerview.setAdapter(preferentialRecyclerviewAdapter);
        preferentialRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
               if (bean.getCoupons().get(i).getCate_id().equals("10")){
                   //跳转首页
                   EventMessage eventMessage = new EventMessage(EventCode.EVENT_G, 1);
                   post(eventMessage);
                   PackageActivity.instance.finish();
                   finish();
               }else if (bean.getCoupons().get(i).getCate_id().equals("11")){
                   Intent intent = new Intent(PreferentialListActivity.this,HelpTakeActivity.class);
                   intent.putExtra("cate_id",bean.getCoupons().get(i).getCate_id());
                   startActivity(intent);
                   PackageActivity.instance.finish();
                   finish();
               }else if (bean.getCoupons().get(i).getCate_id().equals("12")){
                   Intent intent = new Intent(PreferentialListActivity.this,HelpBuyActivity.class);
                   intent.putExtra("cate_id",bean.getCoupons().get(i).getCate_id());
                   startActivity(intent);
                   PackageActivity.instance.finish();
                   finish();
               }else if (bean.getCoupons().get(i).getCate_id().equals("13")){
                   Intent intent = new Intent(PreferentialListActivity.this,HelpSendActivity.class);
                   intent.putExtra("cate_id",bean.getCoupons().get(i).getCate_id());
                   startActivity(intent);
                   PackageActivity.instance.finish();
                   finish();
               }else if (bean.getCoupons().get(i).getCate_id().equals("14")){
                   Intent intent = new Intent(PreferentialListActivity.this,HelpDeliverActivity.class);
                   intent.putExtra("cate_id",bean.getCoupons().get(i).getCate_id());
                   startActivity(intent);
                   PackageActivity.instance.finish();
                   finish();
               }else if (bean.getCoupons().get(i).getCate_id().equals("15")){
                   Intent intent = new Intent(PreferentialListActivity.this,HelpUniversalActivity.class);
                   intent.putExtra("cate_id",bean.getCoupons().get(i).getCate_id());
                   startActivity(intent);
                   PackageActivity.instance.finish();
                   finish();
               }
            }
        });
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }
}
