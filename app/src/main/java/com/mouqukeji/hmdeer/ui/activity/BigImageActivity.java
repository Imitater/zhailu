package com.mouqukeji.hmdeer.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.base.BaseActivity;
import com.mouqukeji.hmdeer.contract.activity.BigImageContract;
import com.mouqukeji.hmdeer.modle.activity.BigImageModel;
import com.mouqukeji.hmdeer.presenter.activity.BigImagePresenter;
import com.mouqukeji.hmdeer.ui.widget.MyActionBar;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BigImageActivity extends BaseActivity<BigImagePresenter, BigImageModel> implements BigImageContract.View, View.OnClickListener {
    @BindView(R.id.big_actionbar)
    MyActionBar bigActionbar;
    @BindView(R.id.bigimage_iv)
    ImageView bigimageIv;

    @Override
    protected void initViewAndEvents() {

    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_bigimage;
    }

    protected void setUpView() {
        ImageView bigImageIv = findViewById(R.id.bigimage_iv);
        //设置图片
        Glide.with(this).load(R.mipmap.test).into(bigImageIv);
    }

    @Override
    protected void setUpData() {

    }


    @Override
    public void onClick(View v) {

    }


}
