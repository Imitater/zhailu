package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.LatLngBounds;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.bumptech.glide.Glide;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseMapActivity;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.SendOrderInfoContract;
import com.mouqukeji.hmdeer.modle.activity.SendOrderInfoModel;
import com.mouqukeji.hmdeer.presenter.activity.SendOrderInfoPresenter;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.CenterDialogView;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SendOrderInfoActivity extends BaseMapActivity<SendOrderInfoPresenter, SendOrderInfoModel> implements SendOrderInfoContract.View, View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;
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
    @BindView(R.id.orderinfo_pay_type)
    TextView orderinfoPayType;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    @BindView(R.id.orderinfo_check)
    TextView orderinfoCheck;
    private String cateId;
    private String taskId;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            //刷新msg的内容
            map.invalidate();
            mMvpPresenter.locationDown(spUserID, server_lat + "", server_lng + "", server_id, mMultipleStateView);
            handler.postDelayed(this, 1000 * 120);// 间隔120秒
        }
    };
    private long sid;
    private String pay_type;
    private String order_id;
    private String spUserID;
    private String server_lat;
    private String server_lng;
    private String server_id;
    private boolean flag = true;
    private HelpSendInfoBean sendInfoBean;
    private List<Marker> items;
    private String picture;
    private String makeup_fee;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(SendOrderInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SendOrderInfoActivity.this, PayBuyCompleteActivity.class);
                        intent.putExtra("task_id", taskId);
                        intent.putExtra("cate_id", cate_id);
                        startActivityForResult(intent, 6);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(SendOrderInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String makeup_id;
    private String cate_id;
    private Marker userMarker;
    private List<Marker> allMarker;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        taskId = intent.getStringExtra("taskId");
        cateId = intent.getStringExtra("cateId");
        mMvpPresenter.getSendInfo(taskId, cateId, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_sendorder_info;
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
        actionBack.setOnClickListener(this);
        orderinfoBottomBt.setOnClickListener(this);
        orderinfoBottomTv.setOnClickListener(this);
        orderinfoCheck.setOnClickListener(this);
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
                DialogUtils.processDialog(SendOrderInfoActivity.this, inflate_procress, true, true, sendInfoBean);
                break;
            case R.id.orderinfo_bottom_tv:
                //进程显示框
                View inflate_procress1 = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(SendOrderInfoActivity.this, inflate_procress1, true, true, sendInfoBean);
                break;
            case R.id.action_back:
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                //关闭Activity
                finish();
                break;
            case R.id.orderinfo_check:
                //查看凭证
                View inflate_check = getLayoutInflater().inflate(R.layout.dialog_buy_check, null);
                final CenterDialogView centerDialogView = DialogUtils.checkDialog(SendOrderInfoActivity.this, SendOrderInfoActivity.this, inflate_check, true, true, picture);
                TextView dialogCheckRightBt = centerDialogView.findViewById(R.id.dialog_check_right_bt);
                ImageView dialogIv = centerDialogView.findViewById(R.id.dialog_iv);
                TextView dialogcheckMoney = centerDialogView.findViewById(R.id.dialog_check_money);
                TextView dialogCheckLeftBt = centerDialogView.findViewById(R.id.dialog_check_left_bt);

                Glide.with(SendOrderInfoActivity.this).load(picture).into(dialogIv);
                dialogcheckMoney.setText(makeup_fee);
                //返回菜单按键
                dialogCheckLeftBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerDialogView.dismiss();
                    }
                });
                //支付按钮点击
                dialogCheckRightBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerDialogView.dismiss();
                        mMvpPresenter.payYueInfo(spUserID, order_id, mMultipleStateView);//获取余额
                    }
                });
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
    public void onLocationChanged(AMapLocation aMapLocation) {

    }

    @Override
    public void getSendInfo(HelpSendInfoBean bean) {
        //显示用户位置
        MarkerOptions userOptions = new MarkerOptions();
        View markerView = LayoutInflater.from(this).inflate(R.layout.marker_user, map, false);
        userOptions.position(new LatLng(Double.parseDouble(bean.getDetail().getStart_lat()), Double.parseDouble(bean.getDetail().getStart_lng())));
        userOptions.icon(BitmapDescriptorFactory.fromView(markerView));
        userMarker = getAmap().addMarker(userOptions);


        sendInfoBean = bean;
        order_id = bean.getDetail().getOrder_id();
        orderinfoName.setText(bean.getDetail().getStart_name());
        orderinfoNumber.setText(bean.getDetail().getStart_telephone());
        orderinfoAddress.setText(bean.getDetail().getStart_address() + bean.getDetail().getStart_detail());
        orderinfoGetName.setText(bean.getDetail().getName());
        orderinfoGetNumber.setText(bean.getDetail().getTelephone());
        orderinfoGetAddress.setText(bean.getDetail().getAddress() + bean.getDetail().getDetail());
        orderinfoType.setText(bean.getDetail().getType_name());
        if (bean.getDetail().getExpress_pay_type().equals("1")) {
            orderinfoPayType.setText("快递到付");
        } else {
            orderinfoPayType.setText("寄付现结");
        }
        orderinfoServerMoney.setText(bean.getDetail().getTask_price() + "元");
        if (bean.getDetail().getCoupon().equals("0.00")) {
            orderinfoYouhui.setText("暂无优惠劵");
        } else {
            orderinfoYouhui.setText(bean.getDetail().getCoupon() + "元");
        }
        orderinfoCountMoney.setText(bean.getDetail().getPay_fee() + "元");
        orderinfoBeizhu.setText(bean.getDetail().getRemarks());
        orderinfoOrderCode.setText("订单号:" + bean.getDetail().getOrder_sn());
        orderinfoOrderCreattime.setText("创建时间:" + bean.getDetail().getCreate_time());
        orderinfoTime.setText(bean.getDetail().getDelivery_time());
        picture = bean.getDetail().getPicture();
        makeup_fee = bean.getDetail().getMakeup_fee();

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
            orderinfoCheck.setVisibility(View.VISIBLE);
        } else if (bean.getDetail().getProgress().equals("6")) {
            orderinfoBottomTv.setText("已取消");
        } else if (bean.getDetail().getProgress().equals("7")) {
            orderinfoBottomTv.setText("已退款");
        } else if (bean.getDetail().getProgress().equals("8")) {
            orderinfoBottomTv.setText("送货中");
            orderinfoRelativelayout2.setBackgroundResource(R.mipmap.mipmap_procress_press);
            orderinfoTake.setTextColor(getResources().getColor(R.color.blue));
            orderinfoCheck.setVisibility(View.GONE);
        } else {
            orderinfoBottomTv.setText("已完成");
            orderinfoRelativelayout2.setBackgroundResource(R.mipmap.mipmap_procress_press);
            orderinfoTake.setTextColor(getResources().getColor(R.color.blue));
            orderinfoRelativelayout3.setBackgroundResource(R.mipmap.mipmap_procress_press);
            orderinfoSend.setTextColor(getResources().getColor(R.color.blue));
            orderinfoCheck.setVisibility(View.VISIBLE);
        }
        makeup_id = bean.getDetail().getMakeup_id();
        cate_id = bean.getDetail().getCate_id();
        server_lat = bean.getDetail().getServer_lat();
        server_lng = bean.getDetail().getServer_lng();
        server_id = bean.getDetail().getServer_id();
        mMvpPresenter.locationDown(spUserID, server_lat + "", server_lng + "", server_id, mMultipleStateView);
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

        allMarker.add(marker);
        allMarker.add(userMarker);
        //一屏显示所有marker
        LatLngBounds.Builder boundsBuilder = new LatLngBounds.Builder();//存放所有点的经纬度
        for(int i=0;i<allMarker.size();i++){
            boundsBuilder.include(allMarker.get(i).getPosition());//把所有点都include进去（LatLng类型）
        }
        getAmap().animateCamera(CameraUpdateFactory.newLatLngBounds(boundsBuilder.build(), 15));//第二个参数为四周留空宽度
    }

    private void setPay(double balance) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(SendOrderInfoActivity.this, inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        //判断余额是否为0
        if (balance != 0 && balance > Double.parseDouble(makeup_fee)) {
            pay_type = "1";
            dialogPayYue.setChecked(true);
            dialogPayRecharge.setVisibility(View.GONE);//隐藏充值按钮
            dialogPayYue.setVisibility(View.VISIBLE);//显示余额支付选项
        } else {
            dialogPayRecharge.setVisibility(View.VISIBLE);//显示充值按钮
            dialogPayYue.setVisibility(View.GONE);//隐藏余额支付按钮
            pay_type = "2";
            dialogPayWeiXing.setChecked(true);
        }
        dialogPayWalletMoney.setText("可用余额" + balance + "元");
        //money保留2位小数
        //设置价钱
        payMoneyTv.setText(makeup_fee);
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payAgainWeixin(makeup_id, spUserID, "1", makeup_fee, mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payAgainZhiFuBao(makeup_id, spUserID, "2", makeup_fee, mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payAgainYue(makeup_id, spUserID, "3", makeup_fee, mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendOrderInfoActivity.this, ReChargeActivity.class);
                intent.putExtra("rechange_type", "1");//设置充值标记
                startActivity(intent);
            }
        });
    }


    //余额
    @Override
    public void payYueInfo(PayYueBean bean) {
        //显示付款
        setPay(bean.getBalance());
    }

    @Override
    public void payAgainWeixin(WeixingPayBean bean) {
        PaymentHelper.startWeChatPay(this, bean);//调取微信支付
    }

    @Override
    public void payAgainZhiFuBao(ZhiFuBoPayBean bean) {
        PaymentHelper.aliPay(this, bean.getPay().getPayInfo(), mHandler);//调取支付宝支付
    }

    @Override
    public void payAgainYue(YuEBean bean) {
        Intent intent = new Intent(SendOrderInfoActivity.this, PayBuyCompleteActivity.class);
        intent.putExtra("task_id", taskId);
        intent.putExtra("cate_id", cate_id);
        startActivityForResult(intent, 6);
    }
}
