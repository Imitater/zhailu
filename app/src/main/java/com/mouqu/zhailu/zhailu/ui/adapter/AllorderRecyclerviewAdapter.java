package com.mouqu.zhailu.zhailu.ui.adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.bean.IndexBean;
import com.mouqu.zhailu.zhailu.util.MultipleItem;

import java.util.List;

public class AllorderRecyclerviewAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {


    public AllorderRecyclerviewAdapter(Context mContext, List list) {
        super(list);
        addItemType(MultipleItem.Generation_Payment, R.layout.adapter_generation_payment_layout);
        addItemType(MultipleItem.Generation_Order, R.layout.adapter_generation_order);
        addItemType(MultipleItem.Ongoing, R.layout.adapter_ongoing_layout);
        addItemType(MultipleItem.Complete, R.layout.adapter_complete_layout);
        addItemType(MultipleItem.To_Evaluate, R.layout.adapter_to_evaluate_layout);
        addItemType(MultipleItem.Cancelled, R.layout.adapter_cancelled_layout);
        addItemType(MultipleItem.HaveRefund, R.layout.adapter_cancelled_layout);
        addItemType(MultipleItem.Sending, R.layout.adapter_ongoing_layout);
        addItemType(MultipleItem.Sended, R.layout.adapter_ongoing_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem multipleItem) {
        switch (helper.getItemViewType()) {
            case 1://待支付
                 helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //取消订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                //去支付
                helper.setOnClickListener(R.id.adapter_to_pay, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                break;
            case 2://待接单
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //取消订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                break;
            case 3://进行中
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                 //去支付
                helper.setOnClickListener(R.id.adapter_to_pay, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case 4://已完成
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //删除订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                break;
            case 5://待评价
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //去评价
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                break;
            case 6://已取消
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //取消订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

                break;
            case 7:
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //取消订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case 8:
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //去支付
                helper.setOnClickListener(R.id.adapter_to_pay, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            case 9:
                helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
                if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
                    helper.setText(R.id.adapter_type, "待付款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
                    helper.setText(R.id.adapter_type, "待接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
                    helper.setText(R.id.adapter_type, "已接单");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
                    helper.setText(R.id.adapter_type, "待评价");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
                    helper.setText(R.id.adapter_type, "已完成");
                } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
                    helper.setText(R.id.adapter_type, "已取消");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
                    helper.setText(R.id.adapter_type, "已退款");
                } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
                    helper.setText(R.id.adapter_type, "送货中");
                } else {
                    helper.setText(R.id.adapter_type, "已送达");
                }
                helper.setText(R.id.adapter_server_type,getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money,getData().get(helper.getLayoutPosition()).getBean().getMakeup_fee());
                helper.setText(R.id.adapter_time,getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")){
                    helper.setText(R.id.adapter_pay,"未支付");
                }else{
                    helper.setText(R.id.adapter_pay,"已支付");
                }
                //去支付
                helper.setOnClickListener(R.id.adapter_to_pay, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
        }
    }
}
