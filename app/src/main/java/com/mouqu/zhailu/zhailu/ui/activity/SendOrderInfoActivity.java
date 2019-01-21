package com.mouqu.zhailu.zhailu.ui.activity;

import android.graphics.Color;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.amap.api.maps2d.MapView;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseMapActivity;
import com.mouqu.zhailu.zhailu.contract.activity.SendOrderInfoContract;
import com.mouqu.zhailu.zhailu.modle.activity.SendOrderInfoModel;
import com.mouqu.zhailu.zhailu.presenter.activity.SendOrderInfoPresenter;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.DialogUtils;


import butterknife.BindView;

public class SendOrderInfoActivity extends BaseMapActivity<SendOrderInfoPresenter, SendOrderInfoModel> implements SendOrderInfoContract.View, View.OnClickListener {
    @BindView(R.id.orderinfo_actionbar)
    MyActionBar orderinfoActionbar;
    @BindView(R.id.orderinfo_bottom_tv)
    TextView orderinfoBottomTv;
    @BindView(R.id.orderinfo_bottom_bt)
    Button orderinfoBottomBt;
    @BindView(R.id.orderinfo_bottom)
    LinearLayout orderinfoBottom;
    @BindView(R.id.orderinfo_relativelayout1)
    RelativeLayout orderinfoRelativelayout1;
    @BindView(R.id.orderinfo_relativelayout2)
    RelativeLayout orderinfoRelativelayout2;
    @BindView(R.id.orderinfo_relativelayout3)
    RelativeLayout orderinfoRelativelayout3;
    @BindView(R.id.orderinfo_pay)
    TextView orderinfoPay;
    @BindView(R.id.orderinfo_take)
    TextView orderinfoTake;
    @BindView(R.id.orderinfo_send)
    TextView orderinfoSend;
    @BindView(R.id.orderinfo_top)
    LinearLayout orderinfoTop;
    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.orderinfo_name)
    TextView orderinfoName;
    @BindView(R.id.orderinfo_number)
    TextView orderinfoNumber;
    @BindView(R.id.orderinfo_address)
    TextView orderinfoAddress;
    @BindView(R.id.orderinfo_code)
    TextView orderinfoCode;
    @BindView(R.id.orderinfo_sex)
    TextView orderinfoSex;
    @BindView(R.id.orderinfo_time)
    TextView orderinfoTime;
    @BindView(R.id.orderinfo_server_money)
    TextView orderinfoServerMoney;
    @BindView(R.id.orderinfo_youhui)
    TextView orderinfoYouhui;
    @BindView(R.id.orderinfo_count_money)
    TextView orderinfoCountMoney;
    @BindView(R.id.orderinfo_beizhu)
    TextView orderinfoBeizhu;
    @BindView(R.id.orderinfo_order_code)
    TextView orderinfoOrderCode;
    @BindView(R.id.orderinfo_order_creattime)
    TextView orderinfoOrderCreattime;
    @BindView(R.id.test_nestedscrollview)
    NestedScrollView testNestedscrollview;
    @BindView(R.id.map)
    MapView map;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sendorder_info;
    }

    @Override
    protected void setUpView() {
        orderinfoActionbar.setTitle("订单详情");
        orderinfoRelativelayout3 = findViewById(R.id.orderinfo_relativelayout3);
        //设置applayout监听
        initAppLayoutListener();
        //设置按键监听
        initListener();

    }



    private void initListener() {
        orderinfoBottomBt.setOnClickListener(this);
    }

    private void initAppLayoutListener() {
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            int alpha = 0;
            float scale = 0;

            @Override
            //verticalOffset是当前appbarLayout的高度与最开始appbarlayout高度的差，向上滑动的话是负数
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (!(appBarLayout.getHeight() + verticalOffset == 0)) {
                    orderinfoTop.setVisibility(View.VISIBLE);
                    scale = (float) -verticalOffset / appBarLayout.getHeight();
                    alpha = (int) (255 * scale);
                    // 随着滑动距离改变透明度
                    orderinfoTop.getBackground().mutate().setAlpha(255 - alpha);

                    orderinfoPay.setTextColor(Color.argb(255 - alpha, 0, 0, 255));
                    orderinfoTake.setTextColor(Color.argb(255 - alpha, 0, 0, 255));
                    orderinfoSend.setTextColor(Color.argb(255 - alpha, 0, 0, 255));

                    orderinfoRelativelayout1.getBackground().mutate().setAlpha(255 - alpha);
                    orderinfoRelativelayout2.getBackground().mutate().setAlpha(255 - alpha);
                    orderinfoRelativelayout3.getBackground().mutate().setAlpha(255 - alpha);


                    orderinfoBottom.getBackground().mutate().setAlpha(alpha);
                    orderinfoBottomTv.setTextColor(Color.argb(alpha, 255, 255, 255));
                    orderinfoBottomBt.getBackground().mutate().setAlpha(alpha);
                } else {
                    orderinfoBottom.getBackground().mutate().setAlpha(255);
                    orderinfoBottomTv.setTextColor(Color.argb(255, 255, 255, 255));
                    orderinfoBottomBt.getBackground().mutate().setAlpha(255);
                    orderinfoBottom.setVisibility(View.VISIBLE);
                    orderinfoTop.setVisibility(View.GONE);
                }

                if (verticalOffset == 0) {
                    orderinfoBottom.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.orderinfo_bottom_bt:
                //进程显示框
                View inflate_procress = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(SendOrderInfoActivity.this, inflate_procress, true, true);
                break;
        }
    }


}
