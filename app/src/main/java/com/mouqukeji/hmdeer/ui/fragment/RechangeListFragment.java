package com.mouqukeji.hmdeer.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.contract.fragment.RechangeListContract;
import com.mouqukeji.hmdeer.modle.fragment.RechangeListModel;
import com.mouqukeji.hmdeer.presenter.fragment.RechangeListPresenter;
import com.mouqukeji.hmdeer.ui.adapter.RechangeListRecyclerviewAdapter;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

public class RechangeListFragment extends BaseFragment<RechangeListPresenter, RechangeListModel> implements RechangeListContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.rechange_list_recyclerview)
    RecyclerView rechangeListRecyclerview;
    @BindView(R.id.rechange_list_swiperefreshalayout)
    SwipeRefreshLayout rechangeListSwiperefreshalayout;
    private int page = 1;
    private RechangeListRecyclerviewAdapter rechangeListRecyclerviewAdapter;
    private String spUserID;
      private int pageCount;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(getActivity());
        page = 1;
        mMvpPresenter.getRechangeList(spUserID, page + "", mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_rechangelist;
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

    private void setUpLoad(RechangeListBean bean) {
        rechangeListRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        rechangeListRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (bean.getBalance().getRecharges().size() >= 10) {
            onLoadMore();
        }
    }

    private void onLoadMore() {
        rechangeListRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                rechangeListRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (page > pageCount) {
                            //数据全部加载完毕
                            rechangeListRecyclerviewAdapter.loadMoreEnd();
                        } else {
                            mMvpPresenter.getRechangeListNext(spUserID, page + "", mMultipleStateView);
                        }
                    }

                }, 1500);
            }
        }, rechangeListRecyclerview);
    }

    @Override
    public void getRechangeList(RechangeListBean bean) {
        page++;
        pageCount = bean.getBalance().getPages();
        if (rechangeListRecyclerview != null) {
             //设置recyclerview
            setRecyclerview(bean);
        }
        //设置上拉加载
        setUpLoad(bean);
        //设置下拉刷新
        initSwipeRefresh();
    }

    private void setBack() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment rechange_list = fragmentManager.findFragmentByTag("rechange_list");
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(rechange_list);
        fragmentTransaction.commit();
    }

    @Override
    public void getRechangeListNext(RechangeListBean bean) {
        page++;
        //成功获取更多数据
        rechangeListRecyclerviewAdapter.addData(bean.getBalance().getRecharges());
        rechangeListRecyclerviewAdapter.loadMoreComplete();
    }

    private void initSwipeRefresh() {
        //设置下拉刷新
        rechangeListSwiperefreshalayout.setColorSchemeResources(R.color.blue);
        rechangeListSwiperefreshalayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rechangeListRecyclerview.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        mMvpPresenter.getRechangeList(spUserID, page + "", mMultipleStateView);
                        if (rechangeListRecyclerviewAdapter != null) {
                            rechangeListRecyclerviewAdapter.notifyDataSetChanged();
                            rechangeListRecyclerviewAdapter.setUpFetching(false);
                            rechangeListRecyclerviewAdapter.setUpFetchEnable(false);
                            rechangeListSwiperefreshalayout.setRefreshing(false);
                        }
                        if (rechangeListSwiperefreshalayout != null)
                            rechangeListSwiperefreshalayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void isEmpty() {
        //暂无订单
        rechangeListRecyclerview.setVisibility(View.GONE);
        initSwipeRefresh();
    }

    private void setRecyclerview(RechangeListBean bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        rechangeListRecyclerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);

        //设置Adapter
        rechangeListRecyclerviewAdapter = new RechangeListRecyclerviewAdapter(R.layout.rechange_list_item, bean.getBalance().getRecharges());
        rechangeListRecyclerview.setAdapter(rechangeListRecyclerviewAdapter);
        rechangeListRecyclerviewAdapter.disableLoadMoreIfNotFullPage(rechangeListRecyclerview);
        rechangeListRecyclerviewAdapter.addHeaderView(View.inflate(getActivity(), R.layout.rechangelist_item, null));
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
