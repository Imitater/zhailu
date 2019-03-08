package com.mouqukeji.hmdeer.ui.fragment;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.base.BaseLazyFragment;
import com.mouqukeji.hmdeer.contract.fragment.IndentContract;
import com.mouqukeji.hmdeer.modle.fragment.IndentModel;
import com.mouqukeji.hmdeer.presenter.fragment.IndentPresenter;
import com.mouqukeji.hmdeer.ui.adapter.OrderAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;


public class IndentFragment extends BaseLazyFragment<IndentPresenter, IndentModel> implements IndentContract.View, View.OnClickListener {
    @BindView(R.id.indent_tablayout)
    TabLayout indentTablayout;
    @BindView(R.id.indent_viewpager)
    ViewPager indentViewpager;
    Unbinder unbinder;
    private ArrayList<String> titleList =new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    private String responseString="6";
    private OrderAdapter orderAdapter;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_indent;
    }

    @Override
    protected void setUpView() {
        initAdapter();
        setViewpager();
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
        //刷新列表页
        orderAdapter.notifyDataSetChanged();
    }

    //初始化Viewpager和TabLayout的tab标签和fragment
    public void initAdapter(){
        titleList.add("全部订单");
        titleList.add("待支付");
        titleList.add("待接单");
        titleList.add("进行中");
        titleList.add("已完成");
        titleList.add("待评价");
        titleList.add("已取消");

        fragmentList.add(new AllOrderFragment());
        fragmentList.add(new ToPayFragment());
        fragmentList.add(new WaitListFragment());
        fragmentList.add(new OngoingOrderFragment());
        fragmentList.add(new CompleteOrderFragment());
        fragmentList.add(new ToEvaluateFragment());
        fragmentList.add(new CancelledOrderFragment());
    }

    //Viewpager的初始化和相关加载
    private void setViewpager(){
        orderAdapter = new OrderAdapter(getActivity().getSupportFragmentManager(), titleList,fragmentList);
        indentViewpager.setAdapter(orderAdapter);
        indentTablayout.setupWithViewPager(indentViewpager);
        indentViewpager.setOffscreenPageLimit(1);//ViewPager设置预加载页面的个数方法
    }
    @Override
    public void onClick(View v) {

    }


}
