package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.BuyIngOrderInfoContract;
import com.mouqukeji.hmdeer.modle.activity.BuyIngOrderInfoModel;
import com.mouqukeji.hmdeer.presenter.activity.BuyIngOrderInfoPresenter;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class BuyIngOrderInfoActivity extends BaseActivity<BuyIngOrderInfoPresenter, BuyIngOrderInfoModel> implements BuyIngOrderInfoContract.View, View.OnClickListener {
    @BindView(R.id.buyorder_next)
    ImageView buyorderNext;
    @BindView(R.id.helpbuy_info_name)
    TextView helpbuyInfoName;
    @BindView(R.id.helpbuy_info_number)
    TextView helpbuyInfoNumber;
    @BindView(R.id.helpbuy_info_user_address)
    TextView helpbuyInfoUserAddress;
    @BindView(R.id.helpbuy_info_buy_info)
    TextView helpbuyInfoBuyInfo;
    @BindView(R.id.helpbuy_info_goods)
    TextView helpbuyInfoGoods;
    @BindView(R.id.helpbuy_info_goods_money)
    TextView helpbuyInfoGoodsMoney;
    @BindView(R.id.buyorder_check)
    TextView buyorderCheck;
    @BindView(R.id.helpbuy_info_buy_time)
    TextView helpbuyInfoBuyTime;
    @BindView(R.id.helpbuy_info_server_money)
    TextView helpbuyInfoServerMoney;
    @BindView(R.id.helpbuy_info_preferntial)
    TextView helpbuyInfoPreferntial;
    @BindView(R.id.helpbuy_info_count)
    TextView helpbuyInfoCount;
    @BindView(R.id.helpbuy_info_beizhu)
    TextView helpbuyInfoBeizhu;
    @BindView(R.id.helpbuy_info_code)
    TextView helpbuyInfoCode;
    @BindView(R.id.helpbuy_info_cearte_time)
    TextView helpbuyInfoCearteTime;
    @BindView(R.id.buyorder_pay_bt)
    TextView buyorderPayBt;
    @BindView(R.id.buyorder_framelayout)
    FrameLayout buyorderFramelayout;
    @BindView(R.id.buyorder_type)
    TextView buyorderType;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    private String spUserID;
    private String cateId;
    private String order_id;
    private String taskId;
    private String progress;
    private HelpBuyInfoBean helpBuyInfoBean;
    public static BuyIngOrderInfoActivity instance = null;
    private static final int SDK_PAY_FLAG = 1;
    private String pay_type = "1";
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
                        Toast.makeText(BuyIngOrderInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(BuyIngOrderInfoActivity.this, PayInfoCompleteActivity.class);
                        intent.putExtra("task_id", taskId);
                        intent.putExtra("cate_id", cate_id);
                        startActivityForResult(intent, 2);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(BuyIngOrderInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String cate_id;
    private String server_id;

    @Override
    protected void initViewAndEvents() {
        //设置标记
        instance = this;
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        taskId = intent.getStringExtra("taskId");
        cateId = intent.getStringExtra("cateId");
        mMvpPresenter.helpBuyInfo(taskId, cateId, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_buyingorder_info;
    }

    @Override
    protected void setUpView() {
        //设置title
        actionTitle.setText("订单详情");
        //设置点击事件监听
        initListener();
    }

    private void initListener() {
        buyorderPayBt.setOnClickListener(this);
        buyorderNext.setOnClickListener(this);
        buyorderType.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buyorder_pay_bt://返回订单列表
                if (progress.equals("5") || progress.equals("6") || progress.equals("7")) {
                    finish();
                } else if (progress.equals("1")) {
                    //待支付
                    mMvpPresenter.payYueInfo(spUserID, order_id, mMultipleStateView);//获取余额
                } else if (progress.equals("2")) {
                    //取消订单
                    mMvpPresenter.cancelOrder(taskId, spUserID, mMultipleStateView);
                } else if (progress.equals("4")) {
                    Intent intent = new Intent(BuyIngOrderInfoActivity.this, EvaluationActivity.class);
                    intent.putExtra("order_id", order_id);
                    intent.putExtra("cate_id", cate_id);
                    intent.putExtra("server_id", server_id);
                    startActivity(intent);
                }
                break;
            case R.id.buyorder_next://进程框
                View inflate_procress = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(BuyIngOrderInfoActivity.this, inflate_procress, true, true, helpBuyInfoBean);
                break;
            case R.id.buyorder_type:
                View inflate_procress1 = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(BuyIngOrderInfoActivity.this, inflate_procress1, true, true, helpBuyInfoBean);
                break;
            case R.id.action_back:
                finish();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            mMvpPresenter.helpBuyInfo(taskId, cateId, mMultipleStateView);
        }
    }


    @Override
    public void payYueInfo(PayYueBean bean) {
        //付款框
        setPay(bean.getPay_fee(), bean.getBalance(), order_id);
    }



    //微信支付
    @Override
    public void payWeiXing(final WeixingPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.startWeChatPay(this, bean);//调取微信支付
    }

    //支付宝支付
    @Override
    public void payZhifubao(ZhiFuBoPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.aliPay(this, bean.getPay().getPayInfo(), mHandler);//调取支付宝支付
    }

    //余额支付
    @Override
    public void payYue(YuEBean bean) {
        taskId = bean.getOrders().getTask_id();
        cate_id = bean.getOrders().getCate_id();
        Intent intent = new Intent(this, PayInfoCompleteActivity.class);
        intent.putExtra("task_id", taskId);
        intent.putExtra("cate_id", cate_id);
        startActivityForResult(intent, 2);
    }


    private void setPay(final String pay_fee, final double balance, final String order_id) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(this, inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        //判断余额是否为0
        if (balance != 0 && balance > Double.parseDouble(pay_fee)) {
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
        final DecimalFormat df = new DecimalFormat("#.00");
        //设置价钱
        payMoneyTv.setText(pay_fee);
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payWeiXing(order_id, spUserID, "1", pay_fee, mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payZhifubao(order_id, spUserID, "2", pay_fee, mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payYue(order_id, spUserID, "3", pay_fee, mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyIngOrderInfoActivity.this, ReChargeActivity.class);
                intent.putExtra("rechange_type", "1");//设置充值标记
                startActivity(intent);
            }
        });
    }






    @Override
    public void helpBuyInfo(HelpBuyInfoBean buyInfoBean) {
        helpBuyInfoBean = buyInfoBean;
        helpbuyInfoName.setText(buyInfoBean.getDetail().getEnd_name());
        helpbuyInfoNumber.setText(buyInfoBean.getDetail().getEnd_telephone());
        helpbuyInfoUserAddress.setText(buyInfoBean.getDetail().getEnd_address() + buyInfoBean.getDetail().getEnd_detail());
        if (TextUtils.isEmpty(buyInfoBean.getDetail().getBuy_address())) {
            helpbuyInfoBuyInfo.setText("就近购买");
        } else {
            helpbuyInfoBuyInfo.setText(buyInfoBean.getDetail().getBuy_address());
        }
        helpbuyInfoGoods.setText(buyInfoBean.getDetail().getGoods());
        if (TextUtils.isEmpty(buyInfoBean.getDetail().getPrice())) {
            helpbuyInfoGoodsMoney.setText("线下凭支付信息支付给买手");
        } else {
            helpbuyInfoGoodsMoney.setText(buyInfoBean.getDetail().getPrice() + "元");
        }
        helpbuyInfoBuyTime.setText(buyInfoBean.getDetail().getDelivery_time());
        helpbuyInfoServerMoney.setText(buyInfoBean.getDetail().getTask_price() + "元");
        helpbuyInfoCount.setText(buyInfoBean.getDetail().getPay_fee() + "元");
        helpbuyInfoCode.setText("订单号:" + buyInfoBean.getDetail().getOrder_sn());
        helpbuyInfoCearteTime.setText("创建时间:" + buyInfoBean.getDetail().getCreate_time());
        if (buyInfoBean.getDetail().getCoupon().equals("0.00")) {
            helpbuyInfoPreferntial.setText("暂无优惠劵");
        } else {
            helpbuyInfoPreferntial.setText(buyInfoBean.getDetail().getCoupon() + "元");
        }
        helpbuyInfoBeizhu.setText(buyInfoBean.getDetail().getRemarks());
        //获取order_id
        order_id = buyInfoBean.getDetail().getOrder_id();
        cate_id = buyInfoBean.getDetail().getCate_id();
        server_id = buyInfoBean.getDetail().getServer_id();
        progress = buyInfoBean.getDetail().getProgress();
        if (buyInfoBean.getDetail().getProgress().equals("5") || buyInfoBean.getDetail().getProgress().equals("6") || buyInfoBean.getDetail().getProgress().equals("7")) {
            buyorderPayBt.setText("返回订单列表");
        } else if (buyInfoBean.getDetail().getProgress().equals("2")) {
            buyorderPayBt.setText("取消订单");
        } else if (buyInfoBean.getDetail().getProgress().equals("4")) {
            buyorderPayBt.setText("去评价");
        }else if(buyInfoBean.getDetail().getProgress().equals("1")){
            buyorderPayBt.setText("去支付");
        }
        //进度
        if (buyInfoBean.getDetail().getProgress().equals("1")) {
            buyorderType.setText("待付款");
        } else if (buyInfoBean.getDetail().getProgress().equals("2")) {
            buyorderType.setText("待接单");
        } else if (buyInfoBean.getDetail().getProgress().equals("3")) {
            buyorderType.setText("已接单");
        } else if (buyInfoBean.getDetail().getProgress().equals("4")) {
            buyorderType.setText("待评价");
        } else if (buyInfoBean.getDetail().getProgress().equals("5")) {
            buyorderType.setText("已完成");
        } else if (buyInfoBean.getDetail().getProgress().equals("6")) {
            buyorderType.setText("已取消");
        } else if (buyInfoBean.getDetail().getProgress().equals("7")) {
            buyorderType.setText("已退款");
        } else if (buyInfoBean.getDetail().getProgress().equals("8")) {
            buyorderType.setText("送货中");
        } else {
            buyorderType.setText("已完成");
        }
        server_id = buyInfoBean.getDetail().getServer_id();
    }

    @Override
    public void cancelOrder(CancelOrderBean bean) {
        //发送消息
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_I, 1);
        post(eventMessage);
        Toast.makeText(this, "取消订单成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_C) {
                Intent intent = new Intent(BuyIngOrderInfoActivity.this, PayInfoCompleteActivity.class);
                intent.putExtra("task_id", taskId);
                intent.putExtra("cate_id", cate_id);
                startActivityForResult(intent, 2);
            }
        }
    }
}
