package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;

 import com.chad.library.adapter.base.BaseItemDraggableAdapter;
 import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.HistoryBean;

import java.util.List;

public class SelectHistoryAdapter extends BaseItemDraggableAdapter<HistoryBean, BaseViewHolder> {

    public SelectHistoryAdapter(int layoutResId, @Nullable List<HistoryBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, HistoryBean item) {
        helper.setText(R.id.adapter_select_address_tv,item.getTitle());
        helper.setText(R.id.adapter_select_address_contact_tv,item.getContent());
        helper.addOnClickListener(R.id.adapter_select_address_item);
    }

}
