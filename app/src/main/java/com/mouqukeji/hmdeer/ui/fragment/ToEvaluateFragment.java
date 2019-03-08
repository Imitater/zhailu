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
import com.mouqukeji.hmdeer.contract.fragment.ToEvaluateContract;
import com.mouqukeji.hmdeer.modle.fragment.ToEvaluateModel;
import com.mouqukeji.hmdeer.presenter.fragment.ToEvaluatetPresenter;
import com.mouqukeji.hmdeer.ui.activity.BuyIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.EvaluationActivity;
import com.mouqukeji.hmdeer.ui.activity.SendIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.adapter.ToEvaluateRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;

public class ToEvaluateFragment extends BaseLazyFragment<ToEvaluatetPresenter, ToEvaluateModel> implements ToEvaluateContract.View, View.OnClickListener {
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    Unbinder unbinder;
    @BindView(R.id.all_order_swiperefreshlayout)
    SwipeRefreshLayout allOrderSwiperefreshlayout;
    private int page = 1;
    private String spUserID;
    private ToEvaluateRecyclerviewAdapter toEvaluateRecyclerviewAdapter;
    private int countPages;
    private List<AllOrderBean.TasksBean> tasks;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getProgressIndent(spUserID, "4", mMultipleStateView);
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
        page = 1;
        mMvpPresenter.getProgressIndent(spUserID, "4", mMultipleStateView);
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
                            mMvpPresenter.getProgressIndent(spUserID, "4", mMultipleStateView);
                            if (toEvaluateRecyclerviewAdapter != null) {
                                toEvaluateRecyclerviewAdapter.notifyDataSetChanged();
                                toEvaluateRecyclerviewAdapter.setUpFetching(false);
                                toEvaluateRecyclerviewAdapter.setUpFetchEnable(false);
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
        toEvaluateRecyclerviewAdapter.addData(bean.getTasks());
        toEvaluateRecyclerviewAdapter.loadMoreComplete();
    }

    private void setUpLoad() {
        toEvaluateRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        toEvaluateRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (tasks.size() >= 10) {
            toEvaluateRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
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
                    toEvaluateRecyclerviewAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.getIndentNext(spUserID, "4", page + "", mMultipleStateView);
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

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置 多布局type
        //设置Adapter
        toEvaluateRecyclerviewAdapter = new ToEvaluateRecyclerviewAdapter(R.layout.adapter_to_evaluate_layout, tasks);
        orderRecycler.setAdapter(toEvaluateRecyclerviewAdapter);
        toEvaluateRecyclerviewAdapter.disableLoadMoreIfNotFullPage(orderRecycler);
        toEvaluateRecyclerviewAdapter.setPreLoadNumber(0);
        //去评价
        toEvaluateRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                Intent intent = new Intent(getMContext(), EvaluationActivity.class);
                intent.putExtra("order_id", toEvaluateRecyclerviewAdapter.getData().get(i).getOrder_id());
                intent.putExtra("cate_id", toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id());
                intent.putExtra("server_id", toEvaluateRecyclerviewAdapter.getData().get(i).getServer_id());
                startActivityForResult(intent,701);
            }
        });
        //详情页面
        toEvaluateRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id().equals("11")) {
                    //待评价
                    Intent intent = new Intent(getMContext(), TakeIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toEvaluateRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 201);
                } else if (toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id().equals("12")) {
                    //待评价
                    Intent intent = new Intent(getMContext(), BuyIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toEvaluateRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 301);
                } else if (toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id().equals("13")) {
                    //待评价
                    Intent intent = new Intent(getMContext(), SendIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toEvaluateRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 401);
                } else if (toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id().equals("14")) {
                    //待评价
                    Intent intent = new Intent(getMContext(), DeliverIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toEvaluateRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 501);
                } else if (toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id().equals("15")) {
                    //待评价
                    Intent intent = new Intent(getMContext(), UniversalIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toEvaluateRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toEvaluateRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 601);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //刷新列表
            page = 1;
            mMvpPresenter.getProgressIndent(spUserID, "4", mMultipleStateView);
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
            if (event.getCode() == EventCode.EVENT_K) {
                //评价成功 刷新列表
                page = 1;
                mMvpPresenter.getProgressIndent(spUserID, "4", mMultipleStateView);
            }
        }
    }
}
