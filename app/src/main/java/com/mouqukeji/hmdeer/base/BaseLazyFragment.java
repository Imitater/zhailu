package com.mouqukeji.hmdeer.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;
import com.mouqukeji.hmdeer.util.ClassReflectHelper;
import com.mouqukeji.hmdeer.util.EventBusUtils;
import com.mouqukeji.hmdeer.util.EventMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jessyan.autosize.AutoSizeConfig;

public abstract class BaseLazyFragment<P extends BasePresenter, M extends BaseModel> extends Fragment implements IBaseView, View.OnTouchListener {
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
        if (isRegisteredEventBus()) {
            EventBusUtils.register(this);
        }
        initViewAndEvents();
        init();
        setUpView();
        setUpData();
        mIsprepared = true;
        lazyLoad();
        AutoSizeConfig.getInstance().setCustomFragment(true);

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

    /**
     * 是否注册事件分发
     *
     * @return true 注册；false 不注册，默认不注册
     */
    protected boolean isRegisteredEventBus() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        //注销 eventbus
        if (isRegisteredEventBus()) {
            EventBusUtils.unregister(this);
        }
    }

    @Override
    public void showLoading(MultipleStatusView multipleStatusView, String msg) {

    }

    /**
     * 接收到分发的事件
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveEvent(EventMessage event) {
    }

    /**
     * 接受到分发的粘性事件
     *
     * @param event 粘性事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onReceiveStickyEvent(EventMessage event) {
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


    /**
     * 判断当前的Fragment是否可见(相对于其他的Fragment)
     */
    protected boolean mIsVisible;

    /**
     * 标志位，标志已经初始化完成
     */
    protected boolean mIsprepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    protected boolean mHasLoadedOnce;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        //设置Fragment的可见状态
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {//getUserVisibleHint获取Fragment可见状态
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }

        if (isResumed()) {
            onVisibilityChangedToUser(isVisibleToUser);
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
        stopLoad();
    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();

    /**
     * 当视图已经对用户不可见并且加载过数据，如果需要在切换到其他页面时停止加载数据，可以覆写此方法
     */
    protected void stopLoad() {
    }

    //region 神策埋点需要，统计Fragement 可见时间
    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            onVisibilityChangedToUser(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onVisibilityChangedToUser(false);
        }
    }

    /**
     * 当Fragment对用户的可见性发生了改变的时候就会回调此方法
     *
     * @param isVisibleToUser true：用户能看见当前Fragment；false：用户看不见当前Fragment
     */
    public void onVisibilityChangedToUser(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            visibleToUser();
        } else {
            inVisibleToUser();
        }
    }

    protected void visibleToUser() {

    }

    protected void inVisibleToUser() {

    }
    //endregion


}
