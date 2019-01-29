package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.IndexBean;

import java.util.List;

public class TakeCodeRecyclerviewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TakeCodeRecyclerviewAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.orderinfo_code,getData().get(helper.getLayoutPosition()));
    }

}
