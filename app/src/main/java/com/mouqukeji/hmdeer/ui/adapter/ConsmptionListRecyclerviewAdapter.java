package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.ConsumptionListBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;

import java.util.List;

public class ConsmptionListRecyclerviewAdapter extends BaseQuickAdapter<ConsumptionListBean.BuyBean.ConsumeBean, BaseViewHolder> {

    private final String total;

    public ConsmptionListRecyclerviewAdapter(int layoutResId, @Nullable List<ConsumptionListBean.BuyBean.ConsumeBean> data, String total) {
        super(layoutResId, data);
        this.total=total;
     }

    @Override
    protected void convert(BaseViewHolder helper, ConsumptionListBean.BuyBean.ConsumeBean item) {
        if (getData().get(helper.getLayoutPosition()).getType().equals("2")){
            helper.setText(R.id.type,"订单消费");
            helper.setText(R.id.money,"-"+getData().get(helper.getLayoutPosition()).getMoney());
        }else if (getData().get(helper.getLayoutPosition()).getType().equals("4")){
            helper.setText(R.id.type,"补差消费");
        }else if (getData().get(helper.getLayoutPosition()).getType().equals("5")){
            helper.setText(R.id.type,"订单退款");
        }else if (getData().get(helper.getLayoutPosition()).getType().equals("6")){
            helper.setText(R.id.type,"补差退款");
        }
        helper.setText(R.id.time,getData().get(helper.getLayoutPosition()).getCreate_time());

    }

}
