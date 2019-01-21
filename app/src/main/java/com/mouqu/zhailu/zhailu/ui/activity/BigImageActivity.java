package com.mouqu.zhailu.zhailu.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.base.BaseActivity;
import com.mouqu.zhailu.zhailu.contract.activity.BigImageContract;
import com.mouqu.zhailu.zhailu.modle.activity.BigImageModel;
import com.mouqu.zhailu.zhailu.presenter.activity.BigImagePresenter;


public class BigImageActivity extends BaseActivity<BigImagePresenter, BigImageModel> implements BigImageContract.View, View.OnClickListener {
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
