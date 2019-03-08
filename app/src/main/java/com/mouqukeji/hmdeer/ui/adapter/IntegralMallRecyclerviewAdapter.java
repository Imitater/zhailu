package com.mouqukeji.hmdeer.ui.adapter;


import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.IntegralPageBean;
import com.mouqukeji.hmdeer.ui.activity.IntegralMallActivity;

import java.util.ArrayList;
import java.util.List;

public class IntegralMallRecyclerviewAdapter extends BaseQuickAdapter<IntegralPageBean.GoodsBean, BaseViewHolder> {



    public IntegralMallRecyclerviewAdapter(int layoutResId, List<IntegralPageBean.GoodsBean> data) {
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, IntegralPageBean.GoodsBean item) {
        ImageView imageView=helper.getView(R.id.adapter_iv);
        Glide.with(mContext).load(item.getPicture()).into(imageView);
        helper.setText(R.id.adapter_title,item.getTitle());
        if (item.getPrice().equals("0.00")){
            helper.setText(R.id.adapter_contact,"￥0押金+"+item.getIntegral()+"积分兑换");
        }else{
            helper.setText(R.id.adapter_contact, "￥"+item.getPrice()+"押金+"+item.getIntegral()+"积分兑换");
        }
        LinearLayout adapterItem=helper.getView(R.id.adapter_item);
        if (helper.getLayoutPosition()==3){
            adapterItem.setEnabled(false);
        }else if (helper.getLayoutPosition()==4){
            adapterItem.setEnabled(false);
        }else if (helper.getLayoutPosition()==5){
            adapterItem.setEnabled(false);
        }
    }

}

