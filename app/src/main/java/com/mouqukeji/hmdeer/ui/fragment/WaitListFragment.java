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
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.contract.fragment.WaitListContract;
import com.mouqukeji.hmdeer.modle.fragment.WaitListModel;
import com.mouqukeji.hmdeer.presenter.fragment.WaitListPresenter;
import com.mouqukeji.hmdeer.ui.activity.BuyIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.PayCompleteActivity;
import com.mouqukeji.hmdeer.ui.activity.SendIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.adapter.WaitListRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class WaitListFragment extends BaseLazyFragment<WaitListPresenter, WaitListModel> implements WaitListContract.View, View.OnClickListener {
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    Unbinder unbinder;
    @BindView(R.id.all_order_swiperefreshlayout)
    SwipeRefreshLayout allOrderSwiperefreshlayout;
    private int page = 1;
    private String spUserID;
    private WaitListRecyclerviewAdapter waitListRecyclerviewAdapter;
    private int countPages;
    private List<AllOrderBean.TasksBean> tasks;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getProgressIndent(spUserID, "2", mMultipleStateView);
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
        page=1;
        mMvpPresenter.getProgressIndent(spUserID, "2", mMultipleStateView);
    }


    @Override
    public void onClick(View v) {

    }


    private void initSwipeRefresh() {
        //设置下拉刷新
        if (allOrderSwiperefreshlayout!=null) {
            allOrderSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            allOrderSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    orderRecycler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMvpPresenter.getProgressIndent(spUserID, "2", mMultipleStateView);
                            if (waitListRecyclerviewAdapter != null) {
                                waitListRecyclerviewAdapter.notifyDataSetChanged();
                                waitListRecyclerviewAdapter.setUpFetching(false);
                                waitListRecyclerviewAdapter.setUpFetchEnable(false);
                            }
                            if (allOrderSwiperefreshlayout!=null)
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
    public void onPause() {
        super.onPause();
        if (allOrderSwiperefreshlayout != null) {
            allOrderSwiperefreshlayout.setRefreshing(false);
        }
    }

    @Override
    public void getIndentNext(AllOrderBean bean) {
        page++;
        //成功获取更多数据
        waitListRecyclerviewAdapter.addData(bean.getTasks());
        waitListRecyclerviewAdapter.loadMoreComplete();
    }

    private void setUpLoad() {
        waitListRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        waitListRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (tasks.size() >= 10) {
            waitListRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
                    waitListRecyclerviewAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.getIndentNext(spUserID, "2", page + "", mMultipleStateView);
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
    public void cancelOrder(CancelOrderBean bean) {
        //发送消息
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_I, 1);
        post(eventMessage);
    }

    private void refreshData() {
        page = 1;
        mMvpPresenter.getProgressIndent(spUserID, "2", mMultipleStateView);
    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        waitListRecyclerviewAdapter = new WaitListRecyclerviewAdapter(R.layout.adapter_wait_order, tasks);
        orderRecycler.setAdapter(waitListRecyclerviewAdapter);
        waitListRecyclerviewAdapter.disableLoadMoreIfNotFullPage(orderRecycler);
        waitListRecyclerviewAdapter.setPreLoadNumber(0);
        waitListRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_cancell, null);
                DialogUtils.cancellDialog(getMContext(),inflate_items,true,true,mMvpPresenter,mMultipleStateView,spUserID,waitListRecyclerviewAdapter.getData().get(i).getTask_id());
                //取消订单
                mMvpPresenter.cancelOrder(waitListRecyclerviewAdapter.getData().get(i).getTask_id(),spUserID,mMultipleStateView);
            }
        });
        //item 点击  详情页面
        waitListRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (waitListRecyclerviewAdapter.getData().get(i).getCate_id().equals("11")) {
                    //未接单
                    Intent intent = new Intent(getMContext(), TakeIngOrderInfoActivity.class);
                    intent.putExtra("taskId", waitListRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", waitListRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 201);
                }else if (waitListRecyclerviewAdapter.getData().get(i).getCate_id().equals("12")){
                    //未接单
                    Intent intent = new Intent(getMContext(), BuyIngOrderInfoActivity.class);
                    intent.putExtra("taskId", waitListRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", waitListRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 301);
                }else if (waitListRecyclerviewAdapter.getData().get(i).getCate_id().equals("13")){
                    //未接单
                    Intent intent = new Intent(getMContext(), SendIngOrderInfoActivity.class);
                    intent.putExtra("taskId", waitListRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", waitListRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 401);
                }else if (waitListRecyclerviewAdapter.getData().get(i).getCate_id().equals("14")){
                    //未接单
                    Intent intent = new Intent(getMContext(), DeliverIngOrderInfoActivity.class);
                    intent.putExtra("taskId", waitListRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", waitListRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 501);
                }else if (waitListRecyclerviewAdapter.getData().get(i).getCate_id().equals("15")){
                    //未接单
                    Intent intent = new Intent(getMContext(), UniversalIngOrderInfoActivity.class);
                    intent.putExtra("taskId", waitListRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", waitListRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 601);
                }
            }
        });
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_I) {
                //取消订单成功 刷新列表
                refreshData();
            }else if (event.getCode()==EventCode.EVENT_J){
                //支付成功 刷新列表
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //刷新列表
            refreshData();
        }
    }

}
