package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.contract.activity.MainContract;
import com.mouqukeji.hmdeer.contract.activity.SplashContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class SplashPresenter extends SplashContract.Presenter {
    @Override
    public void getToken(final Activity activity, String token, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getToken(token), new RxObserverListener<TokenBean>(mView) {
            @Override
            public void onSuccess(TokenBean result) {
                mView.getToken(result);
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.restart();
            }
        }));
    }
}

