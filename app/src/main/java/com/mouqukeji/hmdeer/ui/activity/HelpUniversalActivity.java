package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.UnviersalPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpUniversalContract;
import com.mouqukeji.hmdeer.modle.activity.HelpUniversalModel;
import com.mouqukeji.hmdeer.presenter.activity.HelpUniversalPresenter;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DateUtils;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;
import com.mouqukeji.hmdeer.util.SpUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class HelpUniversalActivity extends BaseActivity<HelpUniversalPresenter, HelpUniversalModel> implements HelpUniversalContract.View, View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;

    public static HelpUniversalActivity instance = null;
    @BindView(R.id.helpuniversal_actionbar)
    MyActionBar helpuniversalActionbar;
    @BindView(R.id.helpuniversal_et)
    EditText helpuniversalEt;
    @BindView(R.id.helpuniversal_first)
    TextView helpuniversalFirst;
    @BindView(R.id.helpuniversal_name)
    TextView helpuniversalName;
    @BindView(R.id.helpuniversal_number)
    TextView helpuniversalNumber;
    @BindView(R.id.helpuniversal_address)
    TextView helpuniversalAddress;
    @BindView(R.id.helpuniversal_unfirst)
    LinearLayout helpuniversalUnfirst;
    @BindView(R.id.helpuniversal_commonly)
    LinearLayout helpuniversalCommonly;
    @BindView(R.id.helpuniversal_time)
    TextView helpuniversalTime;
    @BindView(R.id.helpuniversal_category)
    LinearLayout helpuniversalCategory;
    @BindView(R.id.helpuniversal_money)
    TextView helpuniversalMoney;
    @BindView(R.id.helpuniversal_order)
    Button helpuniversalOrder;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.helpuniversal_long)
    TextView helpuniversalLong;
    @BindView(R.id.helpuniversal_price)
    TextView helpuniversalPrice;
    @BindView(R.id.helpuniversal_pass_long)
    TextView helpuniversalPassLong;
    @BindView(R.id.helpuniversal_pass_weight)
    TextView helpuniversalPassWeight;
    @BindView(R.id.helpuniversal_huiyuan)
    ImageView helpuniversalHuiyuan;
    private String spUserID;
    private String city;
    private String cate_id;
    private ItemsCategoryBean categoryBean;
    private String isDefaul;
    private String endId;
    private String name;
    private String number;
    private String location;
    private String locationInfo;
    private String addressLat;
    private String addressLon;
    private String baseKg;
    private String baseKm;
    private String price;
    private String kg_price;
    private String km_price;
    private Double money;
    private String commodityPrices = "0";
    ;
    private String pay_type;
    private String order_id;
    private String taskId;
    private String countPrice = "0";
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
                        Toast.makeText(HelpUniversalActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        framelayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "5"), "pay").commit();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(HelpUniversalActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private ItemsCategoryBean itemsCategoryBean = null;
    private String vip_num;

    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        cate_id = intent.getStringExtra("cate_id");
        city = (String) SpUtils.get(this, "city", "");
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpuniversal;
    }

    @Override
    protected void setUpView() {
        helpuniversalActionbar.setTitle("万能帮");
        instance = this;
        initListener();
    }

    private void initListener() {
        helpuniversalFirst.setOnClickListener(this);
        helpuniversalCategory.setOnClickListener(this);
        helpuniversalUnfirst.setOnClickListener(this);
        helpuniversalOrder.setOnClickListener(this);
        helpuniversalCommonly.setOnClickListener(this);
        helpuniversalHuiyuan.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpuniversal_commonly://进入地址列表
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra("type", "0");
                startActivityForResult(intent, 81);
                break;
            case R.id.helpuniversal_category://设置物品
                //获取选择时间
                DialogUtils.timeDialog(HelpUniversalActivity.this, helpuniversalTime, "立即帮忙");
                break;
            case R.id.helpuniversal_unfirst://修改地址
                Intent intent2 = new Intent(HelpUniversalActivity.this, AddressEditActivity.class);
                intent2.putExtra("userId", spUserID);
                intent2.putExtra("editId", endId);
                intent2.putExtra("editName", name);
                intent2.putExtra("editNumber", number);
                intent2.putExtra("editLocation", location);
                intent2.putExtra("editLocationInfo", locationInfo);
                intent2.putExtra("editisDefaul", isDefaul);
                //传入 需要修改的参数
                startActivityForResult(intent2, 82);
                break;
            case R.id.helpuniversal_order://下单
                if (itemsCategoryBean == null) {
                    Toast.makeText(this, "请设置取件地址", Toast.LENGTH_SHORT).show();
                } else {
                    if (TextUtils.isEmpty(helpuniversalEt.getText().toString())) {
                        Toast.makeText(HelpUniversalActivity.this, "请输入帮助需求", Toast.LENGTH_SHORT).show();
                    } else {
                        if (helpuniversalTime.getText().toString().equals("立即帮忙")) {
                            String datatime = DateUtils.getData() + " " + DateUtils.getTime();//设置未选择默认时间
                            //下单
                            mMvpPresenter.universalPlaceOrder(spUserID, cate_id, endId, helpuniversalEt.getText().toString(),
                                    "0", "0", "0", datatime, mMultipleStateView);
                        } else {
                            //下单
                            mMvpPresenter.universalPlaceOrder(spUserID, cate_id, endId, helpuniversalEt.getText().toString(),
                                    "0", "0", "0", helpuniversalTime.getText().toString(), mMultipleStateView);
                        }

                    }
                }
                break;
            case R.id.helpuniversal_first://首次进入设置页面
                startActivityForResult(new Intent(this, ReceiverActivity.class), 83);
                break;
            case R.id.helpuniversal_huiyuan:
                Intent intent1 = new Intent(HelpUniversalActivity.this, MemberCenterActivity.class);
                startActivity(intent1);
                break;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 81:
                if (!TextUtils.isEmpty(data.getStringExtra("id")) && !TextUtils.isEmpty(data.getStringExtra("name"))
                        && !TextUtils.isEmpty(data.getStringExtra("number")) && !TextUtils.isEmpty(data.getStringExtra("address")) &&
                        !TextUtils.isEmpty(data.getStringExtra("detail")) && !TextUtils.isEmpty(data.getStringExtra("lat")) &&
                        !TextUtils.isEmpty(data.getStringExtra("lng"))) {
                    endId = data.getStringExtra("id");
                    name = data.getStringExtra("name");
                    number = data.getStringExtra("number");
                    location = data.getStringExtra("address");
                    locationInfo = data.getStringExtra("detail");
                    addressLat = data.getStringExtra("lat");
                    addressLon = data.getStringExtra("lng");
                    helpuniversalName.setText(name);//姓名
                    helpuniversalNumber.setText(number);//电话
                    helpuniversalAddress.setText(location + locationInfo);//地址
                } else {
                    mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                }
                break;
            case 82:
                if (TextUtils.isEmpty(helpuniversalName.getText().toString())) {
                    mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                }
                break;
            case 83:
                mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                break;
        }
    }

    @Override
    public void universalPlaceOrder(UnviersalPlaceOrderBean bean) {
        framelayout.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(bean.getOrder().getTask_id(), bean.getOrder().getCate_id(), "5"), "pay").commit();
        //发送消息 已下单 刷新列表
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_L, 1);
        post(eventMessage);
    }

    private void setPay(double balance) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpUniversalActivity.this, inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        //判断余额是否为0
        if (balance >= money) {
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
        //设置价钱
        payMoneyTv.setText(countPrice);
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payWeiXing(order_id, spUserID, "1", countPrice, mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payZhifubao(order_id, spUserID, "2", countPrice, mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payYue(order_id, spUserID, "3", countPrice, mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpUniversalActivity.this, ReChargeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void isEmpty() {
        helpuniversalUnfirst.setEnabled(true);
        helpuniversalUnfirst.setVisibility(View.GONE);
        helpuniversalFirst.setVisibility(View.VISIBLE);
    }

    @Override
    public void getItemsCategory(ItemsCategoryBean bean) {
        itemsCategoryBean = bean;
        helpuniversalFirst.setEnabled(false);
        helpuniversalFirst.setVisibility(View.GONE);//隐藏添加地址 iv
        helpuniversalUnfirst.setVisibility(View.VISIBLE);//显示默认地址
        categoryBean = bean;
        //是否默认
        isDefaul = "1";
        //id
        endId = bean.getDefault_address().getId();
        //默认姓名
        name = bean.getDefault_address().getName();
        //默认手机号
        number = bean.getDefault_address().getTelephone();
        //默认 地址
        location = bean.getDefault_address().getAddress();
        //默认详细地址
        locationInfo = bean.getDefault_address().getDetail();
        //默认精度
        addressLat = bean.getDefault_address().getLat();
        //默认唯独
        addressLon = bean.getDefault_address().getLng();
        //起步重量
        baseKg = bean.getCategory().getBasekg();
        //获取起步距离
        baseKm = bean.getCategory().getBasekm();
        //起步价
        price = bean.getCategory().getPrice();
        //超公里价格
        kg_price = bean.getCategory().getKg_price();
        //超重价格
        km_price = bean.getCategory().getKm_price();
        money = Double.parseDouble(price);
        helpuniversalPrice.setText(money + "元");
        helpuniversalName.setText(name);//姓名
        helpuniversalNumber.setText(number);//电话
        helpuniversalAddress.setText(location + locationInfo);//地址
        vip_num = bean.getVip_num();
        if (Integer.parseInt(bean.getVip_num()) > 0) {
            money = 0.0;
            helpuniversalHuiyuan.setVisibility(View.GONE);
        } else {
            helpuniversalHuiyuan.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void payWeiXing(WeixingPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.startWeChatPay(this, bean);//调取微信支付
    }

    @Override
    public void payZhifubao(ZhiFuBoPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.aliPay(this, bean.getPay().getPayInfo(), mHandler);//调取支付宝支付
    }

    @Override
    public void payYue(YuEBean bean) {
        taskId = bean.getOrders().getTask_id();
        framelayout.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "5"), "pay").commit();
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_C) {
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "5"), "pay").commit();
            }
        }
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }
}
