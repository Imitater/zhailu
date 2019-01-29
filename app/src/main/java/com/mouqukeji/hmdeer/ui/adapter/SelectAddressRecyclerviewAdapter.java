package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;

import com.amap.api.services.help.Tip;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;

import java.util.List;

public class SelectAddressRecyclerviewAdapter extends BaseQuickAdapter<Tip, BaseViewHolder> {

    public SelectAddressRecyclerviewAdapter(int layoutResId, @Nullable List<Tip> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper,Tip item) {
        helper.setText(R.id.adapter_select_address_tv,getData().get(helper.getLayoutPosition()).getName());
        helper.addOnClickListener(R.id.adapter_select_address_item);
    }

}
