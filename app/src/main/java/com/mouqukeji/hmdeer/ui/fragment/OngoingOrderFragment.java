package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
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
import com.mouqukeji.hmdeer.base.BaseLazyFragment;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.contract.fragment.OngingOrderContract;
import com.mouqukeji.hmdeer.modle.fragment.OngoingOrderModel;
import com.mouqukeji.hmdeer.presenter.fragment.OngoingOrderPresenter;
import com.mouqukeji.hmdeer.ui.activity.BuyOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.SendOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.adapter.OngoingOrderRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class OngoingOrderFragment extends BaseLazyFragment<OngoingOrderPresenter, OngoingOrderModel> implements OngingOrderContract.View, View.OnClickListener {
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    Unbinder unbinder;
    @BindView(R.id.all_order_swiperefreshlayout)
    SwipeRefreshLayout allOrderSwiperefreshlayout;
    private int page = 1;
    private String spUserID;
    private OngoingOrderRecyclerviewAdapter ongoingOrderRecyclerviewAdapter;
    private int countPages;
    private List<AllOrderBean.TasksBean> tasks;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getProgressIndent(spUserID, "3", mMultipleStateView);
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
    protected void lazyLoad() {
        if (!mIsprepared || !mIsVisible || mHasLoadedOnce) {
            return;
        }
        mHasLoadedOnce = true;
        refreshData();
    }


    @Override
    public void onClick(View v) {

    }


    private void initSwipeRefresh() {
        //设置下拉刷新
        if (allOrderSwiperefreshlayout != null) {
            allOrderSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            allOrderSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    orderRecycler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMvpPresenter.getProgressIndent(spUserID, "3", mMultipleStateView);
                            if (ongoingOrderRecyclerviewAdapter != null) {
                                ongoingOrderRecyclerviewAdapter.notifyDataSetChanged();
                                ongoingOrderRecyclerviewAdapter.setUpFetching(false);
                                ongoingOrderRecyclerviewAdapter.setUpFetchEnable(false);
                            }
                            if (allOrderSwiperefreshlayout != null)
                                allOrderSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
    }

    @Override
    public void getProgressIndent(AllOrderBean bean) {
        page++;
        countPages = bean.getPages();
        if (llNoOrder != null && orderRecycler != null) {
            llNoOrder.setVisibility(View.GONE);
            orderRecycler.setVisibility(View.VISIBLE);
            tasks = bean.getTasks();
            //设置recyclerview
            setRecyclerview();
        }
        //设置上拉加载
        setUpLoad();
        //设置下拉刷新
        initSwipeRefresh();
    }


    @Override
    public void getIndentNext(AllOrderBean bean) {
        page++;
        //成功获取更多数据
        ongoingOrderRecyclerviewAdapter.addData(bean.getTasks());
        ongoingOrderRecyclerviewAdapter.loadMoreComplete();
    }

    private void setUpLoad() {
        ongoingOrderRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        ongoingOrderRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (tasks.size() >= 10) {
            ongoingOrderRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
                    ongoingOrderRecyclerviewAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.getIndentNext(spUserID, "3", page + "", mMultipleStateView);
                }
            }
        }, 1500);
    }

    @Override
    public void getEmpty() {
        if (llNoOrder != null && orderRecycler != null) {
            llNoOrder.setVisibility(View.VISIBLE);
            orderRecycler.setVisibility(View.GONE);
        }
        initSwipeRefresh();
    }

    @Override
    public void finishOrder(FinishOrderBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_M, 1);
        post(eventMessage);
    }

    private void refreshData() {
        page = 1;
        mMvpPresenter.getProgressIndent(spUserID,"3", mMultipleStateView);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (allOrderSwiperefreshlayout != null) {
            allOrderSwiperefreshlayout.setRefreshing(false);
        }
    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        ongoingOrderRecyclerviewAdapter = new OngoingOrderRecyclerviewAdapter(R.layout.adapter_ongoing_layout, tasks);
        orderRecycler.setAdapter(ongoingOrderRecyclerviewAdapter);
        ongoingOrderRecyclerviewAdapter.disableLoadMoreIfNotFullPage(orderRecycler);

        //确认收货
        ongoingOrderRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                mMvpPresenter.finishOrder(ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id(),spUserID,mMultipleStateView);
            }
        });

        //item 进入订单详情
        ongoingOrderRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("11")) {
                    //进行中
                    Intent intent = new Intent(getMContext(), TakeOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 202);
                }else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("12")){
                    //进行中
                    Intent intent = new Intent(getMContext(), BuyOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 302);
                }else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("13")){
                    //进行中
                    Intent intent = new Intent(getMContext(), SendOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 402);
                }else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("14")){
                    Intent intent = new Intent(getMContext(), DeliverOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 502);
                }else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("15")){
                    //进行中
                    Intent intent = new Intent(getMContext(), UniversalOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 602);
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //刷新列表
            refreshData();
        }
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_J) {
                //删除订单成功 刷新列表
                refreshData();
            }else if (event.getCode()==EventCode.EVENT_L){
                //已下单 刷新列表
                refreshData();
            }else if (event.getCode() == EventCode.EVENT_M) {
                //确认取件 刷新列表
                refreshData();
            }
        }
    }
}
