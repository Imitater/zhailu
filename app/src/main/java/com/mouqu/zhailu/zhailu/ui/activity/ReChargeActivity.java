package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.ReChargeContract;
import com.mouqu.zhailu.zhailu.modle.activity.ReChargeModel;
import com.mouqu.zhailu.zhailu.presenter.activity.ReChargePresenter;
import com.mouqu.zhailu.zhailu.ui.fragment.RechargeCompleteFragment;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;

import butterknife.BindView;

public class ReChargeActivity extends BaseActivity<ReChargePresenter, ReChargeModel> implements ReChargeContract.View, View.OnClickListener{
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

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_recharge;
    }

    @Override
    protected void setUpView() {
        //设置title
        rechargeActionbar.setTitle("充值");
        //单选按钮设置
        setRadioButton();
        //设置充值金额
        setRecharge();

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
                break;
            case R.id.recharge_pay_bt:
                //进入支付成功页面
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new RechargeCompleteFragment(), "recharge_complete").commit();
                break;

        }
    }



}
