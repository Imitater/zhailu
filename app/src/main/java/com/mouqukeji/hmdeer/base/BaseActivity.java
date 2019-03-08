package com.mouqukeji.hmdeer.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;


import com.mouqukeji.hmdeer.R;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;
import com.mouqukeji.hmdeer.util.ActivityController;
import com.mouqukeji.hmdeer.util.ClassReflectHelper;
import com.mouqukeji.hmdeer.util.EventBusUtils;
import com.mouqukeji.hmdeer.util.EventMessage;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends FragmentActivity implements IBaseView {
    private Unbinder mUnbinder;
    protected P mMvpPresenter;
    protected M mModel;
    protected MultipleStatusView mMultipleStateView;
    private static final String TAG="lifescycle";
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setLayoutResourceID() != 0) {
            mMultipleStateView = new MultipleStatusView(this);
            View inflate = View.inflate(this, setLayoutResourceID(), mMultipleStateView);
            setContentView(inflate);
            mUnbinder = ButterKnife.bind(this, inflate);
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
        //actionbar 设置
        initActionBar();
        setUpView();
        setUpData();

        ActivityController.getInstance().addActivity(this);
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
    public void showLoading(MultipleStatusView multipleStatusView, String msg) {

    }

    protected boolean isRegisteredEventBus() {
        return false;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (isRegisteredEventBus()) {
            EventBusUtils.unregister(this);
        }
        Log.d(TAG, "BaseActivity onDestroy Invoke...");
        ActivityController.getInstance().removeActivity(this);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(TAG, "BaseActivity onBackPressed Invoke...");
        ActivityController.getInstance().removeActivity(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "BaseActivity onStart Invoke...");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "BaseActivity onRestart Invoke...");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "BaseActivity onResume Invoke...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "BaseActivity onPause Invoke...");
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "BaseActivity onLowMemory Invoke...");
    }
}
