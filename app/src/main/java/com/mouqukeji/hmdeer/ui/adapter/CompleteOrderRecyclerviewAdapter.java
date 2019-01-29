package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.AllOrderBean;

import java.util.List;

public class CompleteOrderRecyclerviewAdapter extends BaseQuickAdapter<AllOrderBean.TasksBean, BaseViewHolder> {

    public CompleteOrderRecyclerviewAdapter(int layoutResId, @Nullable List<AllOrderBean.TasksBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, AllOrderBean.TasksBean item) {
        helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getOrder_sn());
        helper.setText(R.id.adapter_type, "待付款");
        helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getCate_name());
        helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getMakeup_fee());
        helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getDelivery_time());
        if (getData().get(helper.getLayoutPosition()).getExpress_pay_type().equals("0")) {
            helper.setText(R.id.adapter_pay, "未支付");
        } else {
            helper.setText(R.id.adapter_pay, "已支付");
        }
        //取消订单
        helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

       }

}
