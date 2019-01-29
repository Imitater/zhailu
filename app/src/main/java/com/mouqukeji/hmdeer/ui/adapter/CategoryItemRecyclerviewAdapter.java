package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.IndexBean;

import java.util.List;

public class CategoryItemRecyclerviewAdapter extends BaseQuickAdapter<IndexBean.CategoriesBean, BaseViewHolder> {

    public CategoryItemRecyclerviewAdapter(int layoutResId, @Nullable List<IndexBean.CategoriesBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, IndexBean.CategoriesBean item) {
         helper.setText(R.id.adapter_items_tv, getData().get( helper.getLayoutPosition()).getCate_name());
         Glide.with(mContext).load(getData().get(helper.getLayoutPosition()).getCate_photo()).into((ImageView) helper.getView(R.id.adapter_items_iv));

    }

}
