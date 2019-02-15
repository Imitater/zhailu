package com.mouqukeji.hmdeer.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberCenterActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.action_title)
    TextView actionTitle;
    @BindView(R.id.member_center_more)
    ImageView memberCenterMore;
    @BindView(R.id.member_center_card_top)
    ImageView memberCenterCardTop;
    @BindView(R.id.member_center_card_week_iv)
    ImageView memberCenterCardWeekIv;
    @BindView(R.id.member_center_card_week_cd)
    CardView memberCenterCardWeekCd;
    @BindView(R.id.member_center_card_month_iv)
    ImageView memberCenterCardMonthIv;
    @BindView(R.id.member_center_card_month_cd)
    CardView memberCenterCardMonthCd;
    @BindView(R.id.member_center_card_year_iv)
    ImageView memberCenterCardYearIv;
    @BindView(R.id.member_center_card_year_cd)
    CardView memberCenterCardYearCd;
    @BindView(R.id.member_center_price)
    TextView memberCenterPrice;
    private Animation animation;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_member_center;
    }

    @Override
    protected void setUpView() {
        memberCenterCardWeekCd.setCardBackgroundColor(getResources().getColor(R.color.member_center_blue));
        //默认周卡
        ViewCompat.animate(memberCenterCardWeekCd).setDuration(100)
                .scaleX(1.1f)
                .scaleY(1.1f)
                .start();
        ViewCompat.animate(memberCenterCardMonthCd).setDuration(100)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .start();
        ViewCompat.animate(memberCenterCardYearCd).setDuration(100)
                .scaleX(1.0f)
                .scaleY(1.0f)
                .start();
        initListener();
    }

    private void initListener() {
        memberCenterCardWeekIv.setOnClickListener(this);
        memberCenterCardMonthIv.setOnClickListener(this);
        memberCenterCardYearIv.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.member_center_card_week_iv:
                memberCenterCardWeekCd.setCardBackgroundColor(getResources().getColor(R.color.member_center_blue));
                memberCenterCardMonthCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardYearCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardTop.setBackgroundResource(R.mipmap.mipmap_week_card_big);
                memberCenterPrice.setText("¥9.9  ");
                //放大动画
                ViewCompat.animate(memberCenterCardWeekCd).setDuration(100)
                        .scaleX(1.1f)
                        .scaleY(1.1f)
                        .start();
                ViewCompat.animate(memberCenterCardMonthCd).setDuration(100)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .start();
                ViewCompat.animate(memberCenterCardYearCd).setDuration(100)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .start();
                break;
            case R.id.member_center_card_month_iv:
                memberCenterCardWeekCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardMonthCd.setCardBackgroundColor(getResources().getColor(R.color.member_center_blue));
                memberCenterCardYearCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardTop.setBackgroundResource(R.mipmap.mipmap_month_card_big);
                memberCenterPrice.setText("¥28.9");
                //放大动画
                ViewCompat.animate(memberCenterCardWeekCd).setDuration(100)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .start();
                ViewCompat.animate(memberCenterCardMonthCd).setDuration(100)
                        .scaleX(1.1f)
                        .scaleY(1.1f)
                        .start();
                ViewCompat.animate(memberCenterCardYearCd).setDuration(100)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .start();
                break;
            case R.id.member_center_card_year_iv:
                memberCenterCardWeekCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardMonthCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardYearCd.setCardBackgroundColor(getResources().getColor(R.color.member_center_blue));
                memberCenterCardTop.setBackgroundResource(R.mipmap.mipmap_year_card_big);
                memberCenterPrice.setText("¥78.9");
                //放大动画
                ViewCompat.animate(memberCenterCardWeekCd).setDuration(100)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .start();
                ViewCompat.animate(memberCenterCardMonthCd).setDuration(100)
                        .scaleX(1.0f)
                        .scaleY(1.0f)
                        .start();
                ViewCompat.animate(memberCenterCardYearCd).setDuration(100)
                        .scaleX(1.1f)
                        .scaleY(1.1f)
                        .start();
                break;
        }
    }

}
