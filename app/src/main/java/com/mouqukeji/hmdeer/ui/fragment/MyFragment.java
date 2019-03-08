package com.mouqukeji.hmdeer.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseLazyFragment;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.contract.fragment.MyContract;
import com.mouqukeji.hmdeer.modle.fragment.MyModel;
import com.mouqukeji.hmdeer.presenter.fragment.MyPresenter;
import com.mouqukeji.hmdeer.ui.activity.AddressSettingListActivity;
import com.mouqukeji.hmdeer.ui.activity.MemberCenterActivity;
import com.mouqukeji.hmdeer.ui.activity.MyInformationActivity;
import com.mouqukeji.hmdeer.ui.activity.MyMembersActivity;
import com.mouqukeji.hmdeer.ui.activity.PackageActivity;
import com.mouqukeji.hmdeer.ui.activity.SettingActivity;
import com.mouqukeji.hmdeer.ui.activity.SignInActivity;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;


public class MyFragment extends BaseLazyFragment<MyPresenter, MyModel> implements MyContract.View, View.OnClickListener {
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
    @BindView(R.id.ll_list_6)
    LinearLayout llList6;
    @BindView(R.id.my_isvip)
    TextView myIsvip;
    private String spUserID;
    private int vipId;

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(getActivity());
        mMvpPresenter.getUserImage(spUserID, mMultipleStateView);
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
        llList6.setOnClickListener(this);
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
        mMvpPresenter.getUserImage(spUserID, mMultipleStateView);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circle_head:
                Intent intent2 = new Intent(getMContext(), MyInformationActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_list_1://我的资料
                Intent intent7 = new Intent(getMContext(), MyInformationActivity.class);
                startActivity(intent7);
                break;
            case R.id.ll_list_2://我的钱包
                Intent intent = new Intent(getMContext(), PackageActivity.class);
                getMContext().startActivity(intent);
                break;
            case R.id.ll_list_3://常用地址
                Intent intent3 = new Intent(getActivity(), AddressSettingListActivity.class);
                intent3.putExtra("type", "1");
                startActivityForResult(intent3, 97);
                break;
            case R.id.ll_list_4://客服电话
                View dialog_iscall = getLayoutInflater().inflate(R.layout.dialog_iscall, null);
                DialogUtils.callDialog(getMContext(), dialog_iscall, true, true);
                break;
            case R.id.ll_list_5://系统设置
                Intent intent1 = new Intent(getMContext(), SettingActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_list_6://会员卡
                if (vipId == 0) {
                    Intent intent4 = new Intent(getMContext(), MemberCenterActivity.class);
                    startActivity(intent4);
                } else {
                    Intent intent4 = new Intent(getMContext(), MyMembersActivity.class);
                    startActivity(intent4);
                }
                break;
        }
    }

    @Override
    public void getUserImage(UserImageBean bean) {
        if (!TextUtils.isEmpty(bean.getNickname())) {
            textUserNickname.setText(bean.getNickname());
        }
        textUserAccount.setText(bean.getTelephone());
        if (!TextUtils.isEmpty(bean.getAvatar())) {
            try {
                Glide.with(this).load(new URL(bean.getAvatar())).into(circleHead);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        //判断是否是会员

        vipId = Integer.parseInt(bean.getVip_id());
        if (vipId == 0) {
            myIsvip.setVisibility(View.GONE);
        } else {
            myIsvip.setVisibility(View.VISIBLE);
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
            if (event.getCode() == EventCode.EVENT_N) {
                //刷新头像
                mMvpPresenter.getUserImage(spUserID, mMultipleStateView);
            }
        }
    }
}
