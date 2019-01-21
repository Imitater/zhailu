package com.mouqu.zhailu.zhailu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;
import com.mouqu.zhailu.zhailu.util.ClassReflectHelper;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment implements IBaseView, View.OnTouchListener {
    private View mContentView;
    private Context mContext;
    private Unbinder mUnbinder;
    protected P mMvpPresenter;
    protected M mModel;
    protected MultipleStatusView mMultipleStateView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (setLayoutResourceID() != 0) {
            mMultipleStateView = new MultipleStatusView(getActivity());
            mContentView = inflater.inflate(setLayoutResourceID(), mMultipleStateView, false);
            mContentView.setOnTouchListener(this);
            mContext = getContext();
            mUnbinder = ButterKnife.bind(this, mContentView);
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

        init();
        setUpView();
        setUpData();
        return mContentView;
    }

    protected abstract void initViewAndEvents();

    /**
     * 此方法用于返回Fragment设置ContentView的布局文件资源ID
     *
     * @return 布局文件资源ID
     */
    protected abstract int setLayoutResourceID();

    /**
     * 一些View的相关操作
     */
    protected abstract void setUpView();

    /**
     * 一些Data的相关操作
     */
    protected abstract void setUpData();

    /**
     * 此方法用于初始化成员变量及获取Intent传递过来的数据
     * 注意：这个方法中不能调用所有的View，因为View还没有被初始化，要使用View在initView方法中调用
     */
    protected void init() {
    }

    public View getContentView() {
        return mContentView;
    }

    public Context getMContext() {
        return mContext;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }


    @Override
    public Context getContext() {
        return super.getContext();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
