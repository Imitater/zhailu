package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.BuyPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyTagBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.MapTitleBean;
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

public class HelpBuyActivity extends BaseActivity<HelpBuyPresenter, HelpBuyModel> implements HelpBuyContract.View, View.OnClickListener {
    private static final int SDK_PAY_FLAG = 1;
    public static HelpBuyActivity instance = null;
    @BindView(R.id.helpbuy_actionbar)
    MyActionBar helpbuyActionbar;
    @BindView(R.id.helpbuy_breakfast_iv)
    ImageView helpbuyBreakfastIv;
    @BindView(R.id.helpbuy_breakfast)
    LinearLayout helpbuyBreakfast;
    @BindView(R.id.helpbuy_lunch_iv)
    ImageView helpbuyLunchIv;
    @BindView(R.id.helpbuy_lunch)
    LinearLayout helpbuyLunch;
    @BindView(R.id.helpbuy_dinner_iv)
    ImageView helpbuyDinnerIv;
    @BindView(R.id.helpbuy_dinner)
    LinearLayout helpbuyDinner;
    @BindView(R.id.helpbuy_snacks_iv)
    ImageView helpbuySnacksIv;
    @BindView(R.id.helpbuy_snacks)
    LinearLayout helpbuySnacks;
    @BindView(R.id.helpbuy_daily_necessities_iv)
    ImageView helpbuyDailyNecessitiesIv;
    @BindView(R.id.helpbuy_daily_necessities)
    LinearLayout helpbuyDailyNecessities;
    @BindView(R.id.buy_items_et)
    EditText buyItemsEt;
    @BindView(R.id.helpbuy_item_recyclerview)
    RecyclerView helpbuyItemRecyclerview;
    @BindView(R.id.buy_items_near)
    TextView buyItemsNear;
    @BindView(R.id.buy_items_near_iv)
    ImageView buyItemsNearIv;
    @BindView(R.id.buy_items_put)
    TextView buyItemsPut;
    @BindView(R.id.buy_items_put_iv)
    ImageView buyItemsPutIv;
    @BindView(R.id.buy_items_near_tv)
    TextView buyItemsNearTv;
    @BindView(R.id.buy_items_put_et)
    TextView buyItemsPutEt;
    @BindView(R.id.buy_items_put_address)
    EditText buyItemsPutAddress;
    @BindView(R.id.buy_items_put_address_iv)
    ImageView buyItemsPutAddressIv;
    @BindView(R.id.buy_items_put_address_ll)
    LinearLayout buyItemsPutAddressLl;
    @BindView(R.id.buy_items)
    LinearLayout buyItems;
    @BindView(R.id.helpbuy_first)
    RelativeLayout helpbuyFirst;
    @BindView(R.id.helpbuy_address_name)
    TextView helpbuyAddressName;
    @BindView(R.id.helpbuy_address_number)
    TextView helpbuyAddressNumber;
    @BindView(R.id.helpbuy_address)
    TextView helpbuyAddress;
    @BindView(R.id.helpbuy_unfirst)
    LinearLayout helpbuyUnfirst;
    @BindView(R.id.helpbuy_list)
    LinearLayout helpbuyList;
    @BindView(R.id.helpbuy_time_tv)
    TextView helpbuyTimeTv;
    @BindView(R.id.helpbuy_time)
    LinearLayout helpbuyTime;
    @BindView(R.id.helpbuy_preferntial)
    TextView helpbuyPreferntial;
    @BindView(R.id.helpbuy_preferntial_item)
    LinearLayout helpbuyPreferntialItem;
    @BindView(R.id.helpbuy_beizhu)
    TextView helpbuyBeizhu;
    @BindView(R.id.helpbuy_beizhu_et)
    EditText helpbuyBeizhuEt;
    @BindView(R.id.helpbuy_long)
    TextView helpbuyLong;
    @BindView(R.id.helpbuy_price)
    TextView helpbuyPrice;
    @BindView(R.id.helpbuy_pass_long_tv)
    TextView helpbuyPassLongTv;
    @BindView(R.id.helpbuy_pass_long)
    TextView helpbuyPassLong;
    @BindView(R.id.helpbuy_pass_weight_tv)
    TextView helpbuyPassWeightTv;
    @BindView(R.id.helpbuy_pass_weight)
    TextView helpbuyPassWeight;
    @BindView(R.id.helpbuy_huiyuan)
    ImageView helpbuyHuiyuan;
    @BindView(R.id.hepbuy_money)
    TextView hepbuyMoney;
    @BindView(R.id.helpbuy_order)
    Button helpbuyOrder;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
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
    private String couponId = "0";
    private int num = 0;
    private int preferntialCount;
    private String gtypeid = "0";
    private String buyAddress;
    private String buyAddressLat="";
    private String buyAddressLng="";
     private int kmPrice;
    private double money;
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
    private ItemsCategoryBean itemsCategoryBean;
    private boolean haveDefaul;
    private Double noNum;
    private String vip_num;

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
        return R.layout.activity_helpbuy;
    }

    @Override
    protected void setUpView() {
        instance = this;
        helpBuyBuyItems = findViewById(R.id.buy_items);
        //设置title
        helpbuyActionbar.setTitle("帮忙买");

        helpbuyList.setOnClickListener(this);
        helpbuyFirst.setOnClickListener(this);
        //设置默认图片
        //背景图片
        Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
        Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
        Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
        Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
        Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);

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
                buyItemsEt.setSelection(buyItemsEt.getText().length());
            }
        });

    }


    private void initListener() {
        helpbuyUnfirst.setOnClickListener(this);
        helpbuyTime.setOnClickListener(this);
        helpbuyOrder.setOnClickListener(this);
        helpbuyBreakfast.setOnClickListener(this);
        helpbuyLunch.setOnClickListener(this);
        helpbuyDinner.setOnClickListener(this);
        helpbuySnacks.setOnClickListener(this);
        helpbuyDailyNecessities.setOnClickListener(this);
        helpbuyFirst.setOnClickListener(this);
        buyItemsNear.setOnClickListener(this);
        buyItemsPut.setOnClickListener(this);
        buyItemsPutEt.setOnClickListener(this);
        buyItemsPutAddressIv.setOnClickListener(this);
        helpbuyHuiyuan.setOnClickListener(this);
        buyItemsPutAddressIv.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpbuy_list:
                Intent intent = new Intent(this, AddressListActivity.class);
                intent.putExtra("type", "0");
                startActivityForResult(intent, 21);
                break;
            case R.id.helpbuy_time://时间
                 DialogUtils.timeDialog(HelpBuyActivity.this, helpbuyTimeTv, "立即购买");
                break;
            case R.id.helpbuy_order://下单
                if (haveDefaul) {
                    checkOrder();
                } else {
                    Toast.makeText(HelpBuyActivity.this, "请填写收货地址", Toast.LENGTH_SHORT).show();
                }
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
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan_jianying.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
                } else {
                    breakfrastFlag = false;
                    gtypeid = "0";//设置gtypeid
                    //背景图片
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
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
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan_jianying.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
                } else {
                    lunchFlag = false;
                    gtypeid = "0";//设置gtypeid
                    //背景图片
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
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
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan_jianying.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
                } else {
                    gtypeid = "0";//设置gtypeid
                    dinnerFlag = false;
                    //背景图片
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
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
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi_jianying.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
                } else {
                    gtypeid = "0";//设置gtypeid
                    snacksFlag = false;
                    //背景图片
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
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
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin_jianying.png").into(helpbuyDailyNecessitiesIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                } else {
                    gtypeid = "0";//设置gtypeid
                    dailyFlag = false;
                    //背景图片
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zaocan.png").into(helpbuyBreakfastIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_zhongcan.png").into(helpbuyLunchIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_wancan.png").into(helpbuyDinnerIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_lingshi.png").into(helpbuySnacksIv);
                    Glide.with(this).load("https://test.mouqukeji.com/static/icon/icon_riyongpin.png").into(helpbuyDailyNecessitiesIv);
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
                //设置购买地址为空
                buyAddress = "";
                //设置购买经度为空
                buyAddressLat = "";
                //设置购买维度为空
                buyAddressLng = "";
                buyItemsNearIv.setVisibility(View.VISIBLE);
                buyItemsPutIv.setVisibility(View.GONE);
                buyItemsPutAddress.setVisibility(View.GONE);
                buyItemsPut.setBackgroundResource(R.drawable.button_buy_shape_unparss_left);
                buyItemsNear.setBackgroundResource(R.drawable.button_buy_shape_left);
                buyItemsPutAddressLl.setVisibility(View.GONE);
                break;
            case R.id.buy_items_put://输入购买地址
                buyItemsNearTv.setVisibility(View.GONE);
                buyItemsPutEt.setVisibility(View.VISIBLE);
                buyItemsNearIv.setVisibility(View.GONE);
                buyItemsPutIv.setVisibility(View.VISIBLE);
                buyItemsPut.setBackgroundResource(R.drawable.button_buy_shape_left);
                buyItemsNear.setBackgroundResource(R.drawable.button_buy_shape_unparss_left);
                break;
            case R.id.buy_items_put_et:
                //进入地址选择页面
                Intent intent1 = new Intent(this, SelectCourierActivity.class);
                startActivity(intent1);
                break;
            case R.id.helpbuy_preferntial_item:
                //优惠券
                if (gtypeid.equals("0")) {
                    Toast.makeText(this, "请选择您要购买的商品类型", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(buyItemsEt.getText().toString())) {
                    Toast.makeText(this, "请输入您要购买的物品", Toast.LENGTH_SHORT).show();
                } else if (!haveDefaul) {
                    Toast.makeText(this, "请设置收件人地址", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent3 = new Intent(HelpBuyActivity.this, PreferentialSelectListActivity.class);
                    intent3.putExtra("cate_id", cate_id);
                    startActivityForResult(intent3, 22);
                }
                break;
            case R.id.buy_items_put_address_iv:
                //进入地址选择页面
                Intent intent6 = new Intent(this, SelectCourierActivity.class);
                startActivity(intent6);
                break;
            case R.id.helpbuy_huiyuan:
                //会员卡界面
                Intent intent3 = new Intent(HelpBuyActivity.this, MemberCenterActivity.class);
                startActivity(intent3);
                break;
        }
    }

    //判断是否满足下单条件
    private void checkOrder() {
        if (gtypeid.equals("0")) {
            Toast.makeText(this, "请选择您要购买的商品类型", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(buyItemsEt.getText().toString())) {
            Toast.makeText(this, "请输入您要购买的物品", Toast.LENGTH_SHORT).show();
        } else if (!haveDefaul) {
            Toast.makeText(this, "请设置收件人地址", Toast.LENGTH_SHORT).show();
        } else {

            if (helpbuyTimeTv.getText().toString().equals("立即购买")){
                String buyTime = DateUtils.getData() + " " + DateUtils.getTime();//设置默认购买时间
                //设置下单
                mMvpPresenter.placeOrder(spUserID, cate_id, endId, gtypeid, "0", num + "",
                        couponId + "", (money + kmPrice) + "",
                        hepbuyMoney.getText().toString(),
                        "", buyTime, helpbuyBeizhuEt.getText().toString(),
                        buyItemsEt.getText().toString(), buyAddress, buyAddressLat, buyAddressLng, "",mMultipleStateView);
            }else{
                //设置下单
                mMvpPresenter.placeOrder(spUserID, cate_id, endId, gtypeid, "0", num + "",
                        couponId + "", (money + kmPrice) + "",
                        hepbuyMoney.getText().toString(),
                        "", helpbuyTimeTv.getText().toString(), helpbuyBeizhuEt.getText().toString(),
                        buyItemsEt.getText().toString(), buyAddress, buyAddressLat, buyAddressLng, "",mMultipleStateView);
            }

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
                    //计算距离
                    if (!TextUtils.isEmpty(buyAddressLat)&&!TextUtils.isEmpty(buyAddressLng)) {
                        calculationDistance();
                    }
                } else {
                    mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                }
                break;
            case 22:
                //返回优惠劵
                if (num == 0) {
                    //初次进入优惠券
                    String moneyTv = hepbuyMoney.getText().toString();
                    //无优惠劵 设置最终money
                    noNum = Double.parseDouble(moneyTv);
                }
                num = Integer.parseInt(data.getStringExtra("num"));
                couponId = data.getStringExtra("couponId");
                if (num != 0) {
                    hepbuyMoney.setText((noNum - num) + "");
                    helpbuyPreferntial.setText("￥-" + num);
                    helpbuyPreferntial.setTextColor(getResources().getColor(R.color.black));
                }
                break;
            case 31:
                if (TextUtils.isEmpty(helpbuyAddressName.getText().toString())) {
                    mMvpPresenter.getItemsCategory(city, cate_id, spUserID, mMultipleStateView);//获取物品分类
                    mMvpPresenter.getPreferentialList(spUserID, mMultipleStateView);//获取优惠列表
                }
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
            hepbuyMoney.setText((money + kmPrice - num) + "");
        } else {
            helpBuyBuyItems.setVisibility(View.GONE);
            hepbuyMoney.setText("0");
            buyItemsEt.setText("");
        }
    }


    //物品分类
    @Override
    public void getItemsCategory(ItemsCategoryBean bean) {
        itemsCategoryBean = bean;
        helpbuyFirst.setEnabled(false);
        helpbuyFirst.setVisibility(View.GONE);//隐藏第一次添加地址
        helpbuyUnfirst.setVisibility(View.VISIBLE);//显示默认地址
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
        helpbuyAddressName.setText(name);//姓名
        helpbuyAddressNumber.setText(number);//电话
        helpbuyAddress.setText(location + locationInfo);//地址
        //物品类型 可点击
        haveDefaul = true;
        helpbuyPrice.setText(money + "元");
        vip_num = bean.getVip_num();
        if (Integer.parseInt(vip_num)>0) {
            money = 0;
            helpbuyHuiyuan.setVisibility(View.GONE);
        } else {
            helpbuyHuiyuan.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(buyAddressLat)&&!TextUtils.isEmpty(buyAddressLng)){
            //计算距离
            calculationDistance();
        }
    }

    //下单
    @Override
    public void placeOrder(BuyPlaceOrderBean bean) {
        order_id = bean.getOrder_id();
        if (Double.parseDouble(hepbuyMoney.getText().toString())<1){
            framelayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(bean.getOrder().getTask_id(), bean.getOrder().getCate_id(), "2"), "pay").commit();
        }else{
            mMvpPresenter.payYueInfo(spUserID, order_id, mMultipleStateView);//获取余额
        }
        //发送消息 已下单 刷新列表
        EventMessage eventMessage = new EventMessage(EventCode.EVENT_L, 1);
        post(eventMessage);
    }

    //余额显示
    @Override
    public void payYueInfo(PayYueBean bean) {
        setPay(bean.getBalance(), bean.getPay_fee());
    }


    //优惠
    @Override
    public void getPreferentialList(PreferentialBean bean) {
        preferntialCount = 0;
        if (bean.getCoupons().size() != 0) {
            for (int i = 0; i < bean.getCoupons().size(); i++) {
                if (bean.getCoupons().get(i).getCate_id().equals(cate_id) || bean.getCoupons().get(i).getCate_id().equals("10")) {
                    preferntialCount++;
                }
            }
        }
        if (preferntialCount != 0) {
            helpbuyPreferntial.setText("优惠劵 x" + preferntialCount + "张");
            helpbuyPreferntialItem.setOnClickListener(this);//设置点击事件
            helpbuyPreferntialItem.setEnabled(true);
        }
    }

    private void setPay(double balance, final String pay_fee) {
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
        if (balance != 0 && balance >= Double.parseDouble(pay_fee)) {
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
        //物品类型 不可点击
        haveDefaul = false;
    }

    @Override
    public void isPrefEmpty() {
        helpbuyPreferntialItem.setEnabled(false);//无法进入优惠劵列表
    }

    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            if (event.getCode() == EventCode.EVENT_F) {
                MapTitleBean mapTitleBean = (MapTitleBean) event.getData();
                if (!TextUtils.isEmpty(mapTitleBean.getTitle())) {
                    //获取地址
                    buyAddress = mapTitleBean.getTitle();
                    buyItemsPutAddressLl.setVisibility(View.VISIBLE);
                    buyItemsPutAddress.setVisibility(View.VISIBLE);
                    buyItemsPutEt.setVisibility(View.GONE);
                    buyItemsPutAddress.setText(buyAddress);
                    buyItemsPutAddress.setTextColor(getResources().getColor(R.color.black));
                    //经度
                    buyAddressLat = mapTitleBean.getLat() + "";
                    //纬度
                    buyAddressLng = mapTitleBean.getLon() + "";
                    //计算距离
                    calculationDistance();

                }
            } else if (event.getCode() == EventCode.EVENT_C){
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, PayCompleteFragment.newInstance(taskId, cate_id, "2"), "pay").commit();
            }
        }
    }

    private void calculationDistance() {
        //设置购买地址之后 money变化
        double distance = DinstaceUtils.getDistance(new LatLng(Double.parseDouble(buyAddressLat), Double.parseDouble(buyAddressLng)),
                new LatLng(Double.parseDouble(addressLat), Double.parseDouble(addressLon)));
        //超路程  计价
        if (distance > Integer.parseInt(baseKm)) {
            double v = distance - Integer.parseInt(baseKm);
            if (v < 1) {
                kmPrice = 1;
                helpbuyPassLong.setText(kmPrice + "元");
                helpbuyPassLongTv.setText("（超过1公里）");
            } else {
                kmPrice = (int) ((distance - Integer.parseInt(baseKm)) * Double.parseDouble(km_price)) + 1;
                helpbuyPassLong.setText(kmPrice + "元");
                DecimalFormat df = new DecimalFormat("#.00");
                helpbuyPassLongTv.setText("（超过" + df.format(v) + "公里）");
            }
            DecimalFormat df = new DecimalFormat("#.00");
            helpbuyLong.setText(df.format(distance) + "公里");
        } else {
            DecimalFormat df = new DecimalFormat("#.00");
            if (distance < 1) {
                helpbuyLong.setText("<1公里");//订单里程
            } else {
                helpbuyLong.setText(df.format(distance) + "公里");//订单里程
            }
            kmPrice = 0;
            helpbuyPassLong.setText("0元");
            helpbuyPassLongTv.setText("（0公里）");
        }
        helpbuyPassWeightTv.setText("（超过0kg)");
        helpbuyPassWeight.setText("0元");
        hepbuyMoney.setText((money + kmPrice - num) + "");//显示设置快递点后的价格变化
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }


}
