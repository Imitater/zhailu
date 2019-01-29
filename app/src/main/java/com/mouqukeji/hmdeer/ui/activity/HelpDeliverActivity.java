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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.DeliverPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpDeliverContract;
import com.mouqukeji.hmdeer.modle.activity.HelpDeliverModel;
import com.mouqukeji.hmdeer.presenter.activity.HelpDeliverPresenter;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DateUtils;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;
import com.mouqukeji.hmdeer.util.SpUtils;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpDeliverActivity extends BaseActivity<HelpDeliverPresenter, HelpDeliverModel> implements HelpDeliverContract.View, View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;
    public static HelpDeliverActivity instance = null;
    @BindView(R.id.helpdeliver_actionbar)
    MyActionBar helpdeliverActionbar;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.helpdeliver_order)
    Button helpdeliverOrder;
    @BindView(R.id.helpdeliver_empty_get)
    TextView helpdeliverEmptyGet;
    @BindView(R.id.helpdeliver_get_name)
    TextView helpdeliverGetName;
    @BindView(R.id.helpdeliver_get_number)
    TextView helpdeliverGetNumber;
    @BindView(R.id.helpdeliver_get_address)
    TextView helpdeliverGetAddress;
    @BindView(R.id.helpdeliver_full_get)
    LinearLayout helpdeliverFullGet;
    @BindView(R.id.helpdeliver_get_commonly)
    LinearLayout helpdeliverGetCommonly;
    @BindView(R.id.helpdeliver_empty_put)
    TextView helpdeliverEmptyPut;
    @BindView(R.id.helpdeliver_put_name)
    TextView helpdeliverPutName;
    @BindView(R.id.helpdeliver_put_number)
    TextView helpdeliverPutNumber;
    @BindView(R.id.helpdeliver_put_address)
    TextView helpdeliverPutAddress;
    @BindView(R.id.helpdeliver_full_put)
    LinearLayout helpdeliverFullPut;
    @BindView(R.id.helpdeliver_put_commonly)
    LinearLayout helpdeliverPutCommonly;
    @BindView(R.id.helpdeliver_items)
    LinearLayout helpdeliverItems;
    @BindView(R.id.helpdeliver_sex)
    LinearLayout helpdeliverSex;
    @BindView(R.id.helpdeliver_time)
    LinearLayout helpdeliverTime;
    @BindView(R.id.helpdeliver_youhui)
    LinearLayout helpdeliverYouhui;
    @BindView(R.id.helpdeliver_beizhu)
    EditText helpdeliverBeizhu;
    @BindView(R.id.helpdeliver_money)
    TextView helpdeliverMoney;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.helpdeliver_items_tv)
    TextView helpdeliverItemsTv;
    @BindView(R.id.helpdeliver_sex_tv)
    TextView helpdeliverSexTv;
    @BindView(R.id.helpdeliver_time_tv)
    TextView helpdeliverTimeTv;
    @BindView(R.id.helpdeliver_Preferential)
    TextView helpdeliverPreferential;
    private ItemsCategoryBean categoryBean;
    private String baseKg;
    private String baseKm;
    private String price;
    private String kg_price;
    private String km_price;
    private double money;
    private String[] category;
    private String cate_id;
    private String city;
    private String spUserID;
    private String getEndId = "-1";
    private String getName;
    private String getNumber;
    private String getLocation;
    private String getLocationInfo;
    private String putEndId = "-1";
    private String putName;
    private String putNumber;
    private String putLocation;
    private String putLocationInfo;
    private String putAddressLat;
    private String putAddressLon;
    private String putIsDefaul;
    private String getIsDefaul;
    private String getAddressLon;
    private String getAddressLat;
    private String datatime;
    private int sexId = 0;
    private int preferntialCount;
    private int couponId;
    private float num=0;
    private String order_id;
    private String pay_type;
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
                        Toast.makeText(HelpDeliverActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        framelayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "4"), "pay").commit();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(HelpDeliverActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
        mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);//获取优惠列表
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helpdeliver;
    }

    @Override
    protected void setUpView() {
        instance=this;
        //设置title
        helpdeliverActionbar.setTitle("帮忙送");
        //设置点击事件监听
        initListener();
    }

    private void initListener() {
        helpdeliverGetCommonly.setOnClickListener(this);
        helpdeliverPutCommonly.setOnClickListener(this);
        helpdeliverFullGet.setOnClickListener(this);
        helpdeliverFullPut.setOnClickListener(this);
        helpdeliverEmptyGet.setOnClickListener(this);
        helpdeliverEmptyPut.setOnClickListener(this);
        helpdeliverItems.setOnClickListener(this);
        helpdeliverSex.setOnClickListener(this);
        helpdeliverTime.setOnClickListener(this);
        helpdeliverOrder.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {
        //设置标记
        instance = this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpdeliver_get_commonly://进入取件人地址列表
                startActivityForResult(new Intent(this, AddressListActivity.class), 71);
                break;
            case R.id.helpdeliver_put_commonly://进入收件人地址列表
                startActivityForResult(new Intent(this, AddressListActivity.class), 72);
                break;
            case R.id.helpdeliver_full_get://进入取件人修改界面
                Intent intent2 = new Intent(HelpDeliverActivity.this, AddressEditActivity.class);
                intent2.putExtra("userId", spUserID);
                intent2.putExtra("geteditId", getEndId);
                intent2.putExtra("editName", getName);
                intent2.putExtra("editNumber", getNumber);
                intent2.putExtra("editLocation", getLocation);
                intent2.putExtra("editLocationInfo", getLocationInfo);
                intent2.putExtra("editisDefaul", getIsDefaul);
                //传入 需要修改的参数
                startActivityForResult(intent2, 73);
                break;
            case R.id.helpdeliver_full_put://进入收件人修改界面
                Intent intent1 = new Intent(HelpDeliverActivity.this, AddressEditActivity.class);
                intent1.putExtra("userId", spUserID);
                intent1.putExtra("editId", putEndId);
                intent1.putExtra("editName", putName);
                intent1.putExtra("editNumber", putNumber);
                intent1.putExtra("editLocation", putLocation);
                intent1.putExtra("editLocationInfo", putLocationInfo);
                intent1.putExtra("editisDefaul", putIsDefaul);
                //传入 需要修改的参数
                startActivityForResult(intent1, 74);
                break;
            case R.id.helpdeliver_empty_get://首次输入取件地址
                startActivityForResult(new Intent(this, ReceiverActivity.class), 75);
                break;
            case R.id.helpdeliver_empty_put://首次输入收件地址
                startActivityForResult(new Intent(this, ReceiverActivity.class), 76);
                break;
            case R.id.helpdeliver_items://物品类型框
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_items, null);
                category = DialogUtils.showButtomItemsDialog(HelpDeliverActivity.this, inflate_items, true, true,
                        helpdeliverItemsTv, categoryBean, Integer.parseInt(baseKg), Double.parseDouble(kg_price), money, helpdeliverMoney);
                break;
            case R.id.helpdeliver_sex://性别框
                View inflate_sex = getLayoutInflater().inflate(R.layout.dialog_sex, null);
                sexId = DialogUtils.showButtomSexDialog(HelpDeliverActivity.this, inflate_sex, true, true, helpdeliverSexTv);
                break;
            case R.id.helpdeliver_time://时间框
                datatime = DialogUtils.timeDialog(HelpDeliverActivity.this, helpdeliverTimeTv, "立即配送");
                break;
            case R.id.helpdeliver_youhui:
                Intent intent3 = new Intent(HelpDeliverActivity.this, PreferentialListActivity.class);
                startActivityForResult(intent3, 77);
                break;
            case R.id.helpdeliver_order://下单按钮
                if (getEndId.equals("-1")) {
                    Toast.makeText(this, "请选择取件地址", Toast.LENGTH_SHORT).show();
                } else if (putEndId.equals("-1")) {
                    Toast.makeText(this, "请选择收件地址", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(category[0])) {
                    Toast.makeText(this, "请选择物品类型", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(category[1])) {
                    Toast.makeText(this, "请设置物品重量", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(datatime)) {
                    datatime = DateUtils.getData() + " " + DateUtils.getTime();//设置未选择默认时间
                } else {
                    //下单接口
                    //money保留2位小数
                    final DecimalFormat df = new DecimalFormat("#.00");
                    mMvpPresenter.deliverPlaceOrder(spUserID,cate_id,getEndId,putEndId,category[0], category[1],num+"",
                            couponId+"",category[2], df.format(Double.parseDouble(category[2])-num),sexId+"",datatime,helpdeliverBeizhu.getText().toString(),mMultipleStateView);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 71:
                if (!TextUtils.isEmpty(data.getStringExtra("id")) && !TextUtils.isEmpty(data.getStringExtra("name"))
                        && !TextUtils.isEmpty(data.getStringExtra("number")) && !TextUtils.isEmpty(data.getStringExtra("address"))
                        && !TextUtils.isEmpty(data.getStringExtra("detail")) && !TextUtils.isEmpty(data.getStringExtra("lat"))
                        && !TextUtils.isEmpty(data.getStringExtra("lng"))) {
                    getEndId = data.getStringExtra("id");
                    getName = data.getStringExtra("name");
                    getNumber = data.getStringExtra("number");
                    getLocation = data.getStringExtra("address");
                    getLocationInfo = data.getStringExtra("detail");
                    getAddressLat = data.getStringExtra("lat");
                    getAddressLon = data.getStringExtra("lng");
                    helpdeliverGetName.setText(getName);
                    helpdeliverGetNumber.setText(getNumber);//电话
                    helpdeliverGetAddress.setText(getLocation + getLocationInfo);//地址
                }
                break;
            case 72:
                if (!TextUtils.isEmpty(data.getStringExtra("id")) && !TextUtils.isEmpty(data.getStringExtra("name"))
                        && !TextUtils.isEmpty(data.getStringExtra("number")) && !TextUtils.isEmpty(data.getStringExtra("address"))
                        && !TextUtils.isEmpty(data.getStringExtra("detail")) && !TextUtils.isEmpty(data.getStringExtra("lat"))
                        && !TextUtils.isEmpty(data.getStringExtra("lng"))) {
                    putEndId = data.getStringExtra("id");
                    putName = data.getStringExtra("name");
                    putNumber = data.getStringExtra("number");
                    putLocation = data.getStringExtra("address");
                    putLocationInfo = data.getStringExtra("detail");
                    putAddressLat = data.getStringExtra("lat");
                    putAddressLon = data.getStringExtra("lng");
                    helpdeliverPutName.setText(putName);//姓名
                    helpdeliverPutNumber.setText(putNumber);//电话
                    helpdeliverPutAddress.setText(putLocation + putLocationInfo);//地址
                }
                break;
            case 73:
                getEndId = data.getStringExtra("afterId");
                getName = data.getStringExtra("afterName");
                getNumber = data.getStringExtra("afterNumber");
                getLocation = data.getStringExtra("afterLocation");
                getLocationInfo = data.getStringExtra("afterLocationInfo");
                helpdeliverGetName.setText(getName);
                helpdeliverGetNumber.setText(getNumber);//电话
                helpdeliverGetAddress.setText(getLocation + getLocationInfo);//地址
                break;
            case 74:
                putEndId = data.getStringExtra("afterId");
                putName = data.getStringExtra("afterName");
                putNumber = data.getStringExtra("afterNumber");
                putLocation = data.getStringExtra("afterLocation");
                putLocationInfo = data.getStringExtra("afterLocationInfo");
                helpdeliverPutName.setText(putName);//姓名
                helpdeliverPutNumber.setText(putNumber);//电话
                helpdeliverPutAddress.setText(putLocation + putLocationInfo);//地址
                break;
            case 75:
                mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                break;
            case 76:
                mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                break;
            case 77:
                //返回优惠劵 num
                num = data.getFloatExtra("num", 0);
                couponId = data.getIntExtra("couponId", 0);
                helpdeliverMoney.setText((Integer.parseInt(category[2]) - num) + "");
                break;
        }
    }


    private void setPay(int balance) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpDeliverActivity.this, inflate_pay, true, true);
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
        //money保留2位小数
        final DecimalFormat df = new DecimalFormat("#.00");
        //设置价钱
        payMoneyTv.setText(df.format(Double.parseDouble(category[2])-num));
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payWeiXing(order_id, spUserID, "1", df.format(Double.parseDouble(category[2])-num), mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payZhifubao(order_id, spUserID, "2", df.format(Double.parseDouble(category[2])-num), mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payYue(order_id, spUserID, "3", df.format(Double.parseDouble(category[2])-num), mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpDeliverActivity.this, ReChargeActivity.class);
                intent.putExtra("rechange_type","1");//设置充值标记
                startActivity(intent);
            }
        });
    }




    @Override
    public void deliverPlaceOrder(DeliverPlaceOrderBean bean) {
        order_id = bean.getOrder_id();
        mMvpPresenter.payYueInfo(spUserID, order_id,mMultipleStateView);
    }

    @Override
    public void getItemsCategory(ItemsCategoryBean bean) {
        helpdeliverEmptyGet.setEnabled(false);
        helpdeliverEmptyPut.setEnabled(false);
        helpdeliverEmptyGet.setVisibility(View.GONE);
        helpdeliverEmptyPut.setVisibility(View.GONE);
        helpdeliverFullGet.setVisibility(View.VISIBLE);//显示默认地址
        helpdeliverFullPut.setVisibility(View.VISIBLE);//显示默认地址
        categoryBean = bean;

        //是否默认
        getIsDefaul = "1";
        //设置取件默认id
        getEndId = bean.getDefault_address().getId();
        //设置取件默认姓名
        getName = bean.getDefault_address().getName();
        //设置取件默认手机号
        getNumber = bean.getDefault_address().getTelephone();
        //设置取件默认 地址
        getLocation = bean.getDefault_address().getAddress();
        //设置取件默认详细地址
        getLocationInfo = bean.getDefault_address().getDetail();
        //设置取件默认精度
        getAddressLat = bean.getDefault_address().getLat();
        //设置取件默认唯独
        getAddressLon = bean.getDefault_address().getLng();


        //是否默认
        putIsDefaul = "1";
        //设置收件id
        putEndId = bean.getDefault_address().getId();
        //设置收件默认姓名
        putName = bean.getDefault_address().getName();
        //设置收件默认手机号
        putNumber = bean.getDefault_address().getTelephone();
        //设置收件默认 地址
        putLocation = bean.getDefault_address().getAddress();
        //设置收件默认详细地址
        putLocationInfo = bean.getDefault_address().getDetail();
        //设置收件默认精度
        putAddressLat = bean.getDefault_address().getLat();
        //设置收件默认唯独
        putAddressLon = bean.getDefault_address().getLng();


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
        helpdeliverGetName.setText(getName);//姓名
        helpdeliverGetNumber.setText(getNumber);//电话
        helpdeliverGetAddress.setText(getLocation + getLocationInfo);//地址


        helpdeliverPutName.setText(putName);//姓名
        helpdeliverPutNumber.setText(putNumber);//电话
        helpdeliverPutAddress.setText(putLocation + putLocationInfo);//地址
    }

    //优惠劵
    @Override
    public void getPreferentialList(PreferentialBean bean) {
        preferntialCount = 0;
        if (bean.getCoupons().size() != 0) {
            for (int i = 0; i < bean.getCoupons().size(); i++) {
                if (bean.getCoupons().get(i).equals(cate_id)) {
                    preferntialCount++;
                }
            }
            if (preferntialCount != 0) {
                helpdeliverPreferential.setText("优惠劵 x" + preferntialCount + "张");
                helpdeliverYouhui.setOnClickListener(this);//设置点击事件
            } else {
                helpdeliverYouhui.setEnabled(false);//无法进入优惠劵列表
            }
        }
    }

    @Override
    public void isEmpty() {
        helpdeliverEmptyGet.setVisibility(View.VISIBLE);//显示输入地址
        helpdeliverEmptyPut.setVisibility(View.VISIBLE);//显示编辑地址
        helpdeliverFullGet.setVisibility(View.GONE);//隐藏默认地址
        helpdeliverFullPut.setVisibility(View.GONE);//隐藏默认地址
    }

    //余额
    @Override
    public void payYueInfo(PayYueBean bean) {
        //显示付款框
        setPay(bean.getBalance());
    }

    //微信支付
    @Override
    public void payWeiXing(WeixingPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.startWeChatPay(this, bean);//调取微信支付
    }

    //支付宝支付
    @Override
    public void payZhifubao(ZhiFuBoPayBean bean) {
        taskId = bean.getOrders().getTask_id();
        PaymentHelper.aliPay(this, bean.getPay().getPayInfo(), mHandler);//调取支付宝支付
    }

    //余额支付
    @Override
    public void payYue(YuEBean bean) {
        taskId = bean.getOrders().getTask_id();
        framelayout.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "4"), "pay").commit();
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            framelayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "4"), "pay").commit();
        }
    }

}
