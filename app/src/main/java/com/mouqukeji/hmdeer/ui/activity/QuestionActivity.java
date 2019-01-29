package com.mouqukeji.hmdeer.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.contract.activity.QuestionContract;
import com.mouqukeji.hmdeer.modle.activity.QuestionModel;
import com.mouqukeji.hmdeer.presenter.activity.QuestionPresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends BaseActivity<QuestionPresenter, QuestionModel> implements QuestionContract.View, View.OnClickListener {
    @BindView(R.id.question_actionbar)
    MyActionBar questionActionbar;
    @BindView(R.id.question_1_iv)
    ImageView question1Iv;
    @BindView(R.id.question_1)
    LinearLayout question1;
    @BindView(R.id.question_1_item)
    LinearLayout question1Item;
    @BindView(R.id.question_2_iv)
    ImageView question2Iv;
    @BindView(R.id.question_2)
    LinearLayout question2;
    @BindView(R.id.question_2_item)
    LinearLayout question2Item;
    @BindView(R.id.question_3_iv)
    ImageView question3Iv;
    @BindView(R.id.question_3)
    LinearLayout question3;
    @BindView(R.id.question_3_item)
    LinearLayout question3Item;
    private boolean question1flag = false;
    private boolean question2flag = false;
    private boolean question3flag=false;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_question;
    }

    @Override
    protected void setUpView() {
        questionActionbar.setTitle("常见问题");
        setListener();
    }

    private void setListener() {
        question1.setOnClickListener(this);
        question2.setOnClickListener(this);
        question3.setOnClickListener(this);
    }

    @Override
    protected void setUpData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_1://问题1
                if (!question1flag) {
                    question1flag = true;
                    question2flag=false;
                    question3flag=false;
                    question1Item.setVisibility(View.VISIBLE);
                    question2Item.setVisibility(View.GONE);
                    question3Item.setVisibility(View.GONE);
                    question1Iv.setBackgroundResource(R.mipmap.mipmap_question_down);
                    question2Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question3Iv.setBackgroundResource(R.mipmap.mipmap_next);
                } else {
                    question1flag = false;
                    question2flag=false;
                    question3flag=false;
                    question1Item.setVisibility(View.GONE);
                    question2Item.setVisibility(View.GONE);
                    question3Item.setVisibility(View.GONE);
                    question1Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question2Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question3Iv.setBackgroundResource(R.mipmap.mipmap_next);
                }
                break;
            case R.id.question_2://问题2
                if (!question2flag) {
                    question2flag = true;
                    question1flag = false;
                    question3flag=false;
                    question2Item.setVisibility(View.VISIBLE);
                    question1Item.setVisibility(View.GONE);
                    question3Item.setVisibility(View.GONE);
                    question2Iv.setBackgroundResource(R.mipmap.mipmap_question_down);
                    question1Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question3Iv.setBackgroundResource(R.mipmap.mipmap_next);
                } else {
                    question2flag = false;
                    question1flag = false;
                    question3flag=false;
                    question2Item.setVisibility(View.GONE);
                    question1Item.setVisibility(View.GONE);
                    question3Item.setVisibility(View.GONE);
                    question2Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question1Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question3Iv.setBackgroundResource(R.mipmap.mipmap_next);
                }
                break;
            case R.id.question_3://问题3
                if (!question3flag) {
                    question2flag = false;
                    question1flag = false;
                    question3flag = true;
                    question3Item.setVisibility(View.VISIBLE);
                    question1Item.setVisibility(View.GONE);
                    question2Item.setVisibility(View.GONE);
                    question3Iv.setBackgroundResource(R.mipmap.mipmap_question_down);
                    question1Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question2Iv.setBackgroundResource(R.mipmap.mipmap_next);
                }else{
                    question3flag = false;
                    question2flag = false;
                    question1flag = false;
                    question3Item.setVisibility(View.GONE);
                    question1Item.setVisibility(View.GONE);
                    question2Item.setVisibility(View.GONE);
                    question3Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question1Iv.setBackgroundResource(R.mipmap.mipmap_next);
                    question2Iv.setBackgroundResource(R.mipmap.mipmap_next);
                }
                break;
        }
    }


}
