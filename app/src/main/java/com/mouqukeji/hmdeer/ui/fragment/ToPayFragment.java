package com.mouqukeji.hmdeer.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.base.BaseLazyFragment;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.fragment.ToPayContract;
import com.mouqukeji.hmdeer.modle.fragment.ToPayModel;
import com.mouqukeji.hmdeer.presenter.fragment.ToPayPresenter;
import com.mouqukeji.hmdeer.ui.activity.BuyIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.BuyOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.PayCompleteActivity;
import com.mouqukeji.hmdeer.ui.activity.ReChargeActivity;
import com.mouqukeji.hmdeer.ui.activity.SendIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.SendOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalIngOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.adapter.AllorderRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.adapter.ToPayRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class ToPayFragment extends BaseLazyFragment<ToPayPresenter, ToPayModel> implements ToPayContract.View, View.OnClickListener {
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    Unbinder unbinder;
    @BindView(R.id.all_order_swiperefreshlayout)
    SwipeRefreshLayout allOrderSwiperefreshlayout;
    private int page = 1;
    private String spUserID;
    private ToPayRecyclerviewAdapter toPayRececleverAdapter;
    private int countPages;
    private List<AllOrderBean.TasksBean> tasks;
    private static final int SDK_PAY_FLAG = 1;
    private String pay_type = "1";
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getMContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getMContext(), PayCompleteActivity.class);
                        intent.putExtra("task_id", taskId);
                        intent.putExtra("cate_id", cate_id);
                        startActivity(intent);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getMContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String cate_id;
    private String taskId;
    private int selectId;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getProgressIndent(spUserID, "1", mMultipleStateView);
    }


    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_order;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {

    }


    private void initSwipeRefresh() {
        //设置下拉刷新
        if (allOrderSwiperefreshlayout != null) {
            allOrderSwiperefreshlayout.setColorSchemeResources(R.color.blue);
            allOrderSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    orderRecycler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mMvpPresenter.getProgressIndent(spUserID, "1", mMultipleStateView);
                            if (toPayRececleverAdapter != null) {
                                toPayRececleverAdapter.notifyDataSetChanged();
                                toPayRececleverAdapter.setUpFetching(false);
                                toPayRececleverAdapter.setUpFetchEnable(false);
                            }
                            if (allOrderSwiperefreshlayout != null)
                                allOrderSwiperefreshlayout.setRefreshing(false);
                        }
                    }, 2000);
                }
            });
        }
    }

    @Override
    public void getProgressIndent(AllOrderBean bean) {
        page++;
        countPages = bean.getPages();
        if (llNoOrder != null && orderRecycler != null) {
            llNoOrder.setVisibility(View.GONE);
            orderRecycler.setVisibility(View.VISIBLE);
            tasks = bean.getTasks();
            //设置recyclerview
            setRecyclerview();
        }
        //设置上拉加载
        setUpLoad();
        //设置下拉刷新
        initSwipeRefresh();
    }

    @Override
    public void getIndentNext(AllOrderBean bean) {
        page++;
        //成功获取更多数据
        toPayRececleverAdapter.addData(bean.getTasks());
        toPayRececleverAdapter.loadMoreComplete();
    }

    private void setUpLoad() {
        toPayRececleverAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        toPayRececleverAdapter.isFirstOnly(false);//设置动画一直使用
        if (tasks.size() >= 10) {
            toPayRececleverAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();//加载更多
                }
            }, orderRecycler);
        }
    }

    private void setLoadMore() {
        orderRecycler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (page > countPages) {
                    //数据全部加载完毕
                    toPayRececleverAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.getIndentNext(spUserID, "1", page + "", mMultipleStateView);
                }
            }
        }, 1500);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (allOrderSwiperefreshlayout != null) {
            allOrderSwiperefreshlayout.setRefreshing(false);
        }
    }

    @Override
    public void getEmpty() {
        if (llNoOrder != null && orderRecycler != null) {
            llNoOrder.setVisibility(View.VISIBLE);
            orderRecycler.setVisibility(View.GONE);
        }
        initSwipeRefresh();
    }

    @Override
    public void cancelOrder(CancelOrderBean bean) {
        //发送消息
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_I, 1);
        post(eventMessage);
    }

    private void refreshData() {
        page = 1;
        mMvpPresenter.getProgressIndent(spUserID, "1", mMultipleStateView);
        toPayRececleverAdapter.notifyDataSetChanged();
    }

    //微信支付
    @Override
    public void payWeiXing(final WeixingPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.startWeChatPay(getActivity(), bean);//调取微信支付
    }

    //支付宝支付
    @Override
    public void payZhifubao(ZhiFuBoPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.aliPay(getActivity(), bean.getPay().getPayInfo(), mHandler);//调取支付宝支付
    }

    //余额支付
    @Override
    public void payYue(YuEBean bean) {
        taskId = bean.getOrders().getTask_id();
        cate_id = bean.getOrders().getCate_id();
        Intent intent = new Intent(getMContext(), PayCompleteActivity.class);
        intent.putExtra("task_id", taskId);
        intent.putExtra("cate_id", cate_id);
        startActivity(intent);
    }

    @Override
    public void payYueInfo(PayYueBean bean) {
        //付款框
        setPay(toPayRececleverAdapter.getData().get(selectId).getPay_fee(), bean.getBalance(), toPayRececleverAdapter.getData().get(selectId).getOrder_id());
    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置 多布局type
        //设置Adapter
        toPayRececleverAdapter = new ToPayRecyclerviewAdapter(R.layout.adapter_generation_payment_layout, getActivity(), tasks, mMvpPresenter, mMultipleStateView, spUserID);
        orderRecycler.setAdapter(toPayRececleverAdapter);
        toPayRececleverAdapter.disableLoadMoreIfNotFullPage(orderRecycler);
        toPayRececleverAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //去支付
                mMvpPresenter.payYueInfo(spUserID, toPayRececleverAdapter.getData().get(i).getOrder_id(), mMultipleStateView);//获取余额
                selectId = i;
            }
        });
        //item点击事件
        toPayRececleverAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                //订单详情
                if (toPayRececleverAdapter.getData().get(i).getCate_id().equals("11")) {
                    //帮忙取
                    Intent intent = new Intent(getMContext(), TakeIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toPayRececleverAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toPayRececleverAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 201);
                } else if (toPayRececleverAdapter.getData().get(i).getCate_id().equals("12")) {
                    //帮忙买
                    Intent intent = new Intent(getMContext(), BuyIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toPayRececleverAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toPayRececleverAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 301);
                } else if (toPayRececleverAdapter.getData().get(i).getCate_id().equals("13")) {
                    //帮忙寄
                    Intent intent = new Intent(getMContext(), SendIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toPayRececleverAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toPayRececleverAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 401);
                } else if (toPayRececleverAdapter.getData().get(i).getCate_id().equals("14")) {
                    //帮忙送
                    Intent intent = new Intent(getMContext(), DeliverIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toPayRececleverAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toPayRececleverAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 501);
                } else {
                    //万能帮
                    Intent intent = new Intent(getMContext(), UniversalIngOrderInfoActivity.class);
                    intent.putExtra("taskId", toPayRececleverAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", toPayRececleverAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 601);
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //刷新列表
            refreshData();
        }
    }


    private void setPay(final String pay_fee, final double balance, final String order_id) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(getActivity(), inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        //判断余额是否为0
        if (balance != 0 && balance > Double.parseDouble(pay_fee)) {
            pay_type = "1";
            dialogPayYue.setChecked(true);
            dialogPayRecharge.setVisibility(View.GONE);//隐藏充值按钮
            dialogPayYue.setVisibility(View.VISIBLE);//显示余额支付选项
        } else {
            dialogPayRecharge.setVisibility(View.VISIBLE);//显示充值按钮
            dialogPayYue.setVisibility(View.GONE);//隐藏余额支付按钮
            pay_type = "2";
            dialogPayWeiXing.setChecked(true);
        }
        dialogPayWalletMoney.setText("可用余额" + balance + "元");
        //money保留2位小数
        final DecimalFormat df = new DecimalFormat("#.00");
        //设置价钱
        payMoneyTv.setText(pay_fee);
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payWeiXing(order_id, spUserID, "1", pay_fee, mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payZhifubao(order_id, spUserID, "2", pay_fee, mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payYue(order_id, spUserID, "3", pay_fee, mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMContext(), ReChargeActivity.class);
                intent.putExtra("rechange_type", "1");//设置充值标记
                startActivity(intent);
            }
        });
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_I) {
                //取消订单成功 刷新列表
                refreshData();
            } else if (event.getCode()==EventCode.EVENT_C){
                Intent intent = new Intent(getMContext(), PayCompleteActivity.class);
                intent.putExtra("task_id", taskId);
                intent.putExtra("cate_id", cate_id);
                startActivity(intent);
            }else if (event.getCode()==EventCode.EVENT_J){
                //支付成功 刷新列表
                refreshData();
            }else if (event.getCode()==EventCode.EVENT_L){
                //已下单 刷新列表
                refreshData();
            }
        }
    }

    @Override
    protected void lazyLoad() {
        if (!mIsprepared || !mIsVisible || mHasLoadedOnce) {
            return;
        }
        mHasLoadedOnce = true;
        page = 1;
        mMvpPresenter.getProgressIndent(spUserID, "1", mMultipleStateView);
    }

}
