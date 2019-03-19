package com.mouqukeji.hmdeer.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.BuyVipBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.VipListBean;
import com.mouqukeji.hmdeer.contract.activity.MembersCenterContract;
import com.mouqukeji.hmdeer.modle.activity.MembersCenterModel;
import com.mouqukeji.hmdeer.presenter.activity.MemberCenterPresenter;
import com.mouqukeji.hmdeer.ui.widget.ButtomDialogView;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.EventMessage;
import com.mouqukeji.hmdeer.util.GetSPData;
import com.mouqukeji.hmdeer.util.PayResult;
import com.mouqukeji.hmdeer.util.PaymentHelper;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberCenterActivity extends BaseActivity<MemberCenterPresenter, MembersCenterModel> implements MembersCenterContract.View, View.OnClickListener {
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
    @BindView(R.id.member_center_bt)
    Button memberCenterBt;
    @BindView(R.id.member_center_num)
    TextView memberCenterNum;
    @BindView(R.id.member_center_coas_price)
    TextView memberCenterCoasPrice;
    @BindView(R.id.member_center_tv1)
    TextView memberCenterTv1;
    private Animation animation;
    private boolean weekFlag = true;
    private boolean monthFlag;
    private boolean yearFlag;
    private VipListBean vipListBean;

    private static final int SDK_PAY_FLAG = 1;
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
                        //进入支付成功页面
                        Toast.makeText(MemberCenterActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MemberCenterActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private double balance;
    private String spUserID;


    @Override
    protected void initViewAndEvents() {
        mMvpPresenter.vip(mMultipleStateView);
        spUserID = new GetSPData().getSPUserID(this);
        mMvpPresenter.getMoney(spUserID, mMultipleStateView);
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
        memberCenterBt.setOnClickListener(this);
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
                memberCenterPrice.setText(vipListBean.getVips().get(0).getPrice());
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
                //设置标记
                weekFlag = true;
                monthFlag = false;
                yearFlag = false;
                memberCenterPrice.setText("¥" + vipListBean.getVips().get(0).getPrice());
                memberCenterCoasPrice.setText("¥" + vipListBean.getVips().get(0).getCost_price());
                memberCenterNum.setText("无时间限制，" + vipListBean.getVips().get(0).getNum() + "次纵情下单！");
                memberCenterTv1.setText("1.VIP周卡,购买之日起,无时间限制,可随时使用.(即校园2公里范围内下单," +
                        "可享受"+ vipListBean.getVips().get(0).getNum() +"次数免单优惠,超出2公里范围所产生的费用根据实际距离计算).");
                break;
            case R.id.member_center_card_month_iv:
                memberCenterCardWeekCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardMonthCd.setCardBackgroundColor(getResources().getColor(R.color.member_center_blue));
                memberCenterCardYearCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardTop.setBackgroundResource(R.mipmap.mipmap_month_card_big);
                memberCenterPrice.setText(vipListBean.getVips().get(1).getPrice());
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

                //设置标记
                weekFlag = false;
                monthFlag = true;
                yearFlag = false;
                memberCenterPrice.setText("¥" + vipListBean.getVips().get(1).getPrice());
                memberCenterCoasPrice.setText("¥" + vipListBean.getVips().get(1).getCost_price());
                memberCenterNum.setText("无时间限制，" + vipListBean.getVips().get(1).getNum() + "次纵情下单！");
                memberCenterTv1.setText("1.VIP月卡,购买之日起,无时间限制,可随时使用.(即校园2公里范围内下单," +
                        "可享受"+ vipListBean.getVips().get(1).getNum() +"次数免单优惠,超出2公里范围所产生的费用根据实际距离计算).");
                break;
            case R.id.member_center_card_year_iv:
                memberCenterCardWeekCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardMonthCd.setCardBackgroundColor(getResources().getColor(R.color.white));
                memberCenterCardYearCd.setCardBackgroundColor(getResources().getColor(R.color.member_center_blue));
                memberCenterCardTop.setBackgroundResource(R.mipmap.mipmap_year_card_big);
                memberCenterPrice.setText(vipListBean.getVips().get(2).getPrice());
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
                //设置标记
                weekFlag = false;
                monthFlag = false;
                yearFlag = true;
                memberCenterPrice.setText("¥" + vipListBean.getVips().get(2).getPrice());
                memberCenterCoasPrice.setText("¥" + vipListBean.getVips().get(2).getCost_price());
                memberCenterNum.setText("无时间限制，" + vipListBean.getVips().get(2).getNum() + "次纵情下单！");
                memberCenterTv1.setText("1.VIP季卡,购买之日起,无时间限制,可随时使用.(即校园2公里范围内下单," +
                        "可享受"+ vipListBean.getVips().get(2).getNum() +"次数免单优惠,超出2公里范围所产生的费用根据实际距离计算).");
                break;
            case R.id.member_center_bt:
                //购买会员卡
                if (weekFlag) {
                    setPay(vipListBean.getVips().get(0).getPrice(), vipListBean.getVips().get(0).getVip_id());
                } else if (monthFlag) {
                    setPay(vipListBean.getVips().get(1).getPrice(), vipListBean.getVips().get(1).getVip_id());
                } else {
                    setPay(vipListBean.getVips().get(2).getPrice(), vipListBean.getVips().get(2).getVip_id());
                }
                break;
        }
    }


    private void setPay(final String price, final String vipId) {
        View inflate_pay = getLayoutInflater().inflate(R.layout.dialog_member_pay, null);
        final ButtomDialogView buttomDialogView = DialogUtils.payMemberDialog(this, inflate_pay, true, true);
        final TextView dialogPayBt = buttomDialogView.findViewById(R.id.dialog_pay_bt);
        final TextView payMoneyTv = buttomDialogView.findViewById(R.id.pay_money_tv);
        final RadioButton dialogPayWeiXing = buttomDialogView.findViewById(R.id.dialog_pay_weixin);
        final RadioButton dialogPayZhiFuBao = buttomDialogView.findViewById(R.id.dialog_pay_zhifubao);
        dialogPayWeiXing.setChecked(true);
        //设置价钱
        payMoneyTv.setText(price);
        //点击支付
        dialogPayBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialogPayWeiXing.isChecked()) {
                    pay_type = "1";
                    mMvpPresenter.buyVip(vipId, spUserID, price, "1", mMultipleStateView);//微信支付接口
                } else if (dialogPayZhiFuBao.isChecked()) {
                    pay_type = "2";
                    mMvpPresenter.buyVip(vipId, spUserID, price, "2", mMultipleStateView);//支付宝支付接口
                }
                buttomDialogView.dismiss();
            }
        });
    }


    @Override
    protected boolean isRegisteredEventBus() {
        return true;
    }

    @Override
    public void vip(VipListBean bean) {
        vipListBean = bean;
        memberCenterPrice.setText("¥" + vipListBean.getVips().get(0).getPrice());
        memberCenterCoasPrice.setText("¥" + vipListBean.getVips().get(0).getCost_price());
        memberCenterNum.setText("无时间限制，" + vipListBean.getVips().get(0).getNum() + "次纵情下单！");
        //设置删除线
        memberCenterCoasPrice.setPaintFlags(memberCenterCoasPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    @Override
    public void buyVip(BuyVipBean bean) {
        if (pay_type.equals("1")) {
            PaymentHelper.startWeChatPay(this, bean);//调取微信支付
        } else {
            PaymentHelper.aliPay(this, bean.getPay().getPayInfo(), mHandler);//调取支付宝支付
        }
    }

    @Override
    public void getMoney(PackageBean bean) {
        balance = Double.parseDouble(bean.getBalance());
    }


    @Override
    public void onReceiveEvent(EventMessage event) {
        super.onReceiveEvent(event);
        if (event != null) {
            Toast.makeText(MemberCenterActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
        }
    }


}
