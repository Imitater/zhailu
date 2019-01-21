package com.mouqu.zhailu.zhailu.ui.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqu.zhailu.zhailu.R;

import java.util.List;

public class BuyItemsRecyclerviewAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    public BuyItemsRecyclerviewAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {
        helper.setText(R.id.adapter_items_tv, getData().get(helper.getLayoutPosition()).toString());
    }

}

