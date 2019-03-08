package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.IntegralListBean;

import java.util.List;

public class JiFenRecyclerviewAdapter extends BaseQuickAdapter<IntegralListBean.IntegralBean, BaseViewHolder> {

    public JiFenRecyclerviewAdapter(int layoutResId, @Nullable List<IntegralListBean.IntegralBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralListBean.IntegralBean item) {
        if (item.getType().equals("1")){
            helper.setText(R.id.adapter_type,"下单");
            helper.setText(R.id.adapter_money,"+"+item.getIntegral());
        }else if (item.getType().equals("2")){
            helper.setText(R.id.adapter_type,"会员购买");
            helper.setText(R.id.adapter_money,"-"+item.getIntegral());
        }else{
            helper.setText(R.id.adapter_type,"商品兑换");
            helper.setText(R.id.adapter_money,"-"+item.getIntegral());
        }
        helper.setText(R.id.adapter_time,item.getCreate_time());

     }

}

