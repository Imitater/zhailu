package com.mouqu.zhailu.zhailu.base;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.MyApplication;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.net.BaseObserverListener;
import com.mouqu.zhailu.zhailu.util.DataSaveSP;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;


public abstract class RxObserverListener<T> implements BaseObserverListener<T> {
    private IBaseView mView;

    protected RxObserverListener(IBaseView view) {
        this.mView = view;
    }

    /**
     * 统一处理异常情况：包括没网、数据返回错误等
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        RetrofitException.ResponseThrowable responseThrowable = RetrofitException.getResponseThrowable(e);
        Log.e("TAG", "e.code=" + responseThrowable.code + responseThrowable.message);
        ErrorBean errorBean = new ErrorBean();
        errorBean.setMsg(responseThrowable.message);
        errorBean.setCode(responseThrowable.code + "");
        if (mView != null) {
            mView.showException(errorBean);
            Toast.makeText(MyApplication.getContext(), responseThrowable.message, Toast.LENGTH_SHORT);
        }
    }

    /**
     * 接口http结果返回200，但是后台数据返回错误。
     * @param errorBean
     */
    @Override
    public void onBusinessError(ErrorBean errorBean) {
        if (mView != null) {
            mView.showBusinessError(errorBean);
//            CommonUtils.makeEventToast(BaseApplication.getInstance(), errorBean.getMsg(), false);
            Log.e("TAG", "onBusinessError msg=" + errorBean.getMsg());
        }
    }

    @Override
    public void onComplete() {

    }
    @Override
    public void onReLoad() {

    }

    @Override
    public void onBeing() {

    }
}
