package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.PreferentialBean;

import java.util.List;

public class PreferentialRecyclerviewAdapter extends BaseQuickAdapter<PreferentialBean.CouponsBean, BaseViewHolder> {

    public PreferentialRecyclerviewAdapter(int layoutResId, @Nullable List<PreferentialBean.CouponsBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, PreferentialBean.CouponsBean item) {
        //type
        if (getData().get(helper.getLayoutPosition()).getType().equals("1")){
            helper.setText(R.id.adpater_preferential_type,"金额抵扣");
        }else{
            helper.setText(R.id.adpater_preferential_type,"折扣抵扣");
        }
        helper.setText(R.id.adpater_preferential_type,getData().get(helper.getLayoutPosition()).getCate_name());
        helper.setText(R.id.adpater_preferential_start_time,getData().get(helper.getLayoutPosition()).getStart_time());
        helper.setText(R.id.adapter_preferential_end_time,getData().get(helper.getLayoutPosition()).getEnd_time());
        helper.setText(R.id.adapter_preferential_money,getData().get(helper.getLayoutPosition()).getNum());
        //判断 type 是否 使用  失效  未使用
        if (getData().get(helper.getLayoutPosition()).getIs_use().equals("0")){//未使用
            helper.setVisible(R.id.adapter_preferential_tv,true);
            helper.setVisible(R.id.adapter_preferential_unuse,false);
            helper.setVisible(R.id.adapter_preferential_used,false);
        }else if (getData().get(helper.getLayoutPosition()).getIs_use().equals("1")){//使用
            helper.setVisible(R.id.adapter_preferential_tv,false);
            helper.setVisible(R.id.adapter_preferential_unuse,false);
            helper.setVisible(R.id.adapter_preferential_used,true);
        }else{//失效
            helper.setVisible(R.id.adapter_preferential_tv,true);
            helper.setVisible(R.id.adapter_preferential_unuse,true);
            helper.setVisible(R.id.adapter_preferential_used,false);
        }
    }

}
