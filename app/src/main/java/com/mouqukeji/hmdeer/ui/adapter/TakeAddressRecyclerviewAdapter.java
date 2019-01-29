package com.mouqukeji.hmdeer.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.AddressListBean;

import java.util.List;

public class TakeAddressRecyclerviewAdapter extends BaseQuickAdapter<AddressListBean.AddressBean, BaseViewHolder> {

      private int mSelectedPos;

    public TakeAddressRecyclerviewAdapter(int adapter_address_layout, @Nullable List<AddressListBean.AddressBean> data, int select) {
        super(adapter_address_layout, data);
          this.mSelectedPos=select;
     }

    @Override
    protected void convert(final BaseViewHolder helper, AddressListBean.AddressBean item) {
        helper.setText(R.id.address_name, getData().get(helper.getLayoutPosition()).getName());
        helper.setText(R.id.address_number, getData().get(helper.getLayoutPosition()).getTelephone());
        helper.setText(R.id.address_location, getData().get(helper.getLayoutPosition()).getAddress() + getData().get(helper.getLayoutPosition()).getDetail());

        helper.setChecked(R.id.address_check, mSelectedPos==helper.getLayoutPosition());
        helper.setOnClickListener(R.id.address_check, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mSelectedPos!=helper.getLayoutPosition()){//当前选中的position和上次选中不是同一个position 执行
                    helper.setChecked(R.id.address_check,true);
                    if(mSelectedPos!=-1){//判断是否有效 -1是初始值 即无效 第二个参数是Object 随便传个int 这里只是起个标志位
                        notifyItemChanged(mSelectedPos);
                    }
                    mSelectedPos=helper.getLayoutPosition();
                }
            }
        });
    }

    public int getSelectedPos() {
        return mSelectedPos;
    }
}
