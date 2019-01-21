package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.HelpSendContract;
import com.mouqu.zhailu.zhailu.modle.activity.HelpSendModel;
import com.mouqu.zhailu.zhailu.presenter.activity.HelpSendPresenter;
import com.mouqu.zhailu.zhailu.ui.fragment.SendAddressNewReceiverFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.SendEditFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.SendPayCompleteFragment;
import com.mouqu.zhailu.zhailu.ui.widget.ButtomDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.DialogUtils;

import butterknife.BindView;

public class HelpSendActivity extends BaseActivity<HelpSendPresenter, HelpSendModel> implements HelpSendContract.View, View.OnClickListener {

    @BindView(R.id.helpsend_actionbar)
    MyActionBar helpsendActionbar;
    @BindView(R.id.helpsend_hint)
    TextView helpsendHint;
    @BindView(R.id.helpsend_next)
    ImageView helpsendNext;
    @BindView(R.id.helpsend_info)
    RelativeLayout helpsendInfo;
    @BindView(R.id.helpsend_address)
    LinearLayout helpsendAddress;
    @BindView(R.id.receiver_name)
    TextView receiverName;
    @BindView(R.id.receiver_number)
    TextView receiverNumber;
    @BindView(R.id.helptake_location)
    TextView helptakeLocation;
    @BindView(R.id.address_linearlayout)
    LinearLayout addressLinearlayout;
    @BindView(R.id.address_commonly)
    LinearLayout addressCommonly;
    @BindView(R.id.address_defaul)
    LinearLayout addressDefaul;
    @BindView(R.id.helpsend_item_tv)
    TextView helpsendItemTv;
    @BindView(R.id.helpsend_items)
    LinearLayout helpsendItems;
    @BindView(R.id.helpsend_location_tv)
    TextView helpsendLocationTv;
    @BindView(R.id.helpsend_location)
    LinearLayout helpsendLocation;
    @BindView(R.id.helpsend_pay_tv)
    TextView helpsendPayTv;
    @BindView(R.id.helpsend_pay)
    LinearLayout helpsendPay;
    @BindView(R.id.helpsend_time_tv)
    TextView helpsendTimeTv;
    @BindView(R.id.helpsend_time)
    LinearLayout helpsendTime;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.helpsend_order)
    Button helpsendOrder;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpsend;
    }

    @Override
    protected void setUpView() {
        //设置title
        helpsendActionbar.setTitle("帮忙寄");
        //点击事件监听
        initListener();
    }

    private void initListener() {
        helpsendInfo.setOnClickListener(this);
        addressCommonly.setOnClickListener(this);
        addressLinearlayout.setOnClickListener(this);
        helpsendItems.setOnClickListener(this);
        helpsendPay.setOnClickListener(this);
        helpsendLocation.setOnClickListener(this);
        helpsendTime.setOnClickListener(this);
        helpsendOrder.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpsend_info://寄件人信息 第一次进入页面 输入地址
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new SendAddressNewReceiverFragment(), "new_address").commit();
                break;
            case R.id.address_commonly://常用 进入地址列表
                Intent intent = new Intent(HelpSendActivity.this, SendAddressListActivity.class);
                startActivity(intent);
                break;
            case R.id.address_linearlayout://进入修改地址页面
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new SendEditFragment(), "edit_address").commit();
                break;
            case R.id.helpsend_items://物品类型
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_items, null);
               // DialogUtils.showButtomItemsDialog(HelpSendActivity.this, inflate_items, true, true, helpsendItemTv);
                break;
            case R.id.helpsend_location://快递服务
                View inflate = getLayoutInflater().inflate(R.layout.dialog_reciver, null);
                //获取选择项
                DialogUtils.showButtomAddressDialog(HelpSendActivity.this, inflate, true, true, helpsendLocationTv);
                break;
            case R.id.helpsend_pay://付款方式
                View inflate_payway = getLayoutInflater().inflate(R.layout.dialog_reciver, null);
                //获取选择项
                DialogUtils.payWayDialog(HelpSendActivity.this, inflate_payway, true, true, helpsendPayTv);
                break;
            case R.id.helpsend_time://揽件时间
                DialogUtils.timeDialog(HelpSendActivity.this, helpsendTimeTv, "立即取件");
                break;
            case R.id.helpsend_order://下单
                setPayDialog();//显示支付框
                break;
        }
    }
    //pay dialog
    private void setPayDialog() {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_pay, null);
        //获取显示框 view
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpSendActivity.this, inflate_pay, true, true);
        //支付按钮点击
        TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        //支付按钮
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new SendPayCompleteFragment(), "send_pay").commit();
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpSendActivity.this, ReChargeActivity.class);
                startActivity(intent);
            }
        });
    }

}
