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
import com.mouqukeji.hmdeer.contract.fragment.AllOrderContract;
import com.mouqukeji.hmdeer.modle.fragment.AllOrderModel;
import com.mouqukeji.hmdeer.presenter.fragment.AllOrderPresenter;
import com.mouqukeji.hmdeer.ui.adapter.AllorderRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.MultipleItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class AllOrderFragment extends BaseFragment<AllOrderPresenter, AllOrderModel> implements AllOrderContract.View, View.OnClickListener {
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    Unbinder unbinder;
    @BindView(R.id.all_order_swiperefreshlayout)
    SwipeRefreshLayout allOrderSwiperefreshlayout;
    private int page = 1;
    boolean flag = true;
    private AllorderRecyclerviewAdapter allorderRecyclerviewAdapter;
    private String spUserID;
    private int pageCount;
    private List<AllOrderBean.TasksBean> tasks;


    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getIndent(spUserID, mMultipleStateView);

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
    public void getIndent(AllOrderBean bean) {
        tasks = bean.getTasks();
        pageCount = bean.getPages();
        //初始加载
        if (flag) {
            flag = false;
            //设置recyclerview
            setRecyclerview(tasks);
        }
        //设置上拉加载
        setUpLoad(tasks);
        //设置下拉刷新
        initSwipeRefresh();
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
                            mMvpPresenter.getIndent(spUserID, mMultipleStateView);
                            if (allorderRecyclerviewAdapter != null) {
                                allorderRecyclerviewAdapter.notifyDataSetChanged();
                                allorderRecyclerviewAdapter.setUpFetching(false);
                                allorderRecyclerviewAdapter.setUpFetchEnable(false);
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
    public void getIndentNext(AllOrderBean bean) {
        tasks = bean.getTasks();
    }

    private void setUpLoad(List<AllOrderBean.TasksBean> tasks) {
        allorderRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        allorderRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (tasks.size() >= 10) {
            onLoadMore();
        }
    }

    private void onLoadMore() {
        allorderRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                orderRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (page > pageCount) {
                            //数据全部加载完毕
                            allorderRecyclerviewAdapter.loadMoreEnd();
                        } else {
                            mMvpPresenter.getIndentNext(spUserID, "0", page + "", mMultipleStateView);
                            page++;
                            List list = new ArrayList<>();
                            for (int i = 0; i < tasks.size(); i++) {
                                MultipleItem multipleItem = new MultipleItem(Integer.parseInt(tasks.get(i).getProgress()), tasks.get(i));
                                list.add(multipleItem);
                            }
                            //成功获取更多数据
                            allorderRecyclerviewAdapter.addData(list);
                            allorderRecyclerviewAdapter.loadMoreComplete();
                        }
                    }

                }, 1500);
            }
        }, orderRecycler);
    }

    @Override
    public void onStart() {
        super.onStart();
        flag=true;
    }

    @Override
    public void getEmpty() {
        //暂无订单
        llNoOrder.setVisibility(View.VISIBLE);
        orderRecycler.setVisibility(View.GONE);
        initSwipeRefresh();
    }

    //设置recyclerview
    private void setRecyclerview(List<AllOrderBean.TasksBean> countBean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置 多布局type
        List list = new ArrayList<>();
        for (int i = 0; i < countBean.size(); i++) {
            MultipleItem multipleItem = new MultipleItem(Integer.parseInt(countBean.get(i).getProgress()), countBean.get(i));
            list.add(multipleItem);
        }
        //设置Adapter
        allorderRecyclerviewAdapter = new AllorderRecyclerviewAdapter(getMContext(), list);
        orderRecycler.setAdapter(allorderRecyclerviewAdapter);
        allorderRecyclerviewAdapter.disableLoadMoreIfNotFullPage(orderRecycler);
    }
}
