package com.mouqukeji.hmdeer.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.bean.ConsumptionListBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.contract.fragment.ConsumptionListContract;
import com.mouqukeji.hmdeer.modle.fragment.ConsumptionListModel;
import com.mouqukeji.hmdeer.presenter.fragment.ConsumptionListPresenter;
import com.mouqukeji.hmdeer.ui.adapter.ConsmptionListRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ConsumptionListFragment extends BaseFragment<ConsumptionListPresenter, ConsumptionListModel> implements ConsumptionListContract.View, View.OnClickListener {
    @BindView(R.id.consumptionlist_recyclerview)
    RecyclerView consumptionlistRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.consumptionlist_swiperefreshlayout)
    SwipeRefreshLayout consumptionlistSwiperefreshlayout;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.consumption_count)
    TextView consumptionCount;
    private String spUserID;
    private int page;
    private int pageCount;
    private ConsmptionListRecyclerviewAdapter consumptionListRecyclerviewAdapter;
    //刷新标志
    boolean isErr = true;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(getActivity());
        page = 0;
        mMvpPresenter.getConsumptionList(spUserID, page + "", mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_consumptionlist;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("充值明细");
        setListener();
        initBackListener();
    }

    private void setListener() {
        actionBack.setOnClickListener(this);
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
        }
    }

    private void setUpLoad(ConsumptionListBean bean) {
        consumptionListRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        consumptionListRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (bean.getBuy().getConsume().size() >= 15) {
            onLoadMore();
        }
    }

    private void onLoadMore() {
        consumptionListRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                consumptionlistRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (page > pageCount) {
                            //数据全部加载完毕
                            consumptionListRecyclerviewAdapter.loadMoreEnd();
                        } else {
                            mMvpPresenter.getConsumptionListNext(spUserID, page + "", mMultipleStateView);
                        }

                    }

                }, 1500);
            }
        }, consumptionlistRecyclerview);
    }


    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment consumption_list = fragmentManager.findFragmentByTag("consumption_list");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(consumption_list);
        fragmentTransaction.commit();
    }


    private void initSwipeRefresh() {
        //设置下拉刷新
        consumptionlistSwiperefreshlayout.setColorSchemeResources(R.color.blue);
        consumptionlistSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                consumptionlistRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        mMvpPresenter.getConsumptionList(spUserID, page + "", mMultipleStateView);
                        if (consumptionListRecyclerviewAdapter != null) {
                            consumptionListRecyclerviewAdapter.notifyDataSetChanged();
                            consumptionListRecyclerviewAdapter.setUpFetching(false);
                            consumptionListRecyclerviewAdapter.setUpFetchEnable(false);
                            consumptionlistSwiperefreshlayout.setRefreshing(false);
                        }
                        if (consumptionlistSwiperefreshlayout != null)
                            consumptionlistSwiperefreshlayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void getConsumptionList(ConsumptionListBean bean) {
        page++;
        consumptionCount.setText(bean.getBuy().getTotal());
        pageCount = bean.getBuy().getPages();
        if (consumptionlistRecyclerview != null) {
            //设置recyclerview
            setRecyclerview(bean);
        }
          //设置上拉加载
        setUpLoad(bean);
        //设置下拉刷新
        initSwipeRefresh();
    }

    @Override
    public void getConsumptionListNext(ConsumptionListBean bean) {
        page++;
        //成功获取更多数据
        consumptionListRecyclerviewAdapter.addData(bean.getBuy().getConsume());
        consumptionListRecyclerviewAdapter.loadMoreComplete();
    }

    @Override
    public void isEmpty() {
        //暂无订单
        consumptionlistRecyclerview.setVisibility(View.GONE);
        initSwipeRefresh();
    }

    private void setRecyclerview(ConsumptionListBean bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        consumptionlistRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        consumptionListRecyclerviewAdapter = new ConsmptionListRecyclerviewAdapter(R.layout.consumption_list_item, bean.getBuy().getConsume(), bean.getBuy().getTotal());
        consumptionlistRecyclerview.setAdapter(consumptionListRecyclerviewAdapter);
        consumptionListRecyclerviewAdapter.disableLoadMoreIfNotFullPage(consumptionlistRecyclerview);
    }

    private void initBackListener() {
        //返回键监听
        getContentView().setFocusableInTouchMode(true);
        getContentView().requestFocus();
        getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    setBack();
                    return true;
                }
                return false;
            }
        });
    }


}
