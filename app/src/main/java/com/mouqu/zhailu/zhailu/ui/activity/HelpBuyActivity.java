package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.HelpBuyContract;
import com.mouqu.zhailu.zhailu.modle.activity.HelpBuyModel;
import com.mouqu.zhailu.zhailu.presenter.activity.HelpBuyPresenter;
import com.mouqu.zhailu.zhailu.ui.adapter.BuyItemsRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.fragment.BuyAddressNewReceiverFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.BuyEditFragment;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.ui.widget.MyLinearLayoutManager;
import com.mouqu.zhailu.zhailu.util.DialogUtils;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HelpBuyActivity extends BaseActivity<HelpBuyPresenter, HelpBuyModel> implements HelpBuyContract.View, View.OnClickListener{


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
    EditText buyItemsPutEt;
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


    private boolean breakfrastFlag = false;
    private boolean lunchFlag = false;
    private boolean dinnerFlag = false;
    private boolean snacksFlag = false;
    private boolean dailyFlag = false;


    private List<String> list1 = new ArrayList();
    private List<String> list2 = new ArrayList();
    private List<String> list3 = new ArrayList();
    private List<String> list4 = new ArrayList();
    private List<String> list5 = new ArrayList();

    //设置 recyclerview 点击内容
    private String contact = "";
    private LinearLayout helpBuyBuyItems;


    @Override
    protected void initViewAndEvents() {

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
        //设置点击事件监听
        initListener();
        //设置recyclerview 数据
        initData();
    }

    private void initData() {
        list1.add("小笼包");
        list1.add("包子");
        list1.add("拌面");
        list1.add("小米粥");
        list1.add("煎饺");
        list1.add("油条");
        list1.add("与我联系");

        list2.add("拉面");
        list2.add("炒面");
        list2.add("拌面");
        list2.add("盖浇饭");
        list2.add("水饺");
        list2.add("麻辣烫");
        list2.add("与我联系");

        list3.add("炒饭");
        list3.add("炒面");
        list3.add("酸辣粉");
        list3.add("盖浇饭");
        list3.add("水饺");
        list3.add("酸辣粉");
        list3.add("与我联系");

        list4.add("薯片");
        list4.add("泡面");
        list4.add("士力架");
        list4.add("可乐");
        list4.add("口香糖");
        list4.add("饼干");
        list4.add("与我联系");

        list5.add("薯片");
        list5.add("泡面");
        list5.add("士力架");
        list5.add("可乐");
        list5.add("口香糖");
        list5.add("饼干");
        list5.add("与我联系");

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
                //当输入框为空时 清空contact
                if (TextUtils.isEmpty(buyItemsEt.getText().toString())) {
                    contact = "";
                }
                //将 recyclerview 每次点击内容叠加放入 contact中
                contact = contact + " " + list.get(position).toString();
                buyItemsEt.setText(contact);
            }
        });

    }


    private void initListener() {
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
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpbuy_list:
                //跳转 地址列表页面
                startActivityForResult(new Intent(HelpBuyActivity.this, BuyAddressListActivity.class), 1);
                break;
            case R.id.helpbuy_sex://性别
                View inflate_sex = getLayoutInflater().inflate(R.layout.dialog_sex, null);
                DialogUtils.showButtomSexDialog(HelpBuyActivity.this, inflate_sex, true, true, helpbuySexTv);
                break;
            case R.id.helpbuy_time://时间
                DialogUtils.timeDialog(HelpBuyActivity.this, helpbuyTimeTv, "立即购买");
                break;
            case R.id.helpbuy_order:
                Intent intent1 = new Intent(HelpBuyActivity.this, BuyOrderInfoActivity.class);
                startActivity(intent1);
                break;
            case R.id.helpbuy_breakfast://早餐
                //判断 并 设置按钮样式
                if (!breakfrastFlag) {
                    breakfrastFlag = true;
                    lunchFlag = false;
                    dinnerFlag = false;
                    snacksFlag = false;
                    dailyFlag = false;
                } else {
                    breakfrastFlag = false;
                }
                //判断是否显示内容
                isItemsShow(list1);
                break;
            case R.id.helpbuy_lunch://午餐
                //判断 并 设置按钮样式
                if (!lunchFlag) {
                    breakfrastFlag = false;
                    lunchFlag = true;
                    dinnerFlag = false;
                    snacksFlag = false;
                    dailyFlag = false;
                } else {
                    lunchFlag = false;
                }
                //判断是否显示内容
                isItemsShow(list2);
                break;
            case R.id.helpbuy_dinner://晚餐
                //判断 并 设置按钮样式
                if (!dinnerFlag) {
                    breakfrastFlag = false;
                    lunchFlag = false;
                    dinnerFlag = true;
                    snacksFlag = false;
                    dailyFlag = false;
                } else {
                    dinnerFlag = false;
                }
                //判断是否显示内容
                isItemsShow(list3);
                break;
            case R.id.helpbuy_snacks://零食
                //判断 并 设置按钮样式
                if (!snacksFlag) {
                    breakfrastFlag = false;
                    lunchFlag = false;
                    dinnerFlag = false;
                    snacksFlag = true;
                    dailyFlag = false;
                } else {
                    snacksFlag = false;
                }
                //判断是否显示内容
                isItemsShow(list4);
                break;
            case R.id.helpbuy_daily_necessities://日用品
                //判断 并 设置按钮样式
                if (!dailyFlag) {
                    breakfrastFlag = false;
                    lunchFlag = false;
                    dinnerFlag = false;
                    snacksFlag = false;
                    dailyFlag = true;
                } else {
                    dailyFlag = false;
                }
                //判断是否显示内容
                isItemsShow(list5);
                break;
            case R.id.helpbuy_first://无默认地址
                //设置默认地址
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new BuyAddressNewReceiverFragment(), "new_address").commit();
                break;
            case R.id.helpbuy_unfirst://有默认地址
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new BuyEditFragment(), "edit_address").commit();
                break;
            case R.id.buy_items_near://就近购买
                buyItemsNearTv.setVisibility(View.VISIBLE);
                buyItemsPutEt.setVisibility(View.GONE);
                buyItemsCommonly.setVisibility(View.VISIBLE);
                break;
            case R.id.buy_items_put://输入购买地址
                buyItemsNearTv.setVisibility(View.GONE);
                buyItemsPutEt.setVisibility(View.VISIBLE);
                buyItemsCommonly.setVisibility(View.GONE);
                break;
        }
    }

    //判断是否显示内容
    private void isItemsShow(List list) {
        if (breakfrastFlag || lunchFlag || dinnerFlag || snacksFlag || dailyFlag) {
            helpBuyBuyItems.setVisibility(View.VISIBLE);
        } else {
            helpBuyBuyItems.setVisibility(View.GONE);
            buyItemsEt.setText("");
        }
        //设置 recyclerview
        setRecyclerview(list);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        String result = data.getExtras().getString("result");//得到新Activity 关闭后返回的数据
    }


}
