package com.mouqukeji.hmdeer.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.LocationBean;

import java.util.List;

public class SelectLocationEtRecyclerviewAdapter extends BaseQuickAdapter<LocationBean, BaseViewHolder> {
    public SelectLocationEtRecyclerviewAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocationBean item) {
        helper.setText(R.id.adapter_select_address_tv,getData().get(helper.getLayoutPosition()).getTitle());
        helper.setText(R.id.adapter_select_address_contact_tv,getData().get(helper.getLayoutPosition()).getContent());
        helper.addOnClickListener(R.id.adapter_select_address_item);
    }

}
