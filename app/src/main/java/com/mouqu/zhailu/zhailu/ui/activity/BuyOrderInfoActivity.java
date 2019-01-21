package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.BuyOrderInfoContract;
import com.mouqu.zhailu.zhailu.modle.activity.BuyOrderInfoModel;
import com.mouqu.zhailu.zhailu.presenter.activity.BuyOrderInfoPresenter;
import com.mouqu.zhailu.zhailu.ui.fragment.PayItemsCompleteFragment;
import com.mouqu.zhailu.zhailu.ui.widget.ButtomDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.CenterDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.DialogUtils;

import butterknife.BindView;

public class BuyOrderInfoActivity extends BaseActivity<BuyOrderInfoPresenter, BuyOrderInfoModel> implements BuyOrderInfoContract.View, View.OnClickListener{


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

    @Override
    protected void initViewAndEvents() {

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
                        setPayDialog();//显示 paydialog
                    }
                });
                break;
            case R.id.buyorder_left_tv://返回订单列表

                break;
            case R.id.buyorder_right_tv://支付商品价格
                setPayDialog();//显示 paydialog
                break;
            case R.id.buyorder_next://进程框
                View inflate_procress = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(BuyOrderInfoActivity.this, inflate_procress, true, true);
                break;
        }
    }

    //pay dialog
    private void setPayDialog() {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_pay, null);
        //获取显示框 view
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(BuyOrderInfoActivity.this, inflate_pay, true, true);
        //支付按钮点击
        TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        //支付按钮
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyorderFramelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.buyorder_framelayout, new PayItemsCompleteFragment(), "pay").commit();
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyOrderInfoActivity.this, ReChargeActivity.class);
                startActivity(intent);
            }
        });
    }


}
