package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.HelpDeliverContract;
import com.mouqu.zhailu.zhailu.modle.activity.HelpDeliverModel;
import com.mouqu.zhailu.zhailu.presenter.activity.HelpDeliverPresenter;
import com.mouqu.zhailu.zhailu.ui.fragment.DeliverPayCompleteFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.DeliverReceiveAddressNewReceiverFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.DeliverReceiveEditFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.DeliverTakeAddressNewReceiverFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.DeliverTakeEditFragment;
import com.mouqu.zhailu.zhailu.ui.widget.ButtomDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.DialogUtils;

import butterknife.BindView;

public class HelpDeliverActivity extends BaseActivity<HelpDeliverPresenter, HelpDeliverModel> implements HelpDeliverContract.View, View.OnClickListener{
    @BindView(R.id.helpdeliver_actionbar)
    MyActionBar helpdeliverActionbar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.helpdeliver_order)
    Button helpdeliverOrder;
    @BindView(R.id.helpdeliver_empty_get)
    TextView helpdeliverEmptyGet;
    @BindView(R.id.helpdeliver_get_name)
    TextView helpdeliverGetName;
    @BindView(R.id.helpdeliver_get_number)
    TextView helpdeliverGetNumber;
    @BindView(R.id.helpdeliver_get_address)
    TextView helpdeliverGetAddress;
    @BindView(R.id.helpdeliver_full_get)
    LinearLayout helpdeliverFullGet;
    @BindView(R.id.helpdeliver_get_commonly)
    LinearLayout helpdeliverGetCommonly;
    @BindView(R.id.helpdeliver_empty_put)
    TextView helpdeliverEmptyPut;
    @BindView(R.id.helpdeliver_put_name)
    TextView helpdeliverPutName;
    @BindView(R.id.helpdeliver_put_number)
    TextView helpdeliverPutNumber;
    @BindView(R.id.helpdeliver_put_address)
    TextView helpdeliverPutAddress;
    @BindView(R.id.helpdeliver_full_put)
    LinearLayout helpdeliverFullPut;
    @BindView(R.id.helpdeliver_put_commonly)
    LinearLayout helpdeliverPutCommonly;
    @BindView(R.id.helpdeliver_items)
    LinearLayout helpdeliverItems;
    @BindView(R.id.helpdeliver_sex)
    LinearLayout helpdeliverSex;
    @BindView(R.id.helpdeliver_time)
    LinearLayout helpdeliverTime;
    @BindView(R.id.helpdeliver_youhui)
    LinearLayout helpdeliverYouhui;
    @BindView(R.id.helpdeliver_beizhu)
    EditText helpdeliverBeizhu;
    @BindView(R.id.helpdeliver_money)
    TextView helpdeliverMoney;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.helpdeliver_items_tv)
    TextView helpdeliverItemsTv;
    @BindView(R.id.helpdeliver_sex_tv)
    TextView helpdeliverSexTv;
    @BindView(R.id.helpdeliver_time_tv)
    TextView helpdeliverTimeTv;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpdeliver;
    }

    @Override
    protected void setUpView() {
        //设置title
        helpdeliverActionbar.setTitle("帮忙送");

        //设置点击事件监听
        initListener();
    }

    private void initListener() {
        helpdeliverGetCommonly.setOnClickListener(this);
        helpdeliverPutCommonly.setOnClickListener(this);
        helpdeliverFullGet.setOnClickListener(this);
        helpdeliverFullPut.setOnClickListener(this);
        helpdeliverEmptyGet.setOnClickListener(this);
        helpdeliverEmptyPut.setOnClickListener(this);
        helpdeliverItems.setOnClickListener(this);
        helpdeliverSex.setOnClickListener(this);
        helpdeliverTime.setOnClickListener(this);
        helpdeliverOrder.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpdeliver_get_commonly://进入取件人地址列表
                Intent intent = new Intent(HelpDeliverActivity.this, DeliverTakeAddressListActivity.class);
                startActivity(intent);
                break;
            case R.id.helpdeliver_put_commonly://进入收件人地址列表
                Intent intent1 = new Intent(HelpDeliverActivity.this, DeliverReceiveAddressListActivity.class);
                startActivity(intent1);
                break;
            case R.id.helpdeliver_full_get://进入取件人修改界面
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DeliverTakeEditFragment(), "edit_address").commit();
                break;
            case R.id.helpdeliver_full_put://进入收件人修改界面
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DeliverReceiveEditFragment(), "edit_address").commit();
                break;
            case R.id.helpdeliver_empty_get://首次输入取件地址
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DeliverTakeAddressNewReceiverFragment(), "new_address").commit();
                break;
            case R.id.helpdeliver_empty_put://首次输入收件地址
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DeliverReceiveAddressNewReceiverFragment(), "new_address").commit();
                break;
            case R.id.helpdeliver_items://物品类型框
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_items, null);
               // DialogUtils.showButtomItemsDialog(HelpDeliverActivity.this, inflate_items, true, true, helpdeliverItemsTv,);
                break;
            case R.id.helpdeliver_sex://性别框
                View inflate_sex = getLayoutInflater().inflate(R.layout.dialog_sex, null);
                DialogUtils.showButtomSexDialog(HelpDeliverActivity.this, inflate_sex, true, true, helpdeliverSexTv);
                break;
            case R.id.helpdeliver_time://时间框
                DialogUtils.timeDialog(HelpDeliverActivity.this, helpdeliverTimeTv, "立即配送");
                break;
            case R.id.helpdeliver_order://下单按钮
                //显示付款框
                setPay();
                break;
        }
    }


    private void setPay() {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_pay, null);
        //获取显示框 view
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpDeliverActivity.this, inflate_pay, true, true);
        //支付按钮点击
        TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        //支付按钮
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DeliverPayCompleteFragment(), "send_pay").commit();
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpDeliverActivity.this, ReChargeActivity.class);
                startActivity(intent);
            }
        });
    }


}
