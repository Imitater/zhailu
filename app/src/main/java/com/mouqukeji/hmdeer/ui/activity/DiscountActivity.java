package com.mouqukeji.hmdeer.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.ConvertGoodsBean;
import com.mouqukeji.hmdeer.bean.DetailIntegralBean;
import com.mouqukeji.hmdeer.contract.activity.DiscountContract;
import com.mouqukeji.hmdeer.modle.activity.DiscountModel;
import com.mouqukeji.hmdeer.presenter.activity.DiscountPresenter;
import com.mouqukeji.hmdeer.ui.fragment.DiscountCompleteFragment;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscountActivity extends BaseActivity<DiscountPresenter, DiscountModel> implements DiscountContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.discount_money)
    TextView discountMoney;
    @BindView(R.id.discount_type)
    TextView discountType;
    @BindView(R.id.discount_contact)
    TextView discountContact;
    @BindView(R.id.discount_type_money)
    TextView discountTypeMoney;
    @BindView(R.id.discount_time)
    TextView discountTime;
    @BindView(R.id.discount_use)
    TextView discountUse;
    @BindView(R.id.discount_use_contact)
    TextView discountUseContact;
    @BindView(R.id.discount_background)
    LinearLayout discountBackground;
    @BindView(R.id.discount_bt)
    Button discountBt;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    private String spUserID;
    private String good_id;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        good_id = intent.getStringExtra("good_id");
        mMvpPresenter.detailIntegral(good_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_discount;
    }

    @Override
    protected void setUpView() {
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        discountBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_back:
                finish();
                break;
            case R.id.discount_bt:
                //领取
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_discount, null);
                DialogUtils.discountDialog(this, inflate_items, true, true, mMvpPresenter, mMultipleStateView, good_id, spUserID);
                break;
        }
    }


    @Override
    public void detailIntegral(DetailIntegralBean bean) {
        if (bean.getGoods().getNum().equals("1")) {
            //优惠券1
            discountBackground.setBackgroundResource(R.mipmap.mipmap_tiyan1_backgournd);
                discountMoney.setText("¥1");
                discountContact.setText("使用宅鹿下单立减1元");
                discountTypeMoney.setText("1元优惠券");
                discountTime.setText("自兑换日期起三天之内");
                if (bean.getGoods().getNum().equals("10")) {
                    discountUseContact.setText("宅鹿所有的系列服务，下单支付时使用");
                } else if (bean.getGoods().getNum().equals("11")) {
                    discountUseContact.setText("宅鹿帮忙取的系列服务，下单支付时使用");
                } else if (bean.getGoods().getNum().equals("12")) {
                    discountUseContact.setText("宅鹿帮忙买的系列服务，下单支付时使用");
                } else if (bean.getGoods().getNum().equals("13")) {
                    discountUseContact.setText("宅鹿帮忙寄的系列服务，下单支付时使用");
                } else if (bean.getGoods().getNum().equals("14")) {
                    discountUseContact.setText("宅鹿帮忙送的系列服务，下单支付时使用");
            }
            discountBt.setText(bean.getGoods().getIntegral()+"积分兑换");
        } else {
            //优惠券1
            discountBackground.setBackgroundResource(R.mipmap.mipmap_tiyan2_backgournd);
            discountMoney.setText("¥2");
            discountContact.setText("使用宅鹿下单立减2元");
            discountTypeMoney.setText("2元优惠券");
            discountTime.setText("自兑换日期起三天之内");
            if (bean.getGoods().getNum().equals("10")) {
                discountUseContact.setText("宅鹿所有的系列服务，下单支付时使用");
            } else if (bean.getGoods().getNum().equals("11")) {
                discountUseContact.setText("宅鹿帮忙取的系列服务，下单支付时使用");
            } else if (bean.getGoods().getNum().equals("12")) {
                discountUseContact.setText("宅鹿帮忙买的系列服务，下单支付时使用");
            } else if (bean.getGoods().getNum().equals("13")) {
                discountUseContact.setText("宅鹿帮忙寄的系列服务，下单支付时使用");
            } else if (bean.getGoods().getNum().equals("14")) {
                discountUseContact.setText("宅鹿帮忙送的系列服务，下单支付时使用");
            }
            discountBt.setText(bean.getGoods().getIntegral()+"积分兑换");
        }
    }

    @Override
    public void convertGoods(ConvertGoodsBean bean) {
        framelayout.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DiscountCompleteFragment(),"discount_complete").commit();
    }

    @Override
    public void isEnough() {
        Toast.makeText(this,"积分不足",Toast.LENGTH_SHORT).show();
    }

}
