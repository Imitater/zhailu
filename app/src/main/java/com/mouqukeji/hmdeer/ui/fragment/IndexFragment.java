package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.bean.IndexBean;
import com.mouqukeji.hmdeer.bean.PageBean;
import com.mouqukeji.hmdeer.contract.fragment.IndexContract;
import com.mouqukeji.hmdeer.modle.fragment.IndexModel;
import com.mouqukeji.hmdeer.presenter.fragment.IndexPresenter;
import com.mouqukeji.hmdeer.ui.activity.HelpBuyActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpDeliverActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpSendActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpTakeActivity;
import com.mouqukeji.hmdeer.ui.activity.HelpUniversalActivity;
import com.mouqukeji.hmdeer.ui.adapter.ActionRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.adapter.CategoryItemRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.callback.PageHelperListener;
import com.mouqukeji.hmdeer.ui.widget.BannerViewPager;
import com.mouqukeji.hmdeer.ui.widget.ZoomIndicator;
import com.mouqukeji.hmdeer.util.MzTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class IndexFragment extends BaseFragment<IndexPresenter, IndexModel> implements IndexContract.View, View.OnClickListener {


    @BindView(R.id.index_loop_viewpager)
    BannerViewPager indexLoopViewpager;
    @BindView(R.id.index_bottom_zoom_arc)
    ZoomIndicator indexBottomZoomArc;
    @BindView(R.id.index_button_list)
    RecyclerView indexButtonList;
    @BindView(R.id.index_left)
    LinearLayout indexLeft;
    @BindView(R.id.index_right)
    LinearLayout indexRight;
    @BindView(R.id.index_action_recyclerview)
    RecyclerView indexActionRecyclerview;
    Unbinder unbinder;
    private List<IndexBean.BannersBean> banners;
    private List<IndexBean.CategoriesBean> categories;
    private List<IndexBean.NoticesBean> notices;

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initViewAndEvents() {
        mMvpPresenter.getIndex(mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_index;
    }

    @Override
    protected void setUpView() {
    }

    private void setCategory() {

        //设置布局管理器
        indexButtonList.setLayoutManager(new GridLayoutManager(getContext(),5));
        indexButtonList.setNestedScrollingEnabled(false);
        //设置Adapter
        CategoryItemRecyclerviewAdapter buyItemsRecyclerviewAdapter = new CategoryItemRecyclerviewAdapter(R.layout.adapter_category_iv, categories);
        indexButtonList.setAdapter(buyItemsRecyclerviewAdapter);
        buyItemsRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(getMContext(), HelpTakeActivity.class);
                        intent.putExtra("cate_id", categories.get(0).getId());
                        getActivity().startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getMContext(), HelpBuyActivity.class);
                        intent1.putExtra("cate_id", categories.get(1).getId());
                        getActivity().startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getMContext(), HelpSendActivity.class);
                        intent2.putExtra("cate_id", categories.get(2).getId());
                        getActivity().startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getMContext(), HelpDeliverActivity.class);
                        intent3.putExtra("cate_id", categories.get(3).getId());
                        getActivity().startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4 = new Intent(getMContext(), HelpUniversalActivity.class);
                        intent4.putExtra("cate_id", categories.get(4).getId());
                        getActivity().startActivity(intent4);
                        break;
                }
            }
        });
    }

    private void setViewPager() {
        //设置列表数据
        ArrayList<String> img = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            img.add(banners.get(i).getBanner());
        }
        PageBean bean = new PageBean.Builder<String>()
                .setDataObjects(img)
                .setIndicator(indexBottomZoomArc)
                .builder();

        //轮播动画
        indexLoopViewpager.setPageTransformer(true, new MzTransformer());
        indexLoopViewpager.setOffscreenPageLimit(3);//轮播一屏幕显示3个item
        indexLoopViewpager.setPageMargin(30);//pager间距

        //viewpager监听
        indexLoopViewpager.setPageListener(bean, R.layout.loop_layout, new PageHelperListener() {
            @Override
            public void getItemView(View view, Object o) {
                ImageView lOopIcon = view.findViewById(R.id.loop_icon);
                lOopIcon.setBackgroundResource(R.mipmap.test);
                Glide.with(getMContext()).load(o.toString()).into(lOopIcon);
                lOopIcon.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        indexLoopViewpager.stopAnim();
    }

    @Override
    public void onResume() {
        super.onResume();
        indexLoopViewpager.startAnim();
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void getIndex(IndexBean bean) {
        banners = bean.getBanners();
        categories = bean.getCategories();
        notices = bean.getNotices();
        //设置轮播图
        setViewPager();
        //设置分类
        setCategory();
        //设置活动列表
        setAction();
    }

    //设置活动列表
    private void setAction() {
         indexActionRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),2));
        indexActionRecyclerview.setNestedScrollingEnabled(false);
        //设置Adapter
        ActionRecyclerviewAdapter actionRecyclerviewAdapter = new ActionRecyclerviewAdapter(R.layout.adapter_actiono_layout, notices);
        indexActionRecyclerview.setAdapter(actionRecyclerviewAdapter);
    }
}
