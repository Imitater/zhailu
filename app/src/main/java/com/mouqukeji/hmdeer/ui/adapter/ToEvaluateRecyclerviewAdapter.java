package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.AllOrderBean;

import java.util.List;

public class ToEvaluateRecyclerviewAdapter extends BaseQuickAdapter<AllOrderBean.TasksBean, BaseViewHolder> {

    public ToEvaluateRecyclerviewAdapter(int layoutResId, @Nullable List<AllOrderBean.TasksBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, AllOrderBean.TasksBean item) {
        helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getOrder_sn());
        helper.setText(R.id.adapter_type, "待评价");
        helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getCate_name());
        helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getMakeup_fee());
        helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getDelivery_time());
        helper.setText(R.id.adapter_pay, "已支付");

        helper.setText(R.id.adapter_money,item.getPay_fee());
        helper.addOnClickListener(R.id.adapter_dismiss);

       }

}
