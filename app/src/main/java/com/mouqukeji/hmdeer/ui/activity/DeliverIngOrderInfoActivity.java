package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.DeliverIngOrderInfoContract;
import com.mouqukeji.hmdeer.modle.activity.DeliverIngOrderInfoModel;
import com.mouqukeji.hmdeer.presenter.activity.DeliverIngOrderInfoPresenter;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
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

public class DeliverIngOrderInfoActivity extends BaseActivity<DeliverIngOrderInfoPresenter, DeliverIngOrderInfoModel> implements DeliverIngOrderInfoContract.View, View.OnClickListener {
    @BindView(R.id.orderinfo_bottom_tv)
    TextView orderinfoBottomTv;
    @BindView(R.id.orderinfo_bottom_bt)
    Button orderinfoBottomBt;
    @BindView(R.id.orderinfo_bottom)
    LinearLayout orderinfoBottom;
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
    @BindView(R.id.takeing_bt)
    TextView takeingBt;
    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.action_save)
    TextView actionSave;
    private String taskId;
    private String cateId;
    public static DeliverIngOrderInfoActivity instance = null;
    private String order_id;
    private String cate_id;
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
                        Toast.makeText(DeliverIngOrderInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DeliverIngOrderInfoActivity.this, PayInfoCompleteActivity.class);
                        intent.putExtra("task_id", taskId);
                        intent.putExtra("cate_id", cate_id);
                        startActivityForResult(intent, 4);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(DeliverIngOrderInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String progress;
    private String payFee;
    private String spUserID;
    private HelpDeliverInfoBean infoBean;
    private String server_id;

    @Override
    protected void initViewAndEvents() {
        //设置标记
        instance = this;
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        taskId = intent.getStringExtra("taskId");
        cateId = intent.getStringExtra("cateId");
        mMvpPresenter.getDeliverInfo(taskId, cateId, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_deliveringorder_info;
    }

    @Override
    protected void setUpView() {
        actionTitle.setText("订单详情");
        //设置按键监听
        initListener();
    }


    private void initListener() {
        orderinfoBottomBt.setOnClickListener(this);
        orderinfoBottomTv.setOnClickListener(this);
        takeingBt.setOnClickListener(this);
        actionBack.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            mMvpPresenter.getDeliverInfo(taskId, cateId, mMultipleStateView);
        }
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
                DialogUtils.processDialog(DeliverIngOrderInfoActivity.this, inflate_procress, true, true, infoBean);
                break;
            case R.id.orderinfo_bottom_tv:
                //进程显示框
                View inflate_procress1 = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(DeliverIngOrderInfoActivity.this, inflate_procress1, true, true, infoBean);
                break;
            case R.id.takeing_bt:
                if (progress.equals("5") || progress.equals("6") || progress.equals("7")) {
                    finish();
                } else if (progress.equals("1")) {
                    //待支付
                    mMvpPresenter.payYueInfo(spUserID, order_id, mMultipleStateView);//获取余额
                } else if (progress.equals("2")) {
                    //取消订单
                    mMvpPresenter.cancelOrder(taskId, spUserID, mMultipleStateView);
                } else if (progress.equals("4")) {
                    Intent intent = new Intent(DeliverIngOrderInfoActivity.this, EvaluationActivity.class);
                    intent.putExtra("order_id", order_id);
                    intent.putExtra("cate_id", cate_id);
                    intent.putExtra("server_id", server_id);
                    startActivity(intent);
                }
                break;
            case R.id.action_back:
                finish();
                break;
        }
    }


    @Override
    public void getDeliverInfo(HelpDeliverInfoBean bean) {
        infoBean = bean;
        order_id = bean.getDetail().getOrder_id();
        server_id = bean.getDetail().getServer_id();
        orderinfoName.setText(bean.getDetail().getStart_name());
        orderinfoNumber.setText(bean.getDetail().getStart_telephone());
        orderinfoAddress.setText(bean.getDetail().getStart_address() + bean.getDetail().getStart_detail());
        cate_id = bean.getDetail().getCate_id();
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


        progress = bean.getDetail().getProgress();
        if (bean.getDetail().getProgress().equals("5") || bean.getDetail().getProgress().equals("6") || bean.getDetail().getProgress().equals("7")) {
            takeingBt.setText("返回订单列表");
        } else if (bean.getDetail().getProgress().equals("2")) {
            takeingBt.setText("取消订单");
        } else if (bean.getDetail().getProgress().equals("4")) {
            takeingBt.setText("去评价");
        } else if (bean.getDetail().getProgress().equals("1")) {
            takeingBt.setText("去支付");
        }
        //进度
        if (bean.getDetail().getProgress().equals("1")) {
            orderinfoBottomTv.setText("待付款");
        } else if (bean.getDetail().getProgress().equals("2")) {
            orderinfoBottomTv.setText("待接单");
        } else if (bean.getDetail().getProgress().equals("3")) {
            orderinfoBottomTv.setText("已接单");
        } else if (bean.getDetail().getProgress().equals("4")) {
            orderinfoBottomTv.setText("待评价");
        } else if (bean.getDetail().getProgress().equals("5")) {
            orderinfoBottomTv.setText("已完成");
        } else if (bean.getDetail().getProgress().equals("6")) {
            orderinfoBottomTv.setText("已取消");
        } else if (bean.getDetail().getProgress().equals("7")) {
            orderinfoBottomTv.setText("已退款");
        } else if (bean.getDetail().getProgress().equals("8")) {
            orderinfoBottomTv.setText("送货中");
        } else {
            orderinfoBottomTv.setText("已完成");
        }
        payFee = bean.getDetail().getPay_fee();
    }

    @Override
    public void cancelOrder(CancelOrderBean bean) {
        //发送消息
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_I, 1);
        post(eventMessage);
        Toast.makeText(this, "取消订单成功", Toast.LENGTH_SHORT).show();
        finish();
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
        startActivity(intent);
    }

    @Override
    public void payYueInfo(PayYueBean bean) {
        //付款框
        setPay(payFee, bean.getBalance(), order_id);
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
                Intent intent = new Intent(DeliverIngOrderInfoActivity.this, ReChargeActivity.class);
                intent.putExtra("rechange_type", "1");//设置充值标记
                startActivity(intent);
            }
        });
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
                Intent intent = new Intent(DeliverIngOrderInfoActivity.this, PayInfoCompleteActivity.class);
                intent.putExtra("task_id", taskId);
                intent.putExtra("cate_id", cate_id);
                startActivityForResult(intent, 4);
            }
        }
    }
}
