package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.AllOrderBean;

import java.util.List;

public class OngoingOrderRecyclerviewAdapter extends BaseQuickAdapter<AllOrderBean.TasksBean, BaseViewHolder> {

    public OngoingOrderRecyclerviewAdapter(int layoutResId, @Nullable List<AllOrderBean.TasksBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, AllOrderBean.TasksBean item) {
        helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getOrder_sn());
        if (getData().get(helper.getLayoutPosition()).getProgress().equals("3")) {
            helper.setText(R.id.adapter_type, "已接单");
            Button dialogDismiss=helper.getView(R.id.adapter_dismiss);
            dialogDismiss.setVisibility(View.GONE);
            Button dialogUndismiss=helper.getView(R.id.adapter_undismiss);
            dialogUndismiss.setVisibility(View.VISIBLE);
           } else if (getData().get(helper.getLayoutPosition()).getProgress().equals("8")) {
            helper.setText(R.id.adapter_type, "送货中");
        }else{
            Button dialogDismiss=helper.getView(R.id.adapter_dismiss);
            dialogDismiss.setVisibility(View.VISIBLE);
            Button dialogUndismiss=helper.getView(R.id.adapter_undismiss);
            dialogUndismiss.setVisibility(View.GONE);
          }
        helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getCate_name());
        helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getMakeup_fee());
        helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getDelivery_time());
        if (getData().get(helper.getLayoutPosition()).getExpress_pay_type().equals("0")) {
            helper.setText(R.id.adapter_pay, "未支付");
        } else {
            helper.setText(R.id.adapter_pay, "已支付");
        }
        helper.setText(R.id.adapter_money,item.getPay_fee());
        helper.addOnClickListener(R.id.adapter_dismiss);
    }

}
