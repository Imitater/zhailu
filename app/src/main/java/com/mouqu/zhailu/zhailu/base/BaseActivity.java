package com.mouqu.zhailu.zhailu.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;


import com.mouqu.zhailu.zhailu.R;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;
import com.mouqu.zhailu.zhailu.util.ClassReflectHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends FragmentActivity implements IBaseView {
    private Unbinder mUnbinder;
    protected P mMvpPresenter;
    protected M mModel;
    protected MultipleStatusView mMultipleStateView;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setLayoutResourceID() != 0) {
            mMultipleStateView = new MultipleStatusView(this);
            View inflate = View.inflate(this, setLayoutResourceID(), mMultipleStateView);
            setContentView(inflate);
             mUnbinder = ButterKnife.bind(this,inflate);
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }
        //MVP
        mMvpPresenter = ClassReflectHelper.getT(this, 0);
        mModel = ClassReflectHelper.getT(this, 1);
        if (this instanceof IBaseView) {
            if (mMvpPresenter != null && mModel != null) {
                mMvpPresenter.setVM(this, mModel);
            }
        }
        initViewAndEvents();


        //actionbar 设置
        initActionBar();
        setUpView();
        setUpData();

    }


    protected abstract void initViewAndEvents();


    public void initActionBar() {
        View action_back = findViewById(R.id.action_back);
        action_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    protected abstract int setLayoutResourceID();




    //绑定view
    protected abstract void setUpView();

    //绑定数据
    protected abstract void setUpData();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
    @Override
    public void showLoading(MultipleStatusView multipleStatusView, String msg) {

    }


    @Override
    public void hideLoading() {

    }
    @Override
    public void showBusinessError(ErrorBean error) {
        mMultipleStateView.showError();
    }

    @Override
    public void showException(ErrorBean error) {
        mMultipleStateView.showNoNetwork();
    }
}
