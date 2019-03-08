package com.mouqukeji.hmdeer.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.MyVipBean;
import com.mouqukeji.hmdeer.contract.activity.MyMembersContract;
import com.mouqukeji.hmdeer.modle.activity.MyMembersModel;
import com.mouqukeji.hmdeer.presenter.activity.MyMemberPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;
import com.mouqukeji.hmdeer.util.DateUtils;
import com.mouqukeji.hmdeer.util.EventCode;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.mouqukeji.hmdeer.util.EventBusUtils.post;

public class MyMembersActivity extends BaseActivity<MyMemberPresenter, MyMembersModel> implements MyMembersContract.View, View.OnClickListener {
    @BindView(R.id.member_myactionbar)
    MyActionBar memberMyactionbar;
    @BindView(R.id.member_count)
    TextView memberCount;
    @BindView(R.id.member_contact)
    TextView memberContact;
    @BindView(R.id.member_bt)
    Button memberBt;
    @BindView(R.id.mymember_card)
    ImageView mymemberCard;
    @BindView(R.id.mymember_card_type)
    TextView mymemberCardType;

    @Override
    protected void initViewAndEvents() {
        String spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.myVip(spUserID, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_mymembers;
    }

    @Override
    protected void setUpView() {
        memberMyactionbar.setTitle("我的会员卡");
        initListener();
    }

    private void initListener() {
        memberBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.member_bt:
                EventMessage eventMessage = new EventMessage(EventCode.EVENT_G, 1);
                post(eventMessage);
                finish();
                break;
        }
    }

    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void myVip(MyVipBean bean) {
        memberCount.setText(bean.getVip().getVip_num());
        Glide.with(this).load(bean.getVip().getVip_image()).into(mymemberCard);
        mymemberCardType.setText("您的宅鹿"+bean.getVip().getVip_name());
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String vip_term = bean.getVip().getVip_term();
            Date vipday = df.parse(vip_term);
            String data = DateUtils.getData();
            Date today = df.parse(data);
            memberContact.setText("有限期限" + (vipday.getTime() - today.getTime()) / (60 * 60 * 1000 * 24) + "天，可享受" + bean.getVip().getVip_num() + "次免单优惠，每天不限次数。");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
