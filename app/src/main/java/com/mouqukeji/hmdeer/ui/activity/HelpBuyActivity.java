package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.BuyPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyTagBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpBuyContract;
import com.mouqukeji.hmdeer.modle.activity.HelpBuyModel;
import com.mouqukeji.hmdeer.presenter.activity.HelpBuyPresenter;
import com.mouqukeji.hmdeer.ui.adapter.BuyItemsRecyclerviewAdapter;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpBuyActivity extends BaseActivity<HelpBuyPresenter, HelpBuyModel> implements HelpBuyContract.View, View.OnClickListener {


    private static final int SDK_PAY_FLAG = 1;
    public static HelpBuyActivity instance = null;
    @BindView(R.id.helpbuy_actionbar)
    MyActionBar helpbuyActionbar;
    @BindView(R.id.helpbuy_breakfast)
    LinearLayout helpbuyBreakfast;
    @BindView(R.id.helpbuy_lunch)
    LinearLayout helpbuyLunch;
    @BindView(R.id.helpbuy_dinner)
    LinearLayout helpbuyDinner;
    @BindView(R.id.helpbuy_snacks)
    LinearLayout helpbuySnacks;
    @BindView(R.id.helpbuy_daily_necessities)
    LinearLayout helpbuyDailyNecessities;
    @BindView(R.id.buy_items_et)
    EditText buyItemsEt;
    @BindView(R.id.helpbuy_item_recyclerview)
    RecyclerView helpbuyItemRecyclerview;
    @BindView(R.id.buy_items_near)
    TextView buyItemsNear;
    @BindView(R.id.buy_items_put)
    TextView buyItemsPut;
    @BindView(R.id.buy_items_near_tv)
    TextView buyItemsNearTv;
    @BindView(R.id.buy_items_put_et)
    TextView buyItemsPutEt;
    @BindView(R.id.buy_items_commonly)
    LinearLayout buyItemsCommonly;
    @BindView(R.id.buy_items)
    LinearLayout buyItems;
    @BindView(R.id.helpbuy_first)
    RelativeLayout helpbuyFirst;
    @BindView(R.id.helpbuy_unfirst)
    LinearLayout helpbuyUnfirst;
    @BindView(R.id.helpbuy_list)
    LinearLayout helpbuyList;
    @BindView(R.id.helpbuy_sex_tv)
    TextView helpbuySexTv;
    @BindView(R.id.helpbuy_sex)
    LinearLayout helpbuySex;
    @BindView(R.id.helpbuy_time_tv)
    TextView helpbuyTimeTv;
    @BindView(R.id.helpbuy_time)
    LinearLayout helpbuyTime;
    @BindView(R.id.helpbuy_price)
    EditText helpbuyPrice;
    @BindView(R.id.helpbuy_beizhu)
    TextView helpbuyBeizhu;
    @BindView(R.id.helpbuy_beizhu_et)
    EditText helpbuyBeizhuEt;
    @BindView(R.id.helpbuy_order)
    Button helpbuyOrder;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    @BindView(R.id.helpbuy_address_name)
    TextView helpbuyAddressName;
    @BindView(R.id.helpbuy_address_number)
    TextView helpbuyAddressNumber;
    @BindView(R.id.helpbuy_address)
    TextView helpbuyAddress;
    @BindView(R.id.helpbuy_preferntial)
    TextView helpbuyPreferntial;
    @BindView(R.id.helpbuy_preferntial_item)
    LinearLayout helpbuyPreferntialItem;
    @BindView(R.id.hepbuy_money)
    TextView hepbuyMoney;
    @BindView(R.id.helpbuy_breakfast_iv)
    ImageView helpbuyBreakfastIv;
    @BindView(R.id.helpbuy_lunch_iv)
    ImageView helpbuyLunchIv;
    @BindView(R.id.helpbuy_dinner_iv)
    ImageView helpbuyDinnerIv;
    @BindView(R.id.helpbuy_snacks_iv)
    ImageView helpbuySnacksIv;
    @BindView(R.id.helpbuy_daily_necessities_iv)
    ImageView helpbuyDailyNecessitiesIv;


    private boolean breakfrastFlag = false;
    private boolean lunchFlag = false;
    private boolean dinnerFlag = false;
    private boolean snacksFlag = false;
    private boolean dailyFlag = false;

    private LinearLayout helpBuyBuyItems;
    private String spUserID;
    private String city;
    private String cate_id;
    private List list;
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
    private int couponId;
    private float num = 0;
    private int preferntialCount;
    private String gtypeid = "0";
    private String buyAddress;
    private String buyAddressLat;
    private String buyAddressLng;
    private int sex = 0;
    private String buyTime = "";
    private int kmPrice;
    private double money;
    private String commodityPrices = "0";
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
                        Toast.makeText(HelpBuyActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        framelayout.setVisibility(View.VISIBLE);
                        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "2"), "pay").commit();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(HelpBuyActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
    private String order_id;
    private String pay_type;
    private String taskId;

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
        return R.layout.activity_helpbuy;
    }

    @Override
    protected void setUpView() {
        instance = this;
        helpBuyBuyItems = findViewById(R.id.buy_items);
        //设置title
        helpbuyActionbar.setTitle("帮忙买");
        buyTime = DateUtils.getData() + " " + DateUtils.getTime();//设置默认购买时间

        //设置点击事件监听
        initListener();

    }


    private void setRecyclerview(final List list) {
        //设置布局管理器
        helpbuyItemRecyclerview.setLayoutManager(new GridLayoutManager(this, 4));
        //设置Adapter
        BuyItemsRecyclerviewAdapter buyItemsRecyclerviewAdapter = new BuyItemsRecyclerviewAdapter(R.layout.adapter_items_layout, list);
        helpbuyItemRecyclerview.setAdapter(buyItemsRecyclerviewAdapter);
        buyItemsRecyclerviewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                buyItemsEt.setText(buyItemsEt.getText().toString() + list.get(position).toString());

            }
        });

    }


    private void initListener() {
        helpbuyFirst.setOnClickListener(this);
        helpbuyList.setOnClickListener(this);
        helpbuySex.setOnClickListener(this);
        helpbuyTime.setOnClickListener(this);
        helpbuyOrder.setOnClickListener(this);
        helpbuyBreakfast.setOnClickListener(this);
        helpbuyLunch.setOnClickListener(this);
        helpbuyDinner.setOnClickListener(this);
        helpbuySnacks.setOnClickListener(this);
        helpbuyDailyNecessities.setOnClickListener(this);
        helpbuyFirst.setOnClickListener(this);
        helpbuyUnfirst.setOnClickListener(this);
        buyItemsNear.setOnClickListener(this);
        buyItemsPut.setOnClickListener(this);
        buyItemsPutEt.setOnClickListener(this);
        helpbuyPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(s.toString())) {
                    commodityPrices = s.toString();
                    hepbuyMoney.setText((money + Double.parseDouble(commodityPrices)) + "");
                }
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
            case R.id.helpbuy_list:
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra("type","0");
                startActivityForResult(intent, 21);
                break;
            case R.id.helpbuy_sex://性别
                View inflate_sex = getLayoutInflater().inflate(R.layout.dialog_sex, null);
                sex = DialogUtils.showButtomSexDialog(HelpBuyActivity.this, inflate_sex, true, true, helpbuySexTv);
                break;
            case R.id.helpbuy_time://时间
                buyTime = DialogUtils.timeDialog(HelpBuyActivity.this, helpbuyTimeTv, "立即购买");
                break;
            case R.id.helpbuy_order://下单
                checkOrder();
                break;
            case R.id.helpbuy_breakfast://早餐
                //判断 并 设置按钮样式
                if (!breakfrastFlag) {
                    breakfrastFlag = true;
                    lunchFlag = false;
                    dinnerFlag = false;
                    snacksFlag = false;
                    dailyFlag = false;
                    gtypeid = "1";//设置gtypeid
                    mMvpPresenter.helpBuyTag("1", mMultipleStateView);//请求tag 1
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(0).getAfter_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                } else {
                    breakfrastFlag = false;
                    gtypeid = "0";//设置gtypeid
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                     Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                }
                //判断是否显示内容
                isItemsShow();
                break;
            case R.id.helpbuy_lunch://午餐
                //判断 并 设置按钮样式
                if (!lunchFlag) {
                    breakfrastFlag = false;
                    lunchFlag = true;
                    dinnerFlag = false;
                    snacksFlag = false;
                    dailyFlag = false;
                    gtypeid = "2";//设置gtypeid
                    mMvpPresenter.helpBuyTag("2", mMultipleStateView);//请求tag 2
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(1).getAfter_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                } else {
                    lunchFlag = false;
                    gtypeid = "0";//设置gtypeid
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                }
                //判断是否显示内容
                isItemsShow();
                break;
            case R.id.helpbuy_dinner://晚餐
                //判断 并 设置按钮样式
                if (!dinnerFlag) {
                    breakfrastFlag = false;
                    lunchFlag = false;
                    dinnerFlag = true;
                    snacksFlag = false;
                    dailyFlag = false;
                    gtypeid = "3";//设置gtypeid
                    mMvpPresenter.helpBuyTag("3", mMultipleStateView);//请求tag 3
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(2).getAfter_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                } else {
                    gtypeid = "0";//设置gtypeid
                    dinnerFlag = false;
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                }
                //判断是否显示内容
                isItemsShow();
                break;
            case R.id.helpbuy_snacks://零食
                //判断 并 设置按钮样式
                if (!snacksFlag) {
                    breakfrastFlag = false;
                    lunchFlag = false;
                    dinnerFlag = false;
                    snacksFlag = true;
                    dailyFlag = false;
                    gtypeid = "4";//设置gtypeid
                    mMvpPresenter.helpBuyTag("4", mMultipleStateView);//请求tag 4
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(3).getAfter_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                } else {
                    gtypeid = "0";//设置gtypeid
                    snacksFlag = false;
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                }
                //判断是否显示内容
                isItemsShow();
                break;
            case R.id.helpbuy_daily_necessities://日用品
                //判断 并 设置按钮样式
                if (!dailyFlag) {
                    breakfrastFlag = false;
                    lunchFlag = false;
                    dinnerFlag = false;
                    snacksFlag = false;
                    dailyFlag = true;
                    gtypeid = "5";//设置gtypeid
                    mMvpPresenter.helpBuyTag("5", mMultipleStateView);//请求tag 5
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(4).getAfter_icon()).into(helpbuyDailyNecessitiesIv);
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                } else {
                    gtypeid = "0";//设置gtypeid
                    dailyFlag = false;
                    //背景图片
                    Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
                    Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
                    Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
                    Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
                    Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
                }
                //判断是否显示内容
                isItemsShow();
                break;
            case R.id.helpbuy_first://无默认地址
                startActivityForResult(new Intent(this, ReceiverActivity.class), 25);
                break;
            case R.id.helpbuy_unfirst://有默认地址
                framelayout.setVisibility(View.VISIBLE);
                Intent intent2 = new Intent(HelpBuyActivity.this, AddressEditActivity.class);
                intent2.putExtra("userId", spUserID);
                intent2.putExtra("editId", endId);
                intent2.putExtra("editName", name);
                intent2.putExtra("editNumber", number);
                intent2.putExtra("editLocation", location);
                intent2.putExtra("editLocationInfo", locationInfo);
                intent2.putExtra("editisDefaul", isDefaul);
                //传入 需要修改的参数
                startActivityForResult(intent2, 31);
                break;
            case R.id.buy_items_near://就近购买
                buyItemsNearTv.setVisibility(View.VISIBLE);
                buyItemsPutEt.setVisibility(View.GONE);
                buyItemsCommonly.setVisibility(View.VISIBLE);
                //设置购买地址为空
                buyAddress = "";
                //设置购买经度为空
                buyAddressLat = "";
                //设置购买维度为空
                buyAddressLng = "";
                break;
            case R.id.buy_items_put://输入购买地址
                buyItemsNearTv.setVisibility(View.GONE);
                buyItemsPutEt.setVisibility(View.VISIBLE);
                buyItemsCommonly.setVisibility(View.GONE);
                break;
            case R.id.buy_items_put_et:
                //进入地址选择页面
                startActivityForResult(new Intent(this, SelectLocationActivity.class), 23);
                break;
            case R.id.helpbuy_preferntial_item:
                Intent intent3 = new Intent(HelpBuyActivity.this, PreferentialListActivity.class);
                startActivityForResult(intent3, 22);
                break;
        }
    }

    //判断是否满足下单条件
    private void checkOrder() {
        if (gtypeid.equals("0")) {
            Toast.makeText(this, "请选择您要购买的商品类型", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(buyItemsEt.getText().toString())) {
            Toast.makeText(this, "请输入您要购买的物品", Toast.LENGTH_SHORT).show();
        } else if (endId.equals("-1")) {
            Toast.makeText(this, "请设置收件人地址", Toast.LENGTH_SHORT).show();
        } else {
            //money保留2位小数
            final DecimalFormat df = new DecimalFormat("#.00");
            //设置下单
            mMvpPresenter.placeOrder(spUserID, cate_id, endId, gtypeid, "0", num + "", couponId + "", df.format(money + Double.parseDouble(commodityPrices)), df.format(money + Double.parseDouble(commodityPrices) - num),
                    sex + "", buyTime, helpbuyBeizhuEt.getText().toString(), buyItemsEt.getText().toString(), buyAddress, buyAddressLat, buyAddressLng, commodityPrices, mMultipleStateView);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 21:
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
                    helpbuyAddressName.setText(name);//姓名
                    helpbuyAddressNumber.setText(number);//电话
                    helpbuyAddress.setText(location + locationInfo);//地址
                }

                break;
            case 22:
                //返回优惠劵 num
                num = data.getFloatExtra("num", 0);
                couponId = data.getIntExtra("couponId", 0);
                hepbuyMoney.setText((money - num) + "");
                break;
            case 23:
                if (!TextUtils.isEmpty(data.getStringExtra("select_address"))) {
                    //获取地址
                    buyAddress = data.getStringExtra("select_address");
                    buyItemsPutEt.setText(buyAddress);
                    //经度
                    buyAddressLat = data.getStringExtra("select_point_lat");
                    //纬度
                    buyAddressLng = data.getStringExtra("select_point_lon");
                    //设置购买地址之后 money变化
                    double distance = DinstaceUtils.getDistance(new LatLng(Double.parseDouble(buyAddressLat), Double.parseDouble(buyAddressLng)), new LatLng(Double.parseDouble(addressLat), Double.parseDouble(addressLon)));
                    //超路程  计价
                    if (distance > Integer.parseInt(baseKm)) {
                        kmPrice = (int) ((distance - Integer.parseInt(baseKm)) * Double.parseDouble(km_price));
                    } else {
                        kmPrice = 0;
                    }
                    money = money + kmPrice;
                    hepbuyMoney.setText(money + "");//显示设置快递点后的价格变化
                }
                break;
            case 31:
                mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);//获取优惠列表
                break;
            case 25:
                mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);//获取优惠列表
                break;
        }
    }

    //判断是否显示内容
    private void isItemsShow() {
        if (breakfrastFlag || lunchFlag || dinnerFlag || snacksFlag || dailyFlag) {
            helpBuyBuyItems.setVisibility(View.VISIBLE);
            hepbuyMoney.setText(money + "");
        } else {
            helpBuyBuyItems.setVisibility(View.GONE);
            hepbuyMoney.setText("0");
            buyItemsEt.setText("");
        }
    }


    //物品分类
    @Override
    public void getItemsCategory(ItemsCategoryBean bean) {
        helpbuyFirst.setEnabled(false);
        helpbuyFirst.setVisibility(View.GONE);//隐藏第一次添加地址
        helpbuyUnfirst.setVisibility(View.VISIBLE);//显示默认地址
        categoryBean = bean;
        //设置商品类型默认图片
        setCategoryImg();
        if (!TextUtils.isEmpty(bean.getDefault_address().toString())){
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
        helpbuyAddressName.setText(name);//姓名
        helpbuyAddressNumber.setText(number);//电话
        helpbuyAddress.setText(location + locationInfo);//地址
        }
    }

    private void setCategoryImg() {
        //设置默认商品类型图片
        Glide.with(this).load(categoryBean.getType().get(0).getBefore_icon()).into(helpbuyBreakfastIv);
        Glide.with(this).load(categoryBean.getType().get(1).getBefore_icon()).into(helpbuyLunchIv);
        Glide.with(this).load(categoryBean.getType().get(2).getBefore_icon()).into(helpbuyDinnerIv);
        Glide.with(this).load(categoryBean.getType().get(3).getBefore_icon()).into(helpbuySnacksIv);
        Glide.with(this).load(categoryBean.getType().get(4).getBefore_icon()).into(helpbuyDailyNecessitiesIv);
    }

    //下单
    @Override
    public void placeOrder(BuyPlaceOrderBean bean) {
        order_id = bean.getOrder_id();
        mMvpPresenter.payYueInfo(spUserID, order_id, mMultipleStateView);//获取余额
    }

    //余额显示
    @Override
    public void payYueInfo(PayYueBean bean) {
        setPay(bean.getBalance());
    }


    //优惠
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
                helpbuyPreferntial.setText("优惠劵 x" + preferntialCount + "张");
                helpbuyPreferntialItem.setOnClickListener(this);//设置点击事件
            } else {
                helpbuyPreferntialItem.setEnabled(false);//无法进入优惠劵列表
            }
        }
    }

    private void setPay(int balance) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpBuyActivity.this, inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final TextView dialogPayWalletMoney = buttomDialogView.findViewById(R.id.dialog_pay_wallet_money);
        final RadioButton dialogPayYue = buttomDialogView.findViewById(R.id.dialog_pay_yue);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        //判断余额是否为0
        if (balance != 0 && balance > money + Double.parseDouble(commodityPrices)) {
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
        payMoneyTv.setText(df.format(money + Double.parseDouble(commodityPrices)));
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.payWeiXing(order_id, spUserID, "1", df.format(money + Double.parseDouble(commodityPrices)), mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.payZhifubao(order_id, spUserID, "2", df.format(money + Double.parseDouble(commodityPrices)), mMultipleStateView);//支付宝支付接口
                } else {
                    pay_type = "3";
                    mMvpPresenter.payYue(order_id, spUserID, "3", df.format(money + Double.parseDouble(commodityPrices)), mMultipleStateView);//余额支付接口
                }
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpBuyActivity.this, ReChargeActivity.class);
                intent.putExtra("rechange_type", "1");//设置充值标记
                startActivity(intent);
            }
        });
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
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "2"), "pay").commit();
    }


    @Override
    public void helpBuyTag(HelpBuyTagBean bean) {
        list = new ArrayList<String>();
        for (int i = 0; i < bean.getTags().size(); i++) {
            list.add(bean.getTags().get(i).getTag());
        }
        //设置 recyclerview
        setRecyclerview(list);
    }

    @Override
    public void isEmpty() {
        helpbuyUnfirst.setEnabled(false);
        helpbuyUnfirst.setVisibility(View.INVISIBLE);//显示第一次添加地址
        helpbuyFirst.setVisibility(View.VISIBLE);//隐藏默认地址
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            framelayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "2"), "pay").commit();
        }
    }
}
