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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps2d.model.LatLng;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.SendPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpSendContract;
import com.mouqukeji.hmdeer.modle.activity.HelpSendModel;
import com.mouqukeji.hmdeer.presenter.activity.HelpSendPresenter;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DateUtils;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.DinstaceUtils;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;
import com.mouqukeji.hmdeer.util.SpUtils;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpSendActivity extends BaseActivity<HelpSendPresenter, HelpSendModel> implements HelpSendContract.View, View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;
    public static HelpSendActivity instance = null;
    @BindView(R.id.helpsend_actionbar)
    MyActionBar helpsendActionbar;
    @BindView(R.id.helpsend_hint)
    TextView helpsendHint;
    @BindView(R.id.helpsend_next)
    ImageView helpsendNext;
    @BindView(R.id.helpsend_info)
    RelativeLayout helpsendInfo;
    @BindView(R.id.helpsend_address)
    LinearLayout helpsendAddress;
    @BindView(R.id.receiver_name)
    TextView receiverName;
    @BindView(R.id.receiver_number)
    TextView receiverNumber;
    @BindView(R.id.helptake_location)
    TextView helptakeLocation;
    @BindView(R.id.address_linearlayout)
    LinearLayout addressLinearlayout;
    @BindView(R.id.address_commonly)
    LinearLayout addressCommonly;
    @BindView(R.id.address_defaul)
    LinearLayout addressDefaul;
    @BindView(R.id.helpsend_item_tv)
    TextView helpsendItemTv;
    @BindView(R.id.helpsend_items)
    LinearLayout helpsendItems;
    @BindView(R.id.helpsend_location_tv)
    TextView helpsendLocationTv;
    @BindView(R.id.helpsend_location)
    LinearLayout helpsendLocation;
    @BindView(R.id.helpsend_pay_tv)
    TextView helpsendPayTv;
    @BindView(R.id.helpsend_pay)
    LinearLayout helpsendPay;
    @BindView(R.id.helpsend_time_tv)
    TextView helpsendTimeTv;
    @BindView(R.id.helpsend_time)
    LinearLayout helpsendTime;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.helpsend_order)
    Button helpsendOrder;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.send_order_name)
    EditText sendOrderName;
    @BindView(R.id.send_order_number)
    EditText sendOrderNumber;
    @BindView(R.id.send_order_address)
    TextView sendOrderAddress;
    @BindView(R.id.send_order_address_select)
    LinearLayout sendOrderAddressSelect;
    @BindView(R.id.send_order_address_info)
    EditText sendOrderAddressInfo;
    @BindView(R.id.send_order_beizhu)
    EditText sendOrderBeizhu;
    @BindView(R.id.send_order_money)
    TextView sendOrderMoney;
    private String[] category;
    private String spUserID;
    private String city;
    private String cate_id;
    private ItemsCategoryBean categoryBean;
    private String isDefaul;
    private String endId = "-1";
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
    private double money;
    private String datatime;
    private String[] strings;
    private String courierLon;
    private String courierLat;
    private int kmPrice;
    private String address;
    private int payWayId = 0;
    private String pay_type;
    private String order_id;
    private String taskId;
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
                        Toast.makeText(HelpSendActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        framelayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "3"), "pay").commit();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(HelpSendActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_helpsend;
    }

    @Override
    protected void setUpView() {
        //设置标记
        instance = this;
        //设置title
        helpsendActionbar.setTitle("帮忙寄");
        //点击事件监听
        initListener();
    }

    private void initListener() {
        helpsendInfo.setOnClickListener(this);
        addressCommonly.setOnClickListener(this);
        addressLinearlayout.setOnClickListener(this);
        helpsendItems.setOnClickListener(this);
        helpsendPay.setOnClickListener(this);
        helpsendTime.setOnClickListener(this);
        helpsendOrder.setOnClickListener(this);
        sendOrderAddressSelect.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpsend_info://寄件人信息 第一次进入页面 输入地址
                startActivityForResult(new Intent(this, ReceiverActivity.class), 45);
                break;
            case R.id.address_commonly://常用 进入地址列表
                startActivityForResult(new Intent(this, AddressListActivity.class), 51);
                break;
            case R.id.address_linearlayout://进入修改地址页面
                Intent intent2 = new Intent(HelpSendActivity.this, AddressEditActivity.class);
                intent2.putExtra("userId", spUserID);
                intent2.putExtra("editId", endId);
                intent2.putExtra("editName", name);
                intent2.putExtra("editNumber", number);
                intent2.putExtra("editLocation", location);
                intent2.putExtra("editLocationInfo", locationInfo);
                intent2.putExtra("editisDefaul", isDefaul);
                //传入 需要修改的参数
                startActivityForResult(intent2, 41);
                break;
            case R.id.helpsend_items://物品类型
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_items, null);
                category = DialogUtils.showButtomItemsDialog(HelpSendActivity.this, inflate_items, true, true,
                        helpsendItemTv, categoryBean, Integer.parseInt(baseKg), Double.parseDouble(kg_price), money, sendOrderMoney);
                break;
            case R.id.send_order_address_select://快递服务
                //进入地点查询页面
                startActivityForResult(new Intent(this, SelectAddressActivity.class), 43);
                break;
            case R.id.helpsend_pay://付款方式
                View inflate_payway = getLayoutInflater().inflate(R.layout.dialog_reciver, null);
                //获取选择项
                payWayId = DialogUtils.payWayDialog(HelpSendActivity.this, inflate_payway, true, true, helpsendPayTv);
                break;
            case R.id.helpsend_time://揽件时间
                //获取选择时间
                datatime = DialogUtils.timeDialog(HelpSendActivity.this, helpsendTimeTv, "立即取件");
                break;
            case R.id.helpsend_order://下单

                if (TextUtils.isEmpty(sendOrderName.getText().toString())) {
                    Toast.makeText(HelpSendActivity.this, "收件人姓名不能为空", Toast.LENGTH_SHORT).show();
                } else if (endId.equals("-1")) {
                    Toast.makeText(this, "请选择收货人地址", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(sendOrderNumber.getText().toString())) {
                    Toast.makeText(HelpSendActivity.this, "收件人号码不能为空", Toast.LENGTH_SHORT).show();
                } else if (sendOrderAddress.getText().toString().equals("请选择地区")) {
                    Toast.makeText(HelpSendActivity.this, "收件人地区不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(sendOrderAddressInfo.getText().toString())) {
                    Toast.makeText(HelpSendActivity.this, "收件人详细地址不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(category[0])) {
                    Toast.makeText(HelpSendActivity.this, "物品类型不能为空", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(category[1])) {
                    Toast.makeText(this, "请设置物品重量", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(helpsendLocationTv.getText().toString())) {
                    Toast.makeText(this, "请设置快递点", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(datatime)) {
                    datatime = DateUtils.getData() + " " + DateUtils.getTime();//设置未选择默认时间
                } else if (payWayId == 0) {
                    Toast.makeText(this, "请设置付款方式", Toast.LENGTH_SHORT).show();
                } else {
                    //下单接口
                    mMvpPresenter.sendPlaceOrder(spUserID, cate_id, endId, helpsendLocationTv.getText().toString(), category[0], category[1], "", "", category[2],category[2],
                            datatime,sendOrderBeizhu.getText().toString(),payWayId+"",
                            sendOrderName.getText().toString(),sendOrderNumber.getText().toString(),
                            sendOrderAddress.getText().toString(),sendOrderAddressInfo.getText().toString(),mMultipleStateView);
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 51:
                endId = data.getStringExtra("id");
                name = data.getStringExtra("name");
                number = data.getStringExtra("number");
                location = data.getStringExtra("address");
                locationInfo = data.getStringExtra("detail");
                addressLat = data.getStringExtra("lat");
                addressLon = data.getStringExtra("lng");
                receiverName.setText(name);//姓名
                receiverNumber.setText(number);//电话
                helptakeLocation.setText(location + locationInfo);//地址
                break;
            case 43:
                if (!TextUtils.isEmpty(data.getStringExtra("select_address"))) {
                    //获取地址
                    address = data.getStringExtra("select_address");
                    sendOrderAddress.setText(address);
                    //经度
                    courierLat = data.getStringExtra("select_point_lat");
                    //纬度
                    courierLon = data.getStringExtra("select_point_lon");
                }
                break;
            case 41:
                mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                break;
            case 45:
                mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                break;
        }
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    //pay dialog
    private void setPayDialog(int balance) {
            View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
            final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpSendActivity.this, inflate_pay, true, true);
            final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
            TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
            final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
            final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
            final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
            final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
            final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
            //判断余额是否为0
            if (balance != 0 && balance > money) {
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
            payMoneyTv.setText(category[2]);
            //点击支付
            dialogPayBt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dialogPayWeiXing.isChecked()) {
                        pay_type = "1";
                        mMvpPresenter.payWeiXing(order_id, spUserID, "1", category[2], mMultipleStateView);//微信支付接口
                    } else if (dialogPayZhiFuBao.isChecked()) {
                        pay_type = "2";
                        mMvpPresenter.payZhifubao(order_id, spUserID, "2", category[2], mMultipleStateView);//支付宝支付接口
                    } else {
                        pay_type = "3";
                        mMvpPresenter.payYue(order_id, spUserID, "3", category[2], mMultipleStateView);//余额支付接口
                    }
                    buttomDialogView.dismiss();
                }
            });
            //充值按钮
            dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HelpSendActivity.this, ReChargeActivity.class);
                    intent.putExtra("rechange_type","1");//设置充值标记
                    startActivity(intent);
                }
            });
    }

    @Override
    public void sendPlaceOrder(SendPlaceOrderBean bean) {
        //获取order_id
        order_id = bean.getOrder_id();
        mMvpPresenter.payYueInfo(spUserID, order_id, mMultipleStateView);//获取余额
    }

    @Override
    public void getItemsCategory(ItemsCategoryBean bean) {
        helpsendInfo.setEnabled(false);
        helpsendHint.setVisibility(View.GONE);//隐藏添加地址 iv
        helpsendNext.setVisibility(View.GONE);//隐藏添加地址 tv
        addressDefaul.setVisibility(View.VISIBLE);//显示默认地址
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

        receiverName.setText(name);//姓名
        receiverNumber.setText(number);//电话
        helptakeLocation.setText(location + locationInfo);//地址
    }

    @Override
    public void getPreferentialList(PreferentialBean bean) {

    }

    @Override
    public void isEmpty() {
        helpsendInfo.setEnabled(true);
        helpsendHint.setVisibility(View.VISIBLE);//显示添加地址 iv
        helpsendNext.setVisibility(View.VISIBLE);//显示添加地址 tv
        addressDefaul.setVisibility(View.GONE);//隐藏默认地址
    }

    @Override
    public void payYueInfo(PayYueBean bean) {
        setPayDialog(bean.getBalance());
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
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "3"), "pay").commit();
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            framelayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "3"), "pay").commit();
        }
    }

}
