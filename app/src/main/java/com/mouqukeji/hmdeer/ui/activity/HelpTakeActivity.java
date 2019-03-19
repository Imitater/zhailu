package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.TextureView;
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
import com.mouqukeji.hmdeer.bean.MapTitleBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpTakeContract;
import com.mouqukeji.hmdeer.modle.activity.HelpTakeModel;
import com.mouqukeji.hmdeer.presenter.activity.HelpTakePresenter;
import com.mouqukeji.hmdeer.ui.adapter.CodeRecyclerviewAdapter;
import com.mouqukeji.hmdeer.ui.fragment.PayCompleteFragment;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DateUtils;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.DinstaceUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;
import com.mouqukeji.hmdeer.util.SpUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class HelpTakeActivity extends BaseActivity<HelpTakePresenter, HelpTakeModel> implements HelpTakeContract.View, View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;
    public static HelpTakeActivity instance = null;
    @BindView(R.id.action_back)
    MyActionBar actionBack;
    @BindView(R.id.helptake_hint)
    TextView helptakeHint;
    @BindView(R.id.helptake_next)
    ImageView helptakeNext;
    @BindView(R.id.helptake_info)
    RelativeLayout helptakeInfo;
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
    @BindView(R.id.helptake_list)
    RecyclerView helptakeList;
    @BindView(R.id.helptake_address_tv)
    TextView helptakeAddressTv;
    @BindView(R.id.helptake_address_et)
    EditText helptakeAddressEt;
    @BindView(R.id.helptake_express)
    ImageView helptakeExpress;
    @BindView(R.id.helptake_receiver)
    LinearLayout helptakeReceiver;
    @BindView(R.id.helptake_item_tv)
    TextView helptakeItemTv;
    @BindView(R.id.helptake_category)
    ImageView helptakeCategory;
    @BindView(R.id.helptake_items)
    RelativeLayout helptakeItems;
    @BindView(R.id.helptake_time_tv)
    TextView helptakeTimeTv;
    @BindView(R.id.helptake_send)
    LinearLayout helptakeSend;
    @BindView(R.id.helptake_preferential_tv)
    TextView helptakePreferentialTv;
    @BindView(R.id.helptake_preferential)
    LinearLayout helptakePreferential;
    @BindView(R.id.helptake_remarks)
    EditText helptakeRemarks;
    @BindView(R.id.helptake_long)
    TextView helptakeLong;
    @BindView(R.id.helptake_price)
    TextView helptakePrice;
    @BindView(R.id.helptake_pass_long)
    TextView helptakePassLong;
    @BindView(R.id.helptake_pass_weight)
    TextView helptakePassWeight;
    @BindView(R.id.helptake_huiyuan)
    ImageView helptakeHuiyuan;
    @BindView(R.id.helptake_money)
    TextView helptakeMoney;
    @BindView(R.id.helptake_order)
    Button helptakeOrder;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.helptake_pass_long_tv)
    TextView helptakePassLongTv;
    @BindView(R.id.helptake_pass_weight_tv)
    TextView helptakePassWeightTv;

    private List codeList = new ArrayList();
    private String spUserID;
    private String cate_id;
    private ItemsCategoryBean categoryBean;
    private String[] strings;
    private String[] category = null;
    private String endId = "-1";
    private CodeRecyclerviewAdapter codeRecyclerviewAdapter;
    private String address;
    private int preferntialCount = 0;
    private String locationInfo;
    private String location;
    private String number;
    private String name;
    private String isDefaul;
    private String city;
    private String courierLon;
    private String courierLat;
    private String addressLon = "";
    private String addressLat = "";
    private String baseKg;
    private String baseKm;
    private String price;
    private Double kg_price;
    private Double km_price;
    private int kmPrice = 0;
    private double money;
    private int num = 0;
    private String couponId = "";
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
                        Toast.makeText(HelpTakeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        framelayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "1"), "pay").commit();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(HelpTakeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private String order_id;
    private String taskId;
    private ItemsCategoryBean itemsCategoryBean;
    private boolean haveDefaul;
    private Double kgPrice;
    private double noNum;

    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        cate_id = intent.getStringExtra("cate_id");
        city = (String) SpUtils.getString("city", this);
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
        mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);//获取优惠列表
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_helptake;
    }

    @Override
    protected void setUpView() {
        //设置标记
        instance = this;
        //设置title
        actionBack.setTitle("帮我取");
        //设置recycleview
        initRecyclerview();
        //按钮监听
        initListener();
    }

    private void initRecyclerview() {
        //设置Recyclerview 列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        helptakeList.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        codeRecyclerviewAdapter = new CodeRecyclerviewAdapter(R.layout.adapter_code_layout, codeList);
        helptakeList.setAdapter(codeRecyclerviewAdapter);
    }

    private void initListener() {
        helptakeExpress.setOnClickListener(this);
        helptakeHuiyuan.setOnClickListener(this);
        helptakeInfo.setOnClickListener(this);
        addressCommonly.setOnClickListener(this);
        helptakeReceiver.setOnClickListener(this);
        helptakeSend.setOnClickListener(this);
        helptakeItems.setOnClickListener(this);
        helptakeOrder.setOnClickListener(this);
        helptakeHuiyuan.setOnClickListener(this);
        addressLinearlayout.setOnClickListener(this);
        helptakeAddressEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                helptakeAddressEt.setSelection(helptakeAddressEt.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helptake_info://添加收件人信息
                startActivityForResult(new Intent(this, ReceiverActivity.class), 15);
                break;
            case R.id.address_commonly://地址列表
                Intent intent = new Intent(this, AddressListActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.helptake_express:
                //进入地点查询页面
                startActivityForResult(new Intent(this, SelectCourierActivity.class), 100);
                break;
            case R.id.helptake_receiver://快遞點
                //进入地点查询页面
                startActivityForResult(new Intent(this, SelectCourierActivity.class), 13);
                break;
            case R.id.helptake_send://配送时间
                //获取选择时间
                DialogUtils.timeDialog(HelpTakeActivity.this, helptakeTimeTv, "立即取件");
                break;
            case R.id.helptake_items://物品类型
                if (haveDefaul) {
                    View inflate_items = getLayoutInflater().inflate(R.layout.dialog_items, null);
                    category = DialogUtils.showCategoryDialog(HelpTakeActivity.this, inflate_items, true, true,
                            helptakeItemTv, categoryBean, Integer.parseInt(baseKg), kg_price, money, kmPrice, helptakeMoney, num,
                            helptakePassWeight, helptakePassWeightTv);
                } else {
                    Toast.makeText(HelpTakeActivity.this, "请填写收件人地址", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.helptake_order://下单
                //获取取货码
                List<String> list = codeRecyclerviewAdapter.getList();
                strings = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    strings[i] = list.get(i);
                }
                if (!haveDefaul) {
                    Toast.makeText(this, "请填写收件人地址", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(this, "请输入取件快递点", Toast.LENGTH_SHORT).show();
                } else if (helptakeItemTv.getText().toString().equals("请选择物品类型")) {
                    Toast.makeText(this, "请选择物品类型", Toast.LENGTH_SHORT).show();
                } else {
                    if (helptakeTimeTv.getText().toString().equals("立即取件")) {
                        //设置当前时间
                        String data = DateUtils.getData() + " " + DateUtils.getTime();
                        //下单接口
                        mMvpPresenter.placeOrder(spUserID, cate_id, endId, strings, address, category[0], category[1],
                                num + "", couponId + "",
                                (money + kmPrice + Double.parseDouble(category[2])) + "",
                                helptakeMoney.getText().toString(),
                                "",
                                data, helptakeRemarks.getText().toString(), mMultipleStateView);
                    } else {
                        //下单接口
                        mMvpPresenter.placeOrder(spUserID, cate_id, endId, strings, address, category[0], category[1],
                                num + "", couponId + "",
                                (money + kmPrice + Double.parseDouble(category[2])) + "",
                                helptakeMoney.getText().toString(),
                                "",
                                helptakeTimeTv.getText().toString(), helptakeRemarks.getText().toString(), mMultipleStateView);
                    }
                }
                break;
            case R.id.address_linearlayout://修改地址
                Intent intent2 = new Intent(HelpTakeActivity.this, AddressEditActivity.class);
                intent2.putExtra("userId", spUserID);
                intent2.putExtra("editId", endId);
                intent2.putExtra("editName", name);
                intent2.putExtra("editNumber", number);
                intent2.putExtra("editLocation", location);
                intent2.putExtra("editLocationInfo", locationInfo);
                intent2.putExtra("editisDefaul", isDefaul);
                //传入 需要修改的参数
                startActivityForResult(intent2, 11);
                break;
            case R.id.helptake_preferential://进入优惠劵列表
                if (!haveDefaul) {
                    Toast.makeText(HelpTakeActivity.this, "请填写收件人地址", Toast.LENGTH_SHORT).show();
                } else if (helptakeItemTv.getText().toString().equals("请选择物品类型")) {
                    Toast.makeText(HelpTakeActivity.this, "请选择物品类型", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(HelpTakeActivity.this, "请选择快递点", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent3 = new Intent(HelpTakeActivity.this, PreferentialSelectListActivity.class);
                    intent3.putExtra("cate_id", cate_id);
                    startActivityForResult(intent3, 2);
                }
                break;
            case R.id.helptake_huiyuan:
                //会员卡界面
                Intent intent1 = new Intent(HelpTakeActivity.this, MemberCenterActivity.class);
                startActivity(intent1);
                break;

        }

    }

    private void setPay(double balance) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpTakeActivity.this, inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        payMoneyTv.setText(helptakeMoney.getText().toString());
        //判断余额是否为0
        if (balance != 0 && balance >= Double.parseDouble(helptakeMoney.getText().toString())) {
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
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payWeiXing(order_id, spUserID, "1", helptakeMoney.getText().toString(), mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payZhifubao(order_id, spUserID, "2", helptakeMoney.getText().toString(), mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payYue(order_id, spUserID, "3", helptakeMoney.getText().toString(), mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpTakeActivity.this, ReChargeActivity.class);
                intent.putExtra("rechange_type", "1");//设置充值标记
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
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
                        receiverName.setText(name);//姓名
                        receiverNumber.setText(number);//电话
                        helptakeLocation.setText(location + locationInfo);//地址
                        //计算路程
                        if (!TextUtils.isEmpty(courierLat) && !TextUtils.isEmpty(courierLon)) {
                            calculationDistance();
                        }
                    } else {
                        mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                    }
                    break;
                case 2:
                    //返回优惠劵
                    if (num == 0) {
                        //初次进入优惠券
                        String moneyTv = helptakeMoney.getText().toString();
                        //无优惠劵 设置最终money
                        noNum = Double.parseDouble(moneyTv);
                    }
                    num = Integer.parseInt(data.getStringExtra("num"));
                    couponId = data.getStringExtra("couponId");
                    if (num != 0) {
                        helptakeMoney.setText((noNum - num) + "");
                        helptakePreferentialTv.setText("￥-" + num);
                        helptakePreferentialTv.setTextColor(getResources().getColor(R.color.black));
                    }
                    break;
                case 11:
                    if (TextUtils.isEmpty(receiverName.getText().toString())) {
                        mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                        mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);//获取优惠列表
                    }
                    break;
                case 15:
                    mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                    mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);//获取优惠列表
                    break;

            }
        }
    }

    @Override
    public void placeOrder(PlaceOrderBean bean) {
        if (Double.parseDouble(helptakeMoney.getText().toString()) < 1) {
            framelayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(bean.getOrder().getTask_id(), bean.getOrder().getCate_id(), "1"), "pay").commit();
        } else {
            //获取order_id
            order_id = bean.getOrder_id();
            mMvpPresenter.payYueInfo(spUserID, order_id, mMultipleStateView);//获取余额
        }


        //发送消息 已下单 刷新列表
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_L, 1);
        post(eventMessage);
    }


    @Override
    public void getItemsCategory(ItemsCategoryBean bean) {
        itemsCategoryBean = bean;
        helptakeInfo.setEnabled(false);
        helptakeNext.setVisibility(View.GONE);//隐藏添加地址 iv
        helptakeHint.setVisibility(View.GONE);//隐藏添加地址 tv
        addressDefaul.setVisibility(View.VISIBLE);//显示默认地址
        categoryBean = bean;
        isDefaul = "1";//是否默认
        endId = bean.getDefault_address().getId();//id
        name = bean.getDefault_address().getName();//默认姓名
        number = bean.getDefault_address().getTelephone();//默认手机号
        location = bean.getDefault_address().getAddress();//默认 地址
        locationInfo = bean.getDefault_address().getDetail();//默认详细地址
        addressLat = bean.getDefault_address().getLat();//默认精度
        addressLon = bean.getDefault_address().getLng();//默认唯独
        baseKg = bean.getCategory().getBasekg(); //起步重量
        baseKm = bean.getCategory().getBasekm();  //获取起步距离
        price = bean.getCategory().getPrice();  //起步价
        //超公里价格
        kg_price = Double.parseDouble(bean.getCategory().getKg_price());
        //超重价格
        km_price = Double.parseDouble(bean.getCategory().getKm_price());
        money = Double.parseDouble(price);
        receiverName.setText(name);//姓名
        receiverNumber.setText(number);//电话
        helptakeLocation.setText(location + locationInfo);//地址
        //物品类型 可点击
        haveDefaul = true;
        helptakePrice.setText(money + "元");
        String vip_num = bean.getVip_num();
        if (Integer.parseInt(vip_num) > 0) {
            money = 0;
            helptakeHuiyuan.setVisibility(View.GONE);
        } else {
            helptakeHuiyuan.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(courierLat) && !TextUtils.isEmpty(courierLon)) {
            calculationDistance();
        }
    }

    private void calculationDistance() {
        //计算路程
        double distance = DinstaceUtils.getDistance(new LatLng(Double.parseDouble(courierLat), Double.parseDouble(courierLon)),
                new LatLng(Double.parseDouble(addressLat), Double.parseDouble(addressLon)));
        //超路程  计价
        if (distance > Integer.parseInt(baseKm)) {

            if (helptakeItemTv.getText().toString().equals("请选择物品类型")) {//判断是否存在kmprice
                double v = distance - Integer.parseInt(baseKm);
                if (v < 1) {
                    kmPrice = 1;
                    helptakePassLong.setText(kmPrice + "元");
                    helptakePassLongTv.setText("（超过1公里）");
                } else {
                    kmPrice = (int) ((distance - Integer.parseInt(baseKm)) * km_price) + 1;
                    helptakePassLong.setText(kmPrice + "元");
                    DecimalFormat df = new DecimalFormat("#.00");
                    helptakePassLongTv.setText("（超过" + df.format(v) + "公里）");
                }
                helptakeMoney.setText((money + kmPrice - num) + "");//显示设置快递点后的价格变化
                DecimalFormat df = new DecimalFormat("#.00");
                helptakeLong.setText(df.format(distance) + "公里");//订单里程
                helptakePassWeightTv.setText("（超过0kg)");
                helptakePassWeight.setText("0元");
            } else {
                double v = distance - Integer.parseInt(baseKm);
                if (v < 1) {
                    kmPrice = 1;
                    helptakePassLong.setText(kmPrice + "元");
                    helptakePassLongTv.setText("（1公里）");
                } else {
                    kmPrice = (int) ((distance - Integer.parseInt(baseKm)) * km_price) + 1;
                    helptakePassLong.setText(kmPrice + "元");
                    DecimalFormat df = new DecimalFormat("#.00");
                    helptakePassLongTv.setText("（超过" + df.format(v) + "公里）");
                }
                kgPrice = Double.parseDouble(category[2]);
                helptakeMoney.setText((money + kmPrice + kgPrice - num) + "");//显示设置快递点后的价格变化
                DecimalFormat df = new DecimalFormat("#.00");
                helptakeLong.setText(df.format(distance) + "公里");//订单里程
                helptakePassWeightTv.setText("（超过" + kgPrice + "kg)");
                helptakePassWeight.setText(kgPrice + "元");
            }
        } else {
            if (helptakeItemTv.getText().toString().equals("请选择物品类型")) {//判断是否存在kmprice
                kmPrice = 0;
                helptakeMoney.setText((money - num) + "");//显示设置快递点后的价格变化
                DecimalFormat df = new DecimalFormat("#.0");
                if (distance < 1) {
                    helptakeLong.setText("<1公里");//订单里程
                } else {
                    helptakeLong.setText(df.format(distance) + "公里");//订单里程
                }
                helptakePassLong.setText("0元");
                helptakePassLongTv.setText("（0公里）");
                helptakePassWeightTv.setText("（超过0kg)");
                helptakePassWeight.setText("0元");
            } else {
                kmPrice = 0;
                kgPrice = Double.parseDouble(category[2]);
                DecimalFormat df = new DecimalFormat("#.0");
                String format = df.format(money + kmPrice + kgPrice - num);
                if (distance < 1) {
                    helptakeLong.setText("<1公里");//订单里程
                } else {
                    helptakeLong.setText(df.format(distance) + "公里");//订单里程
                }
                helptakePassLong.setText("0元");
                helptakePassLongTv.setText("（0公里）");
                helptakePassWeightTv.setText("（超过" + kgPrice + "kg)");
                helptakePassWeight.setText(kgPrice + "元");
                helptakeMoney.setText(format);//显示设置快递点后的价格变化
            }
        }
    }

    //获取优惠列表
    @Override
    public void getPreferentialList(PreferentialBean bean) {
        preferntialCount = 0;
        for (int i = 0; i < bean.getCoupons().size(); i++) {
            if (bean.getCoupons().get(i).getCate_id().equals(cate_id) || bean.getCoupons().get(i).getCate_id().equals("10")) {
                preferntialCount++;
            }
        }

        if (preferntialCount != 0) {
            helptakePreferentialTv.setText("优惠劵 x" + preferntialCount + "张");
            helptakePreferential.setOnClickListener(this);//设置点击事件
            helptakePreferential.setEnabled(true);//设置点击事件
        }

    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    //默认地址为空
    @Override
    public void isEmpty() {
        helptakeInfo.setEnabled(true);
        helptakeNext.setVisibility(View.VISIBLE);//显示添加地址 iv
        helptakeHint.setVisibility(View.VISIBLE);//显示添加地址 tv
        addressDefaul.setVisibility(View.GONE);//隐藏默认地址
        //物品类型 不可点击
        haveDefaul = false;
    }

    @Override
    public void payYueInfo(PayYueBean bean) {
        //显示付款
        setPay(bean.getBalance());
    }

    //微信支付
    @Override
    public void payWeiXing(final WeixingPayBean bean) {
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
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "1"), "pay").commit();
    }

    @Override
    public void isPreEmpty() {
        helptakePreferential.setEnabled(false);//无法进入优惠劵列表
    }


    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_F) {
                MapTitleBean mapTitleBean = (MapTitleBean) event.getData();
                if (!TextUtils.isEmpty(mapTitleBean.getTitle())) {
                    //获取地址
                    kmPrice = 0;
                    address = mapTitleBean.getTitle();
                    helptakeAddressTv.setVisibility(View.GONE);
                    helptakeAddressEt.setVisibility(View.VISIBLE);
                    helptakeAddressEt.setText(address);
                    helptakeAddressEt.setTextColor(getResources().getColor(R.color.black));
                    //经度
                    courierLat = mapTitleBean.getLat() + "";
                    //纬度
                    courierLon = mapTitleBean.getLon() + "";
                    //计算路程
                    calculationDistance();
                }
            } else if (event.getCode() == EventCode.EVENT_C) {
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "1"), "pay").commit();
            }
        }
    }


}
