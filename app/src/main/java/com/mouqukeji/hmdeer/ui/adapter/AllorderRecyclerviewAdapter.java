package com.mouqukeji.hmdeer.ui.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.presenter.fragment.AllOrderPresenter;
import com.mouqukeji.hmdeer.ui.activity.EvaluationActivity;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.MultipleItem;
import com.mouqukeji.hmdeer.util.PayResult;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

public class AllorderRecyclerviewAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    private final AllOrderPresenter mMvpPresenter;
    private final String userId;
    private final MultipleStatusView mMultipleStatusView;
    private final Activity activity;

    public AllorderRecyclerviewAdapter(Activity activity, List list, AllOrderPresenter mMvpPresenter, MultipleStatusView mMultipleStateView, String spUserID) {
        super(list);
        addItemType(MultipleItem.Generation_Payment, R.layout.adapter_generation_payment_layout);
        addItemType(MultipleItem.Generation_Order, R.layout.adapter_wait_order);
        addItemType(MultipleItem.Ongoing, R.layout.adapter_ongoing_layout);
        addItemType(MultipleItem.Complete, R.layout.adapter_to_evaluate_layout);
        addItemType(MultipleItem.To_Evaluate, R.layout.adapter_complete_layout);
        addItemType(MultipleItem.Cancelled, R.layout.adapter_cancelled_layout);
        addItemType(MultipleItem.HaveRefund, R.layout.adapter_cancelled_layout);
        addItemType(MultipleItem.Sending, R.layout.adapter_ongoing_layout);
        addItemType(MultipleItem.Sended, R.layout.adapter_ongoing_layout);
        this.mMvpPresenter=mMvpPresenter;
        this.userId=spUserID;
        this.mMultipleStatusView=mMultipleStateView;
        this.activity =activity;
     }

    @Override
    protected void convert(final BaseViewHolder helper, final MultipleItem multipleItem) {
        switch (helper.getItemViewType()) {
            case 1://待支付
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                //取消订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //显示取消订单框
                        View inflate_items = activity.getLayoutInflater().inflate(R.layout.dialog_cancell, null);
                        DialogUtils.cancellDialog(activity,inflate_items,true,true,mMvpPresenter,mMultipleStatusView,userId,multipleItem.getBean().getTask_id());
                    }
                });
                //去支付
                helper.addOnClickListener(R.id.adapter_to_pay);

                break;
            case 2://待接单
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")) {
                    helper.setText(R.id.adapter_pay, "未支付");
                } else {
                    helper.setText(R.id.adapter_pay, "已支付");
                }
                //取消订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View inflate_items = activity.getLayoutInflater().inflate(R.layout.dialog_cancell, null);
                        DialogUtils.cancellDialog(mContext,inflate_items,true,true,mMvpPresenter,mMultipleStatusView,userId,multipleItem.getBean().getTask_id());
                    }
                });

                break;
            case 3://进行中
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")) {
                    helper.setText(R.id.adapter_pay, "未支付");
                } else {
                    helper.setText(R.id.adapter_pay, "已支付");
                }
                //确认收货
                Button dialogDismiss=helper.getView(R.id.adapter_dismiss);
                dialogDismiss.setVisibility(View.GONE);
                Button dialogUndismiss=helper.getView(R.id.adapter_undismiss);
                dialogUndismiss.setVisibility(View.VISIBLE);
                break;
            case 4://待评价
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")) {
                    helper.setText(R.id.adapter_pay, "未支付");
                } else {
                    helper.setText(R.id.adapter_pay, "已支付");
                }
                //去评价
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, EvaluationActivity.class);
                        intent.putExtra("order_id",getData().get(helper.getLayoutPosition()).getBean().getOrder_id());
                        intent.putExtra("cate_id",getData().get(helper.getLayoutPosition()).getBean().getCate_id());
                        activity.startActivityForResult(intent,701);
                    }
                });
                break;
            case 5://已完成
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                //删除订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View inflate_items = activity.getLayoutInflater().inflate(R.layout.dialog_delete, null);
                        DialogUtils.deleteDialog(mContext,inflate_items,true,true,mMvpPresenter,mMultipleStatusView,getData().get(helper.getLayoutPosition()).getBean().getTask_id());
                    }
                });


                break;
            case 6://已取消
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")) {
                    helper.setText(R.id.adapter_pay, "未支付");
                } else {
                    helper.setText(R.id.adapter_pay, "已支付");
                }
                //删除订单
                helper.setOnClickListener(R.id.adapter_cancell, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View inflate_items = activity.getLayoutInflater().inflate(R.layout.dialog_delete, null);
                        DialogUtils.deleteDialog(mContext,inflate_items,true,true,mMvpPresenter,mMultipleStatusView,getData().get(helper.getLayoutPosition()).getBean().getTask_id());
                    }
                });

                break;
            case 7:
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")) {
                    helper.setText(R.id.adapter_pay, "未支付");
                } else {
                    helper.setText(R.id.adapter_pay, "已支付");
                }
                //删除订单
                helper.setOnClickListener(R.id.adapter_cancell, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View inflate_items = activity.getLayoutInflater().inflate(R.layout.dialog_delete, null);
                        DialogUtils.deleteDialog(mContext,inflate_items,true,true,mMvpPresenter,mMultipleStatusView,getData().get(helper.getLayoutPosition()).getBean().getTask_id());
                    }
                });
                break;
            case 8:
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")) {
                    helper.setText(R.id.adapter_pay, "未支付");
                } else {
                    helper.setText(R.id.adapter_pay, "已支付");
                }
                //确认收货
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mMvpPresenter.finishOrder(getData().get(helper.getLayoutPosition()).getBean().getTask_id(),userId,mMultipleStatusView);
                    }
                });
                break;
            case 9:
                setType(helper);
                helper.setText(R.id.adapter_server_type, getData().get(helper.getLayoutPosition()).getBean().getCate_name());
                helper.setText(R.id.adapter_money, getData().get(helper.getLayoutPosition()).getBean().getPay_fee());
                helper.setText(R.id.adapter_time, getData().get(helper.getLayoutPosition()).getBean().getDelivery_time());
                if (getData().get(helper.getLayoutPosition()).getBean().getExpress_pay_type().equals("0")) {
                    helper.setText(R.id.adapter_pay, "未支付");
                } else {
                    helper.setText(R.id.adapter_pay, "已支付");
                }
                //删除订单
                helper.setOnClickListener(R.id.adapter_dismiss, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        View inflate_items = activity.getLayoutInflater().inflate(R.layout.dialog_delete, null);
                        DialogUtils.deleteDialog(mContext,inflate_items,true,true,mMvpPresenter,mMultipleStatusView,getData().get(helper.getLayoutPosition()).getBean().getTask_id());
                    }
                });
                break;
        }
    }

    private void setType(BaseViewHolder helper) {
        helper.setText(R.id.adapter_code, getData().get(helper.getLayoutPosition()).getBean().getOrder_sn());
        if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("1")) {
            helper.setText(R.id.adapter_type, "待付款");
        } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("2")) {
            helper.setText(R.id.adapter_type, "待接单");
        } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("3")) {
            helper.setText(R.id.adapter_type, "已接单");
        } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("4")) {
            helper.setText(R.id.adapter_type, "待评价");
        } else if (getData().get(helper.getLayoutPosition()).getBean().equals("5")) {
            helper.setText(R.id.adapter_type, "已完成");
        } else if (getData().get(helper.getLayoutPosition()).getBean().equals("6")) {
            helper.setText(R.id.adapter_type, "已取消");
        } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("7")) {
            helper.setText(R.id.adapter_type, "已取消");
        } else if (getData().get(helper.getLayoutPosition()).getBean().getProgress().equals("8")) {
            helper.setText(R.id.adapter_type, "送货中");
        } else {
            helper.setText(R.id.adapter_type, "已完成");
        }
    }
}
