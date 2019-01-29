package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;
import android.content.Intent;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.contract.activity.MainContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.activity.SignInActivity;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class MainPresenter extends MainContract.Presenter {
    @Override
    public void getToken(final Activity activity, String token, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getToken(token), new RxObserverListener<TokenBean>(mView) {
            @Override
            public void onSuccess(TokenBean result) {

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

