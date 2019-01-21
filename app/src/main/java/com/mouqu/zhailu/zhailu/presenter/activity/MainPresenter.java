package com.mouqu.zhailu.zhailu.presenter.activity;


import android.app.Activity;
import android.content.Intent;

import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.TokenBean;
import com.mouqu.zhailu.zhailu.contract.activity.MainContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.activity.SignInActivity;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class MainPresenter extends MainContract.Presenter {
    @Override
    public void getToken(final Activity activity, String token, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getToken(token), new RxObserverListener<TokenBean>(mView) {
            @Override
            public void onSuccess(TokenBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getToken(result);
            }
            //token 错误 重新登录
            @Override
            public void onReLoad() {
                activity.finish();
                Intent intent=new Intent(activity,SignInActivity.class);
                activity.startActivity(intent);
            }
        }));
    }
}

