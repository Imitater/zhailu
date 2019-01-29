package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;

import java.util.List;

public class ItemsRecyclerviewAdapter extends BaseQuickAdapter<ItemsCategoryBean.TypeBean, BaseViewHolder> {
    private int mSelectedPos=-1;
    private String category;
    private String gtype_id;

    public ItemsRecyclerviewAdapter(int layoutResId, @Nullable List<ItemsCategoryBean.TypeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, ItemsCategoryBean.TypeBean item) {
        helper.setText(R.id.adpater_items_button, getData().get(helper.getLayoutPosition()).getType_name());
        if (mSelectedPos==helper.getLayoutPosition()) {
            helper.setBackgroundRes(R.id.adpater_items_button, R.drawable.dialog_button_line_press);
        }else{
            helper.setBackgroundRes(R.id.adpater_items_button, R.drawable.dialog_button_line_normal);
        }
         helper.setOnClickListener(R.id.adpater_items_button, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedPos!=helper.getLayoutPosition()){//当前选中的position和上次选中不是同一个position 执行
                    helper.setBackgroundRes(R.id.adpater_items_button,R.drawable.dialog_button_line_press);
                    if(mSelectedPos!=-1){//判断是否有效 -1是初始值 即无效 第二个参数是Object 随便传个int 这里只是起个标志位
                        notifyItemChanged(mSelectedPos);
                    }
                    mSelectedPos=helper.getLayoutPosition();
                }
                gtype_id = getData().get(helper.getLayoutPosition()).getGtype_id();
                category = getData().get(helper.getLayoutPosition()).getType_name();
            }
        });
    }
    public String getGtypeId(){
        return gtype_id;
    }
    public String getCategoryName(){
        return category;
    }

}
