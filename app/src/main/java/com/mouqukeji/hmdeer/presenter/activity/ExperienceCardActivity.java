package com.mouqukeji.hmdeer.presenter.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.bean.ConvertGoodsBean;
import com.mouqukeji.hmdeer.bean.DetailIntegralBean;
import com.mouqukeji.hmdeer.contract.activity.ExperienceCardContract;
import com.mouqukeji.hmdeer.modle.activity.ExperienceCardModel;
import com.mouqukeji.hmdeer.ui.fragment.DiscountCompleteFragment;
import com.mouqukeji.hmdeer.util.DialogUtils;
import com.mouqukeji.hmdeer.util.GetSPData;

import butterknife.BindView;

public class ExperienceCardActivity extends BaseActivity<ExperienceCardPresenter, ExperienceCardModel> implements ExperienceCardContract.View, View.OnClickListener {
    @BindView(R.id.action_back)
    View actionBack;
    @BindView(R.id.experiencecard_type_money)
    TextView experiencecardTypeMoney;
    @BindView(R.id.experiencecard_time)
    TextView experiencecardTime;
    @BindView(R.id.experiencecard_use)
    TextView experiencecardUse;
    @BindView(R.id.experiencecard_use_contact)
    TextView experiencecardUseContact;
    @BindView(R.id.experiencecard_use_guize)
    TextView experiencecardUseGuize;
    @BindView(R.id.experiencecard_bt)
    Button experiencecardBt;
    @BindView(R.id.discount_background)
    LinearLayout discountBackground;
    @BindView(R.id.framelayout)
    FrameLayout framelayout;
    private String good_id;
    private String spUserID;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.action_back:
                finish();
                break;
            case R.id.experiencecard_bt:
                //领取
                View inflate_items = getLayoutInflater().inflate(R.layout.dialog_discount, null);
                DialogUtils.discountDialog(this, inflate_items, true, true, mMvpPresenter, mMultipleStateView, good_id, spUserID);
                break;
        }
    }

    @Override
    protected void initViewAndEvents() {
        spUserID = new GetSPData().getSPUserID(this);
        Intent intent = getIntent();
        good_id = intent.getStringExtra("good_id");
        mMvpPresenter.detailIntegral(good_id, mMultipleStateView);
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_experiencecard;
    }

    @Override
    protected void setUpView() {
        initListener();
    }

    private void initListener() {
        actionBack.setOnClickListener(this);
        experiencecardBt.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void detailIntegral(DetailIntegralBean bean) {

    }

    @Override
    public void convertGoods(ConvertGoodsBean bean) {
        framelayout.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.framelayout, new DiscountCompleteFragment(),"discount_complete").commit();
    }

    @Override
    public void isEnought() {
        Toast.makeText(this,"积分不足",Toast.LENGTH_SHORT).show();
    }


}
