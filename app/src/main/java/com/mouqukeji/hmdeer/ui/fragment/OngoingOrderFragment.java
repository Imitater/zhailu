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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseFragment;
import com.mouqukeji.hmdeer.base.BaseLazyFragment;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.fragment.OngingOrderContract;
import com.mouqukeji.hmdeer.modle.fragment.OngoingOrderModel;
import com.mouqukeji.hmdeer.presenter.fragment.OngoingOrderPresenter;
import com.mouqukeji.hmdeer.ui.activity.BuyOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.DeliverOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.PayBuyCompleteActivity;
import com.mouqukeji.hmdeer.ui.activity.ReChargeActivity;
import com.mouqukeji.hmdeer.ui.activity.SendOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.TakeOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.activity.UniversalOrderInfoActivity;
import com.mouqukeji.hmdeer.ui.adapter.OngoingOrderRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.CenterDialogView;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class OngoingOrderFragment extends BaseLazyFragment<OngoingOrderPresenter, OngoingOrderModel> implements OngingOrderContract.View, View.OnClickListener {
    @BindView(R.id.order_recycler)
    RecyclerView orderRecycler;
    @BindView(R.id.ll_no_order)
    LinearLayout llNoOrder;
    Unbinder unbinder;
    @BindView(R.id.all_order_swiperefreshlayout)
    SwipeRefreshLayout allOrderSwiperefreshlayout;
    private int page = 1;
    private String spUserID;
    private OngoingOrderRecyclerviewAdapter ongoingOrderRecyclerviewAdapter;
    private int countPages;
    private List<AllOrderBean.TasksBean> tasks;
    private String task_id;
    private String cate_id;
    private String makeup_id;
    private String pay_type;
    private static final int SDK_PAY_FLAG = 1;
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
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), PayBuyCompleteActivity.class);
                        intent.putExtra("task_id", task_id);
                        intent.putExtra("cate_id", cate_id);
                        startActivityForResult(intent, 6);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String makeup_fee;

    @Override
    protected void initViewAndEvents() {
        page = 1;
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getProgressIndent(spUserID, "3", mMultipleStateView);
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
    protected void lazyLoad() {
        if (!mIsprepared || !mIsVisible || mHasLoadedOnce) {
            return;
        }
        mHasLoadedOnce = true;
        refreshData();
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
                            mMvpPresenter.getProgressIndent(spUserID, "3", mMultipleStateView);
                            if (ongoingOrderRecyclerviewAdapter != null) {
                                ongoingOrderRecyclerviewAdapter.notifyDataSetChanged();
                                ongoingOrderRecyclerviewAdapter.setUpFetching(false);
                                ongoingOrderRecyclerviewAdapter.setUpFetchEnable(false);
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
        ongoingOrderRecyclerviewAdapter.addData(bean.getTasks());
        ongoingOrderRecyclerviewAdapter.loadMoreComplete();
    }

    private void setUpLoad() {
        ongoingOrderRecyclerviewAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);//设置recyclerview 动画
        ongoingOrderRecyclerviewAdapter.isFirstOnly(false);//设置动画一直使用
        if (tasks.size() >= 10) {
            ongoingOrderRecyclerviewAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    setLoadMore();
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
                    ongoingOrderRecyclerviewAdapter.loadMoreEnd();
                } else {
                    mMvpPresenter.getIndentNext(spUserID, "3", page + "", mMultipleStateView);
                }
            }
        }, 1500);
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
    public void finishOrder(FinishOrderBean bean) {
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_M, 1);
        post(eventMessage);
    }

    private void setPay(double balance) {
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
        if (balance != 0 && balance > Double.parseDouble(makeup_fee)) {
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
        //设置价钱
        payMoneyTv.setText(makeup_fee);
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payAgainWeixin(makeup_id, spUserID, "1", makeup_fee, mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payAgainZhiFuBao(makeup_id, spUserID, "2", makeup_fee, mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payAgainYue(makeup_id, spUserID, "3", makeup_fee, mMultipleStateView);//余额支付接口
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


    //余额
    @Override
    public void payYueInfo(PayYueBean bean) {
        //显示付款
        setPay(bean.getBalance());
    }

    @Override
    public void payAgainWeixin(WeixingPayBean bean) {
        PaymentHelper.startWeChatPay(getActivity(), bean);//调取微信支付
    }

    @Override
    public void payAgainZhiFuBao(ZhiFuBoPayBean bean) {
        PaymentHelper.aliPay(getActivity(), bean.getPay().getPayInfo(), mHandler);//调取支付宝支付
    }

    @Override
    public void payAgainYue(YuEBean bean) {
        Intent intent = new Intent(getMContext(), PayBuyCompleteActivity.class);
        intent.putExtra("task_id", task_id);
        intent.putExtra("cate_id", cate_id);
        startActivityForResult(intent, 6);
    }

    private void refreshData() {
        page = 1;
        mMvpPresenter.getProgressIndent(spUserID, "3", mMultipleStateView);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (allOrderSwiperefreshlayout != null) {
            allOrderSwiperefreshlayout.setRefreshing(false);
        }
    }

    private void setRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());
        orderRecycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        ongoingOrderRecyclerviewAdapter = new OngoingOrderRecyclerviewAdapter(R.layout.adapter_ongoing_layout, tasks);
        orderRecycler.setAdapter(ongoingOrderRecyclerviewAdapter);
        ongoingOrderRecyclerviewAdapter.disableLoadMoreIfNotFullPage(orderRecycler);

        //确认收货
        ongoingOrderRecyclerviewAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {


            @Override
            public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, final int i) {
                if (Integer.parseInt(ongoingOrderRecyclerviewAdapter.getData().get(i).getMakeup_id()) != 0) {
                    //二次支付  弹框
                    View inflate_check = getLayoutInflater().inflate(R.layout.dialog_buy_check, null);
                    final CenterDialogView centerDialogView = DialogUtils.checkDialog(getActivity(), getMContext(), inflate_check, true, true, ongoingOrderRecyclerviewAdapter.getData().get(i).getPicture());
                    TextView dialogCheckRightBt = centerDialogView.findViewById(R.id.dialog_check_right_bt);
                    ImageView dialogIv = centerDialogView.findViewById(R.id.dialog_iv);
                    TextView dialogcheckMoney = centerDialogView.findViewById(R.id.dialog_check_money);
                    TextView dialogCheckLeftBt = centerDialogView.findViewById(R.id.dialog_check_left_bt);
                    task_id = ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id();
                    cate_id = ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id();
                    makeup_id = ongoingOrderRecyclerviewAdapter.getData().get(i).getMakeup_id();
                    makeup_fee = ongoingOrderRecyclerviewAdapter.getData().get(i).getMakeup_fee();
                    Glide.with(getMContext()).load(ongoingOrderRecyclerviewAdapter.getData().get(i).getPicture()).into(dialogIv);
                    dialogcheckMoney.setText(ongoingOrderRecyclerviewAdapter.getData().get(i).getMakeup_fee());
                    //关闭弹窗
                    dialogCheckLeftBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            centerDialogView.dismiss();
                        }
                    });
                    //支付按钮点击
                    dialogCheckRightBt.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            centerDialogView.dismiss();
                            mMvpPresenter.payYueInfo(spUserID, ongoingOrderRecyclerviewAdapter.getData().get(i).getOrder_id(), mMultipleStateView);//获取余额
                        }
                    });
                } else {
                    //确认收货
                    View inflate_items = getLayoutInflater().inflate(R.layout.dialog_finish, null);
                    DialogUtils.finishOrderDialog(getMContext(), inflate_items, true,
                            true, mMvpPresenter, mMultipleStateView,ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id(),spUserID);
                }
            }
        });

        //item 进入订单详情
        ongoingOrderRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("11")) {
                    //进行中
                    Intent intent = new Intent(getMContext(), TakeOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 202);
                } else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("12")) {
                    //进行中
                    Intent intent = new Intent(getMContext(), BuyOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 302);
                } else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("13")) {
                    //进行中
                    Intent intent = new Intent(getMContext(), SendOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 402);
                } else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("14")) {
                    Intent intent = new Intent(getMContext(), DeliverOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 502);
                } else if (ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id().equals("15")) {
                    //进行中
                    Intent intent = new Intent(getMContext(), UniversalOrderInfoActivity.class);
                    intent.putExtra("taskId", ongoingOrderRecyclerviewAdapter.getData().get(i).getTask_id());
                    intent.putExtra("cateId", ongoingOrderRecyclerviewAdapter.getData().get(i).getCate_id());
                    startActivityForResult(intent, 602);
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

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_J) {
                //删除订单成功 刷新列表
                refreshData();
            } else if (event.getCode() == EventCode.EVENT_L) {
                //已下单 刷新列表
                refreshData();
            } else if (event.getCode() == EventCode.EVENT_M) {
                //确认取件 刷新列表
                refreshData();
            } else if (event.getCode() == EventCode.EVENT_C) {
                Intent intent = new Intent(getContext(), PayBuyCompleteActivity.class);
                intent.putExtra("task_id", task_id);
                intent.putExtra("cate_id", cate_id);
                startActivityForResult(intent, 6);
            }
        }
    }
}
