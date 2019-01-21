package com.mouqu.zhailu.zhailu.ui.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.PlaceOrderContract;
import com.mouqu.zhailu.zhailu.modle.activity.PlaceOrderModel;
import com.mouqu.zhailu.zhailu.presenter.activity.PlaceOrderPresenter;
import com.mouqu.zhailu.zhailu.ui.fragment.PayCompleteFragment;
import com.mouqu.zhailu.zhailu.ui.widget.ButtomDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.DialogUtils;


public class PlaceOrderActivity extends BaseActivity<PlaceOrderPresenter, PlaceOrderModel> implements PlaceOrderContract.View, View.OnClickListener{

    private TextView placeOrderPayBt;
    private ImageView placeOrderNext;
    private FrameLayout payFramelayout;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_place_order;
    }

    @Override
    protected void setUpView() {
        MyActionBar placeOrderActionbar = findViewById(R.id.place_order_actionbar);
        placeOrderActionbar.setTitle("订单详情");
        placeOrderNext = findViewById(R.id.placeorder_next);

        placeOrderPayBt = findViewById(R.id.placeorder_pay_bt);
        payFramelayout = findViewById(R.id.pay_framelayout);
        //设置时间监听
        initListener();
    }

    private void initListener() {
        placeOrderPayBt.setOnClickListener(this);
        placeOrderNext.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.placeorder_pay_bt:
                View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_pay, null);
                final ButtomDialogView buttomDialogView = DialogUtils.payDialog(PlaceOrderActivity.this, inflate_pay, true, true);
                TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
                dialogPayBt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        payFramelayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().add(R.id.pay_framelayout, new PayCompleteFragment(), "pay").commit();
                        buttomDialogView.dismiss();
                    }
                });

                break;
            case R.id.placeorder_next:
                //进程显示框
                View inflate_procress = getLayoutInflater().inflate(R.layout.dialog_procress, null);
                DialogUtils.processDialog(PlaceOrderActivity.this, inflate_procress, true, true);
                break;
        }
    }
}
