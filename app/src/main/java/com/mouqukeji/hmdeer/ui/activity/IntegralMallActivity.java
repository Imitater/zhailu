package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.DrawIntegralBean;
import com.mouqukeji.hmdeer.bean.IntegralPageBean;
import com.mouqukeji.hmdeer.contract.activity.IntegralMallContract;
import com.mouqukeji.hmdeer.modle.activity.IntegralMallModel;
import com.mouqukeji.hmdeer.presenter.activity.ExperienceCardActivity;
import com.mouqukeji.hmdeer.presenter.activity.IntegralMallPresenter;
import com.mouqukeji.hmdeer.ui.adapter.IntegralMallRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.widget.ColorArcProgressBar;
import com.mouqukeji.hmdeer.util.GetSPData;
import butterknife.BindView;

public class IntegralMallActivity extends BaseActivity<IntegralMallPresenter, IntegralMallModel> implements IntegralMallContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_more)
    ImageView actionMore;
    @BindView(R.id.bar1)
    ColorArcProgressBar bar1;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.integralmall_bt)
    TextView integralmallBt;
    @BindView(R.id.integralmall_recyclerview)
    RecyclerView integralmallRecyclerview;
    @BindView(R.id.integralmall_score)
    TextView integralmallScore;
    private String spUserID;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.integralPage(spUserID, mMultipleStateView);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mMvpPresenter.integralPage(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_integralmall;
    }

    @Override
    protected void setUpView() {
        initListener();
    }

    private void initListener() {
        integralmallBt.setOnClickListener(this);
        actionMore.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_more:
                showPopupWindow(v);
                break;
            case R.id.integralmall_bt:
                //领取积分
                mMvpPresenter.drawIntegral(spUserID,mMultipleStateView);
                break;
        }
    }

    private void showPopupWindow(View view) {
        View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
        TextView popItem2 = (TextView) contentView.findViewById(R.id.pop_item2);
        popItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntegralMallActivity.this, JiFenListActivity.class);
                startActivity(intent);
            }
        });

        final PopupWindow popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.pop_background));
        popupWindow.showAsDropDown(view);
    }

    @Override
    public void integralPage(IntegralPageBean bean) {
        bar1.setCurrentValues(bean.getTotal());
        if (bean.getUnclaimed()==null){
            integralmallScore.setText("0分");
            integralmallBt.setBackgroundResource(R.drawable.gray_circle_shape);
            integralmallBt.setEnabled(false);
        }else{
            integralmallScore.setText(bean.getUnclaimed()+"分");
            integralmallBt.setBackgroundResource(R.drawable.blue_circle_shape);
            integralmallBt.setEnabled(true);
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        integralmallRecyclerview.setLayoutManager(gridLayoutManager);
        final IntegralMallRecyclerviewAdapter integrallMallRecyclerAdapter = new IntegralMallRecyclerviewAdapter(R.layout.adapter_integrallmall_layout, bean.getGoods());
        integralmallRecyclerview.setAdapter(integrallMallRecyclerAdapter);
        integralmallRecyclerview.setNestedScrollingEnabled(false);//禁止滑动

        integrallMallRecyclerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //item点击事件 类型判断
                if (integrallMallRecyclerAdapter.getData().get(i).getType().equals("1")){
                    //优惠券
                    Intent intent = new Intent(IntegralMallActivity.this, DiscountActivity.class);
                    intent.putExtra("good_id",integrallMallRecyclerAdapter.getData().get(i).getGoods_id());
                    startActivity(intent);
                }else if (integrallMallRecyclerAdapter.getData().get(i).getType().equals("2")){
                    //体验卡
                    Intent intent = new Intent(IntegralMallActivity.this, ExperienceCardActivity.class);
                    intent.putExtra("good_id",integrallMallRecyclerAdapter.getData().get(i).getGoods_id());
                    startActivity(intent);
                }else {
                    //商品

                }
            }
        });
    }

    @Override
    public void drawIntegral(DrawIntegralBean bean) {
        Toast.makeText(this,"积分领取成功",Toast.LENGTH_SHORT).show();
        mMvpPresenter.integralPage(spUserID, mMultipleStateView);
    }


}
