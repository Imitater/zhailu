package com.mouqukeji.hmdeer.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.contract.fragment.CancelledOrderContract;
import com.mouqukeji.hmdeer.modle.fragment.CancelledOrderModel;
import com.mouqukeji.hmdeer.presenter.fragment.CancelledOrderPresenter;
import com.mouqukeji.hmdeer.ui.adapter.CancelledOrderRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class CancelledOrderFragment extends BaseFragment<CancelledOrderPresenter, CancelledOrderModel> implements CancelledOrderContract.View, View.OnClickListener {
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    @BindView(R.id.all_order_swiperefreshlayout)
    SwipeRefreshLayout allOrderSwiperefreshlayout;
    private int page = 1;
    private String spUserID;
    private CancelledOrderRecyclerviewAdapter cancelledOrderRecyclerviewAdapter;
    private int countPages;
    private List<AllOrderBean.TasksBean> tasks;
    private boolean flag = true;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getProgressIndent(spUserID, "6", mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_order;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onStart() {
        super.onStart();
        flag = true;
    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        allOrderSwiperefreshlayout.setColorSchemeResources(R.color.blue);
        allOrderSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mMvpPresenter.getProgressIndent(spUserID, "6", mMultipleStateView);
                        if (cancelledOrderRecyclerviewAdapter != null) {
                            cancelledOrderRecyclerviewAdapter.notifyDataSetChanged();
                            cancelledOrderRecyclerviewAdapter.setUpFetching(false);
                            cancelledOrderRecyclerviewAdapter.setUpFetchEnable(false);
                        }
                        allOrderSwiperefreshlayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void getProgressIndent(AllOrderBean bean) {
        tasks = bean.getTasks();
        countPages = bean.getPages();
        if (flag) {
            flag = false;
            //设置recyclerview
            setRecyclerview(bean);
        }
        //设置上拉加载
        setUpLoad(bean);
        //设置下拉刷新
        initSwipeRefresh();
    }

    @Override
    public void getIndentNext(AllOrderBean bean) {
        tasks = bean.getTasks();
    }

    private void setUpLoad(AllOrderBean bean) {
        cancelledOrderRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        cancelledOrderRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (bean.getTasks().size() >= 10) {
            cancelledOrderRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();
                }
            }, orderRecycler);
        }
    }

    private void setLoadMore() {
        orderRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > countPages) {
                    //数据全部加载完毕
                    cancelledOrderRecyclerviewAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.getIndentNext(spUserID, "6", page + "", mMultipleStateView);
                    page++;
                    //成功获取更多数据
                    cancelledOrderRecyclerviewAdapter.addData(tasks);
                    cancelledOrderRecyclerviewAdapter.loadMoreComplete();
                }
            }
        }, 1500);
    }

    @Override
    public void getEmpty() {
        //暂无订单
        llNoOrder.setVisibility(View.VISIBLE);
        orderRecycler.setVisibility(View.GONE);
        initSwipeRefresh();
    }

    private void setRecyclerview(AllOrderBean bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置 多布局type
        //设置Adapter

        cancelledOrderRecyclerviewAdapter = new CancelledOrderRecyclerviewAdapter(R.layout.adapter_generation_order, bean.getTasks());
        orderRecycler.setAdapter(cancelledOrderRecyclerviewAdapter);
        cancelledOrderRecyclerviewAdapter.disableLoadMoreIfNotFullPage(orderRecycler);
        cancelledOrderRecyclerviewAdapter.setPreLoadNumber(0);
    }

}
