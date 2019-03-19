package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseMapActivity;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.contract.activity.DeliverOrderInfoContract;
import com.mouqukeji.hmdeer.modle.activity.DeliverOrderInfoModel;
import com.mouqukeji.hmdeer.presenter.activity.DeliverOrderInfoPresenter;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeliverOrderInfoActivity extends BaseMapActivity<DeliverOrderInfoPresenter, DeliverOrderInfoModel> implements DeliverOrderInfoContract.View, View.OnClickListener {
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
    @BindView(R.id.map)
    MapView map;
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
    @BindView(R.id.orderinfo_get_name)
    TextView orderinfoGetName;
    @BindView(R.id.orderinfo_get_number)
    TextView orderinfoGetNumber;
    @BindView(R.id.orderinfo_get_address)
    TextView orderinfoGetAddress;
    @BindView(R.id.orderinfo_type)
    TextView orderinfoType;
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
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    private String taskId;
    private String cateId;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            //刷新msg的内容
            if (map != null)
                map.invalidate();
            mMvpPresenter.locationDown(spUserID, server_lat + "", server_lng + "", server_id, mMultipleStateView);
            handler.postDelayed(this, 1000 * 120);
        }
    };
    private boolean flag = true;
    private String order_id;
    private String spUserID;
    private String server_id;
    private String server_lng;
    private String server_lat;
    private HelpDeliverInfoBean infoBean;
    private List<Marker> items;
    private Marker getMarker;
    private Marker putMarker;
    private List<Marker> allMarker;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        taskId = intent.getStringExtra("taskId");
        cateId = intent.getStringExtra("cateId");
        mMvpPresenter.getDeliverInfo(taskId, cateId, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_deliverorder_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
        orderinfoRelativelayout3 = findViewById(R.id.orderinfo_relativelayout3);
        //设置applayout监听
        initAppLayoutListener();
        //设置按键监听
        initListener();

    }


    private void initListener() {
        orderinfoBottomBt.setOnClickListener(this);
        actionBack.setOnClickListener(this);
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
                DialogUtils.processDialog(DeliverOrderInfoActivity.this, inflate_procress, true, true, infoBean);
                break;
            case R.id.action_back:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        //关闭Activity
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {

    }


    @Override
    public void getDeliverInfo(HelpDeliverInfoBean bean) {

        //取位置
        MarkerOptions putOptions = new MarkerOptions();
        View markerGetView = LayoutInflater.from(this).inflate(R.layout.marker_user, map, false);
        putOptions.position(new LatLng(Double.parseDouble(bean.getDetail().getStart_lat()), Double.parseDouble(bean.getDetail().getStart_lng())));
        putOptions.icon(BitmapDescriptorFactory.fromView(markerGetView));
        getMarker = getAmap().addMarker(putOptions);

        //送位置
        MarkerOptions getOptions = new MarkerOptions();
        View markerPutView = LayoutInflater.from(this).inflate(R.layout.marker_end, map, false);
        getOptions.position(new LatLng(Double.parseDouble(bean.getDetail().getEnd_lat()), Double.parseDouble(bean.getDetail().getEnd_lng())));
        getOptions.icon(BitmapDescriptorFactory.fromView(markerPutView));
        putMarker = getAmap().addMarker(getOptions);

        infoBean = bean;
        order_id = bean.getDetail().getOrder_id();
        orderinfoName.setText(bean.getDetail().getStart_name());
        orderinfoNumber.setText(bean.getDetail().getStart_telephone());
        orderinfoAddress.setText(bean.getDetail().getStart_address() + bean.getDetail().getStart_detail());

        orderinfoGetName.setText(bean.getDetail().getEnd_name());
        orderinfoGetNumber.setText(bean.getDetail().getEnd_telephone());
        orderinfoGetAddress.setText(bean.getDetail().getEnd_address() + bean.getDetail().getEnd_detail());

        orderinfoType.setText(bean.getDetail().getType_name());
        orderinfoTime.setText(bean.getDetail().getDelivery_time());
        orderinfoServerMoney.setText(bean.getDetail().getTask_price() + "元");
        if (bean.getDetail().getCoupon().equals("0.00")) {
            orderinfoYouhui.setText("暂无优惠劵");
        } else {
            orderinfoYouhui.setText(bean.getDetail().getCoupon() + "元");
        }
        orderinfoCountMoney.setText(bean.getDetail().getPay_fee() + "元");
        orderinfoOrderCreattime.setText("创建时间:" + bean.getDetail().getCreate_time());
        orderinfoOrderCode.setText("订单号:" + bean.getDetail().getOrder_sn());
        orderinfoBeizhu.setText(bean.getDetail().getRemarks());

        //进度
        if (bean.getDetail().getProgress().equals("1")) {
            orderinfoBottomTv.setText("待付款");
        } else if (bean.getDetail().getProgress().equals("2")) {
            orderinfoBottomTv.setText("待接单");
        } else if (bean.getDetail().getProgress().equals("3")) {
            orderinfoBottomTv.setText("已接单");
        } else if (bean.getDetail().getProgress().equals("5")) {
            orderinfoBottomTv.setText("待评价");
        } else if (bean.getDetail().getProgress().equals("4")) {
            orderinfoBottomTv.setText("已完成");
            orderinfoRelativelayout3.setBackgroundResource(R.mipmap.mipmap_procress_press);
            orderinfoSend.setTextColor(getResources().getColor(R.color.blue));
        } else if (bean.getDetail().getProgress().equals("6")) {
            orderinfoBottomTv.setText("已取消");
        } else if (bean.getDetail().getProgress().equals("7")) {
            orderinfoBottomTv.setText("已退款");
        } else if (bean.getDetail().getProgress().equals("8")) {
            orderinfoBottomTv.setText("送货中");
            orderinfoRelativelayout2.setBackgroundResource(R.mipmap.mipmap_procress_press);
            orderinfoTake.setTextColor(getResources().getColor(R.color.blue));
        } else {
            orderinfoBottomTv.setText("已完成");
            orderinfoRelativelayout2.setBackgroundResource(R.mipmap.mipmap_procress_press);
            orderinfoTake.setTextColor(getResources().getColor(R.color.blue));
            orderinfoRelativelayout3.setBackgroundResource(R.mipmap.mipmap_procress_press);
            orderinfoSend.setTextColor(getResources().getColor(R.color.blue));
        }

        server_lat = bean.getDetail().getServer_lat();
        server_lng = bean.getDetail().getServer_lng();
        server_id = bean.getDetail().getServer_id();
        mMvpPresenter.locationDown(spUserID, server_lat + "", server_lng + "", server_id, mMultipleStateView);

    }

    @Override
    public void locationDown(LocationDownBean bean) {
        if (flag) {
            flag = false;
            handler.postDelayed(runnable, 1000);
        } else {
            handler.postDelayed(runnable, 1000 * 120);
        }
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                items.get(i).remove();
            }
        }
        allMarker = new ArrayList<>();
        items = new ArrayList<>();
        //显示 配送员位置
        MarkerOptions markerOption = new MarkerOptions();
        View markerView = LayoutInflater.from(this).inflate(R.layout.marker_runningman, map, false);
        markerOption.position(new LatLng(Double.parseDouble(bean.getServer_lat()), Double.parseDouble(bean.getServer_lng())));
        markerOption.icon(BitmapDescriptorFactory.fromView(markerView));
        Marker marker = getAmap().addMarker(markerOption);
        items.add(marker);

        allMarker.add(getMarker);
        allMarker.add(putMarker);
        allMarker.add(marker);
        //一屏显示所有marker
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        for(int i=0;i<allMarker.size();i++){
            boundsBuilder.include(allMarker.get(i).getPosition());//把所有点都include进去（LatLng类型）
        }
        getAmap().animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 15));//第二个参数为四周留空宽度
    }


}
