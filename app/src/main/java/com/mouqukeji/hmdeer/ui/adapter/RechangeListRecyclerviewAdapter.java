package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.IndexBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;

import java.util.List;

public class RechangeListRecyclerviewAdapter extends BaseQuickAdapter<RechangeListBean.BalanceBean.RechargesBean, BaseViewHolder> {

    public RechangeListRecyclerviewAdapter(int layoutResId, @Nullable List<RechangeListBean.BalanceBean.RechargesBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, RechangeListBean.BalanceBean.RechargesBean item) {
        helper.setText(R.id.time,getData().get(helper.getLayoutPosition()-1).getCreate_time()+"");
        if (getData().get(helper.getLayoutPosition()-1).getPay_type().equals("1")){
            helper.setText(R.id.way,"微信");
        }else if (getData().get(helper.getLayoutPosition()-1).getPay_type().equals("2")){
            helper.setText(R.id.way,"支付宝");
        }else{
            helper.setText(R.id.way,"银行卡");
        }
        helper.setText(R.id.money,getData().get(helper.getLayoutPosition()-1).getMoney()+"");
    }

}
