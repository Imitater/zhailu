package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.base.BaseLazyFragment;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.DeleteOrderBean;
import com.mouqukeji.hmdeer.contract.fragment.CancelledOrderContract;
import com.mouqukeji.hmdeer.modle.fragment.CancelledOrderModel;
import com.mouqukeji.hmdeer.presenter.fragment.CancelledOrderPresenter;
import com.mouqukeji.hmdeer.ui.activity.BuyIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.SendIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.adapter.CancelledOrderRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import java.util.List;
import butterknife.BindView;

import static android.app.Activity.RESULT_OK;
import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class CancelledOrderFragment extends BaseLazyFragment<CancelledOrderPresenter, CancelledOrderModel> implements CancelledOrderContract.View, View.OnClickListener {
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
        if (allOrderSwiperefreshlayout!=null) {
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
    public void getIndentNext(AllOrderBean bean) {
        page++;
        //成功获取更多数据
        cancelledOrderRecyclerviewAdapter.addData(bean.getTasks());
        cancelledOrderRecyclerviewAdapter.loadMoreComplete();
    }

    private void setUpLoad() {
        cancelledOrderRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        cancelledOrderRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (tasks.size() >= 10) {
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
    public void deleteOrder(DeleteOrderBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_Z, 1);
        post(eventMessage);
    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置 多布局type
        //设置Adapter
        cancelledOrderRecyclerviewAdapter = new CancelledOrderRecyclerviewAdapter(R.layout.adapter_generation_order, tasks);
        orderRecycler.setAdapter(cancelledOrderRecyclerviewAdapter);
        cancelledOrderRecyclerviewAdapter.disableLoadMoreIfNotFullPage(orderRecycler);
        cancelledOrderRecyclerviewAdapter.setPreLoadNumber(0);
        //删除订单
        cancelledOrderRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_delete, null);
                DialogUtils.deleteDialog(getMContext(),inflate_items,true,true,mMvpPresenter,mMultipleStateView,cancelledOrderRecyclerviewAdapter.getData().get(i).getTask_id());
             }
        });
        //详情页面
        cancelledOrderRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("11")) {
                    //已取消
                    Intent intent = new Intent(getMContext(), TakeIngOrderInfoActivity.class);
                    intent.putExtra("taskId", cancelledOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 201);
                }else if (cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("12")){
                    //已取消
                    Intent intent = new Intent(getMContext(), BuyIngOrderInfoActivity.class);
                    intent.putExtra("taskId", cancelledOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 301);
                }else if (cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("13")){
                    //已取消
                    Intent intent = new Intent(getMContext(), SendIngOrderInfoActivity.class);
                    intent.putExtra("taskId", cancelledOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 401);
                }else if (cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("14")){
                    //已取消
                    Intent intent = new Intent(getMContext(), DeliverIngOrderInfoActivity.class);
                    intent.putExtra("taskId", cancelledOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 501);
                }else if (cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("15")){
                    //已取消
                    Intent intent = new Intent(getMContext(), UniversalIngOrderInfoActivity.class);
                    intent.putExtra("taskId", cancelledOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", cancelledOrderRecyclerviewAdapter.getData().get(i).getCate_id());
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
            }else if (event.getCode()==EventCode.EVENT_Z){
                //删除订单成功 刷新列表
                refreshData();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            refreshData();
        }
    }

    private void refreshData() {
        //刷新列表
        page=1;
        mMvpPresenter.getProgressIndent(spUserID, "6", mMultipleStateView);
    }
}
