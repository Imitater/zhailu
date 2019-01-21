package com.mouqu.zhailu.zhailu.presenter.fragment;


import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.contract.fragment.AllOrderContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class AllOrderPresenter extends AllOrderContract.Presenter {

    @Override
    public void getIndent(String user_id,final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getIndent(user_id), new RxObserverListener<AllOrderBean>(mView) {
            @Override
            public void onSuccess(AllOrderBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                if (result.getTasks().size()!=0) {
                    mView.getIndent(result);
                }else{
                    mView.getEmpty();
                }
            }
        }));
    }

    @Override
    public void getIndentNext(String user_id, String progress, String page, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getIndentNext(user_id,progress,page), new RxObserverListener<AllOrderBean>(mView) {
            @Override
            public void onSuccess(AllOrderBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getIndentNext(result);
            }

        }));
    }

}

