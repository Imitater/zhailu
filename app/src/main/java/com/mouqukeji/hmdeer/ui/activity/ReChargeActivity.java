package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.ReChargeBean;
import com.mouqukeji.hmdeer.bean.RechargePageBean;
import com.mouqukeji.hmdeer.contract.activity.ReChargeContract;
import com.mouqukeji.hmdeer.modle.activity.ReChargeModel;
import com.mouqukeji.hmdeer.presenter.activity.ReChargePresenter;
import com.mouqukeji.hmdeer.ui.fragment.PrepaidCompleteFragment;
import com.mouqukeji.hmdeer.ui.fragment.RechargeCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReChargeActivity extends BaseActivity<ReChargePresenter, ReChargeModel> implements ReChargeContract.View, View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;
    @BindView(R.id.recharge_actionbar)
    MyActionBar rechargeActionbar;
    @BindView(R.id.recharge_bt_100)
    LinearLayout rechargeBt100;
    @BindView(R.id.charge_bt_200)
    LinearLayout chargeBt200;
    @BindView(R.id.recharge_bt_500)
    LinearLayout rechargeBt500;
    @BindView(R.id.recharge_bt_1000)
    LinearLayout rechargeBt1000;
    @BindView(R.id.dialog_pay_weixin)
    RadioButton dialogPayWeixin;
    @BindView(R.id.dialog_pay_zhifubao)
    RadioButton dialogPayZhifubao;
    @BindView(R.id.dialog_pay_money_tv)
    TextView dialogPayMoneyTv;
    @BindView(R.id.recharge_pay_bt)
    Button rechargePayBt;
    @BindView(R.id.recharge_bt_100_tv)
    TextView rechargeBt100Tv;
    @BindView(R.id.recharge_bt_200_tv)
    TextView rechargeBt200Tv;
    @BindView(R.id.recharge_bt_500_tv)
    TextView rechargeBt500Tv;
    @BindView(R.id.recharge_bt_1000_tv)
    TextView rechargeBt1000Tv;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.recharge_bt_500_tv_more)
    TextView rechargeBt500TvMore;
    @BindView(R.id.recharge_bt_1000_tv_more)
    TextView rechargeBt1000TvMore;
    private String spUserID;
    private String price;
    private int type;
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
                        Toast.makeText(ReChargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        if (rechangeType.equals("1")) {
                            //进入支付成功页面
                            framelayout.setVisibility(View.VISIBLE);
                            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RechargeCompleteFragment(), "recharge_complete").commit();
                        } else {
                            framelayout.setVisibility(View.VISIBLE);
                            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new PrepaidCompleteFragment(), "prepaid_complete").commit();
                        }
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(ReChargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
    private String rechangeType;
    private RechargePageBean bean1;

    @Override
    protected void initViewAndEvents() {
        mMvpPresenter.reChargePage(mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void setUpView() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        rechangeType = intent.getStringExtra("rechange_type");
        //设置title
        rechargeActionbar.setTitle("充值");
        //单选按钮设置
        setRadioButton();
        //设置充值金额
        setRecharge();
        //设置默认金额
        price = "10.00";
        dialogPayMoneyTv.setText("¥10.00");
        rechargePayBt.setOnClickListener(this);
    }

    //设置充值金额
    private void setRecharge() {
        //设置默认选择为 100
        rechargeBt100.setSelected(true);
        chargeBt200.setSelected(false);
        rechargeBt500.setSelected(false);
        rechargeBt1000.setSelected(false);
        //默认字体设置
        rechargeBt100Tv.setTextColor(getResources().getColor(R.color.blue));
        rechargeBt200Tv.setTextColor(getResources().getColor(R.color.black));
        rechargeBt500Tv.setTextColor(getResources().getColor(R.color.black));
        rechargeBt1000Tv.setTextColor(getResources().getColor(R.color.black));

        rechargeBt100.setOnClickListener(this);
        chargeBt200.setOnClickListener(this);
        rechargeBt500.setOnClickListener(this);
        rechargeBt1000.setOnClickListener(this);
    }

    private void setRadioButton() {
        //设置默认选择为微信
        dialogPayWeixin.setChecked(true);
        dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));

        //微信单选点击设置
        dialogPayWeixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogPayWeixin.setChecked(true);
                    dialogPayZhifubao.setChecked(false);

                    dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                    dialogPayZhifubao.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                }
            }
        });
        //支付宝单选点击设置
        dialogPayZhifubao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dialogPayWeixin.setChecked(false);
                    dialogPayZhifubao.setChecked(true);

                    dialogPayWeixin.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.order_code_gray)));
                    dialogPayZhifubao.setButtonTintList(ColorStateList.valueOf(getResources().getColor(R.color.blue)));
                }
            }
        });
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recharge_bt_100://充值100
                //默认字体设置
                rechargeBt100Tv.setTextColor(getResources().getColor(R.color.blue));
                rechargeBt200Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt500Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt1000Tv.setTextColor(getResources().getColor(R.color.black));

                rechargeBt100.setSelected(true);
                chargeBt200.setSelected(false);
                rechargeBt500.setSelected(false);
                rechargeBt1000.setSelected(false);
                price = bean1.getRecharge().get(0).getRecharge_fee();
                dialogPayMoneyTv.setText("¥"+price);
                break;
            case R.id.charge_bt_200://充值 200
                rechargeBt100Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt200Tv.setTextColor(getResources().getColor(R.color.blue));
                rechargeBt500Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt1000Tv.setTextColor(getResources().getColor(R.color.black));

                rechargeBt100.setSelected(false);
                chargeBt200.setSelected(true);
                rechargeBt500.setSelected(false);
                rechargeBt1000.setSelected(false);
                price = bean1.getRecharge().get(1).getRecharge_fee();
                dialogPayMoneyTv.setText("¥"+price);
                 break;
            case R.id.recharge_bt_500://充值 500
                rechargeBt100Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt200Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt500Tv.setTextColor(getResources().getColor(R.color.blue));
                rechargeBt1000Tv.setTextColor(getResources().getColor(R.color.black));

                rechargeBt100.setSelected(false);
                chargeBt200.setSelected(false);
                rechargeBt500.setSelected(true);
                rechargeBt1000.setSelected(false);
                price = bean1.getRecharge().get(2).getRecharge_fee();
                dialogPayMoneyTv.setText("¥"+price);
                 break;
            case R.id.recharge_bt_1000://充值 1000
                rechargeBt100Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt200Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt500Tv.setTextColor(getResources().getColor(R.color.black));
                rechargeBt1000Tv.setTextColor(getResources().getColor(R.color.blue));

                rechargeBt100.setSelected(false);
                chargeBt200.setSelected(false);
                rechargeBt500.setSelected(false);
                rechargeBt1000.setSelected(true);
                price = bean1.getRecharge().get(3).getRecharge_fee();
                dialogPayMoneyTv.setText("¥"+price);
                break;
            case R.id.recharge_pay_bt:
                if (dialogPayWeixin.isChecked()) {
                    type = 1;
                    mMvpPresenter.reCharge(spUserID, price, price, "1", mMultipleStateView);//微信充值
                } else {
                    type = 2;
                    mMvpPresenter.reCharge(spUserID, price, price, "2", mMultipleStateView);//支付宝充值
                }
                break;
        }
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            //进入支付成功页面
            if (rechangeType.equals("1")) {
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RechargeCompleteFragment(), "recharge_complete").commit();
            } else {
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new PrepaidCompleteFragment(), "prepaid_complete").commit();
            }
        }
    }

    @Override
    public void reCharge(ReChargeBean bean) {
        if (type == 1) {//调用微信支付
            PaymentHelper.reChargeWeChatPay(this, bean);
        } else {//调用支付宝支付
            PaymentHelper.aliPay(this, bean.getPayInfo(), mHandler);//调取支付宝支付
        }
    }

    @Override
    public void reChargePage(RechargePageBean bean) {
        bean1 = bean;
        rechargeBt100Tv.setText("充值"+bean.getRecharge().get(0).getRecharge_fee()+"元");
        rechargeBt200Tv.setText("充值"+bean.getRecharge().get(1).getRecharge_fee()+"元");
        rechargeBt500Tv.setText("充值"+bean.getRecharge().get(2).getRecharge_fee()+"元");
        rechargeBt1000Tv.setText("充值"+bean.getRecharge().get(3).getRecharge_fee()+"元");

        rechargeBt500TvMore.setText("送"+bean.getRecharge().get(2).getReward_fee()+"元");
        rechargeBt1000TvMore.setText("送"+bean.getRecharge().get(3).getReward_fee()+"元");
    }


}
