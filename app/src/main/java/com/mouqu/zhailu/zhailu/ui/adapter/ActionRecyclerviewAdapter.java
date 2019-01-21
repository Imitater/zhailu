package com.mouqu.zhailu.zhailu.ui.adapter;


import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.bean.IndexBean;

import java.util.List;

public class ActionRecyclerviewAdapter extends BaseQuickAdapter<IndexBean.NoticesBean, BaseViewHolder> {

    public ActionRecyclerviewAdapter(int layoutResId, @Nullable List<IndexBean.NoticesBean> data) {
        super(layoutResId, data);
     }

    @Override
    protected void convert(BaseViewHolder helper, IndexBean.NoticesBean item) {
        Glide.with(mContext).load(getData().get(helper.getLayoutPosition()).getThumb()).into((ImageView) helper.getView(R.id.action_iv));
        helper.setText(R.id.action_tv, getData().get( helper.getLayoutPosition()).getDescription());
    }

}
