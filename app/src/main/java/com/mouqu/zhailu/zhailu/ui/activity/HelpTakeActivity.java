package com.mouqu.zhailu.zhailu.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.bean.AddressListBean;
import com.mouqu.zhailu.zhailu.bean.ItemsCategoryBean;
import com.mouqu.zhailu.zhailu.bean.PlaceOrderBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.contract.activity.HelpTakeContract;
import com.mouqu.zhailu.zhailu.modle.activity.HelpTakeModel;
import com.mouqu.zhailu.zhailu.presenter.activity.HelpTakePresenter;
import com.mouqu.zhailu.zhailu.ui.adapter.CodeRecyclerviewAdapter;
import com.mouqu.zhailu.zhailu.ui.fragment.PayCompleteFragment;
import com.mouqu.zhailu.zhailu.ui.fragment.ReceivierFragment;
import com.mouqu.zhailu.zhailu.ui.widget.ButtomDialogView;
import com.mouqu.zhailu.zhailu.ui.widget.MyActionBar;
import com.mouqu.zhailu.zhailu.util.DialogUtils;
import com.mouqu.zhailu.zhailu.util.GetSPData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpTakeActivity extends BaseActivity<HelpTakePresenter, HelpTakeModel> implements HelpTakeContract.View, View.OnClickListener {

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
    @BindView(R.id.helptake_express)
    ImageView helptakeExpress;
    @BindView(R.id.helptake_receiver)
    RelativeLayout helptakeReceiver;
    @BindView(R.id.helptake_item_tv)
    TextView helptakeItemTv;
    @BindView(R.id.helptake_category)
    ImageView helptakeCategory;
    @BindView(R.id.helptake_items)
    RelativeLayout helptakeItems;
    @BindView(R.id.helptake_sex_tv)
    TextView helptakeSexTv;
    @BindView(R.id.helptake_sex)
    LinearLayout helptakeSex;
    @BindView(R.id.helptake_time_tv)
    TextView helptakeTimeTv;
    @BindView(R.id.helptake_send)
    LinearLayout helptakeSend;
    @BindView(R.id.helptake_order)
    Button helptakeOrder;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    public static HelpTakeActivity instance = null;
    @BindView(R.id.helptake_remarks)
    EditText helptakeRemarks;
    @BindView(R.id.helptake_preferential)
    LinearLayout helptakePreferential;
    @BindView(R.id.helptake_preferential_tv)
    TextView helptakePreferentialTv;
    private List codeList = new ArrayList();
    private AddressListBean addressListBean;
    private String spUserID;
    private String cate_id;
    private ItemsCategoryBean categoryBean;
    private String[] strings;
    private String[] category;
    private String endId;
    private CodeRecyclerviewAdapter codeRecyclerviewAdapter;
    private String address;
    private int sexId;
    private int timeId;
    private int preferntialCount = 0;

    @Override
    protected void initViewAndEvents() {
        Intent intent = getIntent();
        cate_id = intent.getStringExtra("cate_id");
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getAddressList(spUserID, mMultipleStateView);//获取地址列表
        mMvpPresenter.getItemsCategory("杭州市", cate_id, spUserID, mMultipleStateView);//获取物品分类
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
        //按钮监听
        initListener();
        //设置recycleview
        initRecyclerview();
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
        helptakeInfo.setOnClickListener(this);
        addressCommonly.setOnClickListener(this);
        helptakeReceiver.setOnClickListener(this);
        helptakeSend.setOnClickListener(this);
        helptakeSex.setOnClickListener(this);
        helptakeItems.setOnClickListener(this);
        helptakeOrder.setOnClickListener(this);
        addressLinearlayout.setOnClickListener(this);
        helptakePreferential.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helptake_info://添加收件人信息
                framelayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new ReceivierFragment(), "receiver").commit();
                break;
            case R.id.address_commonly://地址列表
                startActivityForResult(new Intent(HelpTakeActivity.this, TakeAddressListActivity.class), 1);
                break;
            case R.id.helptake_receiver://快遞點
                View inflate = getLayoutInflater().inflate(R.layout.dialog_reciver, null);
                //获取选择项
                address = DialogUtils.showButtomAddressDialog(HelpTakeActivity.this, inflate, true, true, helptakeAddressTv);
                break;
            case R.id.helptake_send://配送时间
                View inflate_send = getLayoutInflater().inflate(R.layout.dialog_send, null);
                //获取选择时间
                timeId = DialogUtils.showButtomSendDialog(HelpTakeActivity.this, inflate_send, true, true, helptakeTimeTv);
                break;
            case R.id.helptake_sex://性别
                View inflate_sex = getLayoutInflater().inflate(R.layout.dialog_sex, null);
                sexId = DialogUtils.showButtomSexDialog(HelpTakeActivity.this, inflate_sex, true, true, helptakeSexTv);
                break;
            case R.id.helptake_items://物品类型
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_items, null);
                category = DialogUtils.showButtomItemsDialog(HelpTakeActivity.this, inflate_items, true, true, helptakeItemTv, categoryBean);
                break;
            case R.id.helptake_order://下单
                //显示下单框
                setPay();
                break;
            case R.id.address_linearlayout://修改地址
                Intent intent2 = new Intent(HelpTakeActivity.this, AddressEditActivity.class);
                startActivity(intent2);
                break;
            case R.id.helptake_preferential:
                Intent intent3 = new Intent(HelpTakeActivity.this, PreferentialListActivity.class);
                startActivityForResult(intent3, 2);
                break;
        }
    }

    private void setPay() {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_another_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payDialog(HelpTakeActivity.this, inflate_pay, true, true);
        TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        TextView dialogPayRecharge = buttomDialogView.findViewById(R.id.dialog_pay_recharge);
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置下单
                setPlaceOrder();
                buttomDialogView.dismiss();
            }
        });
        //充值按钮
        dialogPayRecharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpTakeActivity.this, ReChargeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setPlaceOrder() {
        List<String> list = codeRecyclerviewAdapter.getList();
        strings = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings[i] = list.get(i);
        }
        if (TextUtils.isEmpty(endId)) {
            Toast.makeText(this, "请选择收货人地址", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(address)) {
            Toast.makeText(this, "请选择取件快递点", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(category[0])) {
            Toast.makeText(this, "请选择物品类型", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(category[1])) {
            Toast.makeText(this, "请设置物品重量", Toast.LENGTH_SHORT).show();
        } else {
            mMvpPresenter.placeOrder(spUserID, cate_id, endId, strings,
                    address, category[0], category[1], "", "", "10.00",
                    "8.00", sexId + "", timeId + "", helptakeRemarks.getText().toString(), mMultipleStateView);//下单接口
            framelayout.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new PayCompleteFragment(), "pay").commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        int position = data.getIntExtra("position", -1);
        if (position != -1) {
            endId = addressListBean.getAddress().get(position).getId();
            receiverName.setText(addressListBean.getAddress().get(position).getName());
            receiverNumber.setText(addressListBean.getAddress().get(position).getTelephone());
            helptakeLocation.setText(addressListBean.getAddress().get(position).getAddress() + addressListBean.getAddress().get(position).getDetail());
        }
        int num = data.getIntExtra("num", 0);//返回优惠劵 num
    }

    @Override
    public void getAddressList(AddressListBean bean) {
        helptakeNext.setVisibility(View.GONE);//隐藏添加地址 iv
        helptakeHint.setVisibility(View.GONE);//隐藏添加地址 tv
        addressDefaul.setVisibility(View.VISIBLE);//显示默认地址
        for (int i = 0; i < bean.getAddress().size(); i++) {
            if (bean.getAddress().get(i).getIs_default().equals("1")) {
                endId = bean.getAddress().get(i).getId();
                receiverName.setText(bean.getAddress().get(i).getName());//姓名
                receiverNumber.setText(bean.getAddress().get(i).getTelephone());//电话
                helptakeLocation.setText(bean.getAddress().get(i).getAddress() + bean.getAddress().get(i).getDetail());//地址
            }
        }
        addressListBean = bean;
    }

    @Override
    public void placeOrder(PlaceOrderBean bean) {

    }

    @Override
    public void getItemsCategory(ItemsCategoryBean bean) {
        categoryBean = bean;
    }

    @Override
    public void getPreferentialList(PreferentialBean bean) {
        for (int i = 0; i < bean.getCoupons().size(); i++) {
            if (bean.getCoupons().get(i).equals(cate_id)) {
                preferntialCount++;
            }
        }
        if (preferntialCount != 0) {
            helptakePreferentialTv.setText("优惠劵 x"+preferntialCount+"张");
        }else{
            helptakePreferential.setEnabled(false);//无法进入优惠劵列表
        }
    }

    @Override
    public void isEmpty() {
        helptakeNext.setVisibility(View.VISIBLE);//显示添加地址 iv
        helptakeHint.setVisibility(View.VISIBLE);//显示添加地址 tv
        addressDefaul.setVisibility(View.GONE);//隐藏默认地址
    }



}
