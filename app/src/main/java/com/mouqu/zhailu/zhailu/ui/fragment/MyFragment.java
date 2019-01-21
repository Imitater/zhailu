package com.mouqu.zhailu.zhailu.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseFragment;
import com.mouqu.zhailu.zhailu.bean.UserImageBean;
import com.mouqu.zhailu.zhailu.contract.fragment.MyContract;
import com.mouqu.zhailu.zhailu.modle.fragment.MyModel;
import com.mouqu.zhailu.zhailu.presenter.fragment.MyPresenter;
import com.mouqu.zhailu.zhailu.ui.activity.PackageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyFragment extends BaseFragment<MyPresenter, MyModel> implements MyContract.View, View.OnClickListener {
    @BindView(R.id.circle_head)
    CircleImageView circleHead;
    @BindView(R.id.text_user_nickname)
    TextView textUserNickname;
    @BindView(R.id.text_user_account)
    TextView textUserAccount;
    @BindView(R.id.linearLayout2)
    LinearLayout linearLayout2;
    @BindView(R.id.ll_list_1)
    LinearLayout llList1;
    @BindView(R.id.ll_list_2)
    LinearLayout llList2;
    @BindView(R.id.ll_list_3)
    LinearLayout llList3;
    @BindView(R.id.text_open_phone)
    TextView textOpenPhone;
    @BindView(R.id.ll_list_4)
    LinearLayout llList4;
    @BindView(R.id.ll_list_5)
    LinearLayout llList5;
    @BindView(R.id.ll_list_main)
    LinearLayout llListMain;
    Unbinder unbinder;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_my;
    }

    @Override
    protected void setUpView() {
        setListener();
    }

    private void setListener() {
        circleHead.setOnClickListener(this);
        llList1.setOnClickListener(this);
        llList2.setOnClickListener(this);
        llList3.setOnClickListener(this);
        llList4.setOnClickListener(this);
        llList5.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circle_head:
                break;
            case R.id.ll_list_1://我的资料
                break;
            case R.id.ll_list_2://我的钱包
                Intent intent = new Intent(getMContext(), PackageActivity.class);
                getMContext().startActivity(intent);
                break;
            case R.id.ll_list_3://常用地址
                break;
            case R.id.ll_list_4://客服电话
                break;
            case R.id.ll_list_5://系统设置
                break;
        }
    }

    @Override
    public void getUserImage(UserImageBean bean) {

    }


}
