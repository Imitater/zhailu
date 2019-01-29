package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.BuyOrderInfoContract;
import com.mouqukeji.hmdeer.modle.activity.BuyOrderInfoModel;
import com.mouqukeji.hmdeer.presenter.activity.BuyOrderInfoPresenter;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.ui.fragment.PayItemsCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.CenterDialogView;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BuyOrderInfoActivity extends BaseActivity<BuyOrderInfoPresenter, BuyOrderInfoModel> implements BuyOrderInfoContract.View, View.OnClickListener {


    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.buyorder_actionbar)
    MyActionBar buyorderActionbar;
    @BindView(R.id.buyorder_next)
    ImageView buyorderNext;
    @BindView(R.id.buyorder_check)
    TextView buyorderCheck;
    @BindView(R.id.buyorder_left_tv)
    TextView buyorderLeftTv;
    @BindView(R.id.buyorder_right_tv)
    TextView buyorderRightTv;
    @BindView(R.id.buyorder_pay_bt)
    TextView buyorderPayBt;
    @BindView(R.id.buyorder_framelayout)
    FrameLayout buyorderFramelayout;
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
    @BindView(R.id.helpbuy_info_buy_time)
    TextView helpbuyInfoBuyTime;
    @BindView(R.id.helpbuy_info_sex)
    TextView helpbuyInfoSex;
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
    @BindView(R.id.buyorder_have_pay)
    LinearLayout buyorderHavePay;
    private String spUserID;
    private String orderId;
    private String cateId;
    private String pay_type;
    private String makeup_id;
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
                        Toast.makeText(BuyOrderInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        buyorderFramelayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().add(R.id.buyorder_framelayout, PayItemsCompleteFragment.newInstance(order_id), "buy_pay").commit();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(BuyOrderInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
    private String order_id;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra("taskId");
        cateId = intent.getStringExtra("cateId");
        mMvpPresenter.helpBuyInfo(orderId, cateId, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_buyorder_info;
    }

    @Override
    protected void setUpView() {

        //设置title
        buyorderActionbar.setTitle("订单详情");

        //设置点击事件监听
        initListener();
    }

    private void initListener() {
        buyorderCheck.setOnClickListener(this);
        buyorderLeftTv.setOnClickListener(this);
        buyorderRightTv.setOnClickListener(this);
        buyorderNext.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buyorder_check://查看凭证
                //进程显示框
                View inflate_check = getLayoutInflater().inflate(R.layout.dialog_buy_check, null);
                //获取显示框 view
                final CenterDialogView centerDialogView = DialogUtils.checkDialog(BuyOrderInfoActivity.this, BuyOrderInfoActivity.this, inflate_check, true, true);
                TextView dialogCheckRightBt = centerDialogView.findViewById(R.id.dialog_check_right_bt);
                //支付按钮点击
                dialogCheckRightBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        centerDialogView.dismiss();
                        mMvpPresenter.payYueInfo(spUserID, orderId, mMultipleStateView);//获取余额
                    }
                });
                break;
            case R.id.buyorder_left_tv://返回订单列表

                break;
            case R.id.buyorder_right_tv://支付商品价格
                mMvpPresenter.payYueInfo(spUserID, orderId, mMultipleStateView);//获取余额
                break;
            case R.id.buyorder_next://进程框
                View inflate_procress = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(BuyOrderInfoActivity.this, inflate_procress, true, true);
                break;
        }
    }

    private void setPay(int balance) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(BuyOrderInfoActivity.this, inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        //判断余额是否为0
        if (balance != 0&&balance>Double.parseDouble(makeup_fee)) {
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
        payMoneyTv.setText("1");
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payWeiXing(makeup_id, spUserID, "1", "1", mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payZhifubao(makeup_id, spUserID, "2", "1", mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payYue(makeup_id, spUserID, "3", "1", mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyOrderInfoActivity.this, ReChargeActivity.class);
                intent.putExtra("rechange_type","1");//设置充值标记
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
        buyorderFramelayout.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.buyorder_framelayout, PayItemsCompleteFragment.newInstance(order_id), "buy_pay").commit();
    }


    @Override
    public void helpBuyInfo(HelpBuyInfoBean buyInfoBean) {
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
            helpbuyInfoGoodsMoney.setText(buyInfoBean.getDetail().getPrice()+"元");
        }
        helpbuyInfoBuyTime.setText(buyInfoBean.getDetail().getDelivery_time());
        if (buyInfoBean.getDetail().getGender().equals("0")) {
            helpbuyInfoSex.setText("男女不限");
        } else if (buyInfoBean.getDetail().getGender().equals("1")) {
            helpbuyInfoSex.setText("男");
        } else {
            helpbuyInfoSex.setText("女");
        }
        helpbuyInfoServerMoney.setText(buyInfoBean.getDetail().getTask_price() + "元");
        helpbuyInfoCount.setText(buyInfoBean.getDetail().getPay_fee() + "元");
        helpbuyInfoCode.setText("订单号:" + buyInfoBean.getDetail().getOrder_sn());
        helpbuyInfoCearteTime.setText("创建时间:" + buyInfoBean.getDetail().getCreate_time());
        if (buyInfoBean.getDetail().getCoupon().equals("0.00")) {
            helpbuyInfoPreferntial.setText("暂无优惠劵");
        } else {
            helpbuyInfoPreferntial.setText(buyInfoBean.getDetail().getCoupon() + "yua");
        }
        helpbuyInfoBeizhu.setText(buyInfoBean.getDetail().getRemarks());
        //获取markerid
        makeup_id = buyInfoBean.getDetail().getMakeup_id();
        //获取marker free
        makeup_fee = buyInfoBean.getDetail().getMakeup_fee();
        //获取order_id
        order_id = buyInfoBean.getDetail().getOrder_id();
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            buyorderFramelayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().add(R.id.buyorder_framelayout, PayItemsCompleteFragment.newInstance(order_id), "buy_pay").commit();
        }
    }


}
