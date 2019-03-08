package com.mouqukeji.hmdeer.ui.adapter;


import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.presenter.fragment.ToPayPresenter;
import com.mouqukeji.hmdeer.ui.fragment.ToPayFragment;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;
import com.mouqukeji.hmdeer.util.DialogUtils;

import java.util.List;

public class ToPayRecyclerviewAdapter extends BaseQuickAdapter<AllOrderBean.TasksBean, BaseViewHolder> {

    private final ToPayPresenter mMvpPresenter;
    private final MultipleStatusView mMultipleStateView;
    private final String id;
    private final Activity activity;

    public ToPayRecyclerviewAdapter(int layoutResId, Activity activity, @Nullable List<AllOrderBean.TasksBean> data, ToPayPresenter mMvpPresenter, MultipleStatusView mMultipleStateView, String spUserID) {
        super(layoutResId, data);
        this.mMvpPresenter=mMvpPresenter;
        this.mMultipleStateView=mMultipleStateView;
        this.id=spUserID;
        this.activity=activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AllOrderBean.TasksBean item) {
        helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getOrder_sn());
        helper.setText(R.id.adapter_type, "待付款");
        helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getCate_name());
        helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getMakeup_fee());
        helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getDelivery_time());
        helper.setText(R.id.adapter_pay, "未支付");
        helper.setText(R.id.adapter_money,item.getPay_fee());
        //取消订单
        helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate_items = activity.getLayoutInflater().inflate(R.layout.dialog_cancell, null);
                DialogUtils.cancellDialog(activity,inflate_items,true,true,mMvpPresenter,mMultipleStateView,id,item.getTask_id());
            }
        });
        helper.addOnClickListener(R.id.adapter_to_pay);
    }

}
