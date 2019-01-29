package com.mouqukeji.hmdeer.ui.adapter;


import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;

import java.util.List;

public class BuyAddressRecyclerviewAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {

    private int mSelectedPos=0;//保存当前选中的position
    public BuyAddressRecyclerviewAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, Object item) {
        helper.setChecked(R.id.address_check,mSelectedPos==helper.getLayoutPosition());
        helper.setOnClickListener(R.id.address_check, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedPos!=helper.getLayoutPosition()){//当前选中的position和上次选中不是同一个position 执行
                    helper.setChecked(R.id.address_check,true);
                    if(mSelectedPos!=-1){//判断是否有效 -1是初始值 即无效 第二个参数是Object 随便传个int 这里只是起个标志位
                        notifyItemChanged(mSelectedPos,0);
                    }
                    mSelectedPos=helper.getLayoutPosition();
                }
            }
        });
    }
    public int getSelectedPos(){
        return mSelectedPos;
    }
}
