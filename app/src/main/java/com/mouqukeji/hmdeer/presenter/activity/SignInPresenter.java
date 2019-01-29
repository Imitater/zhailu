package com.mouqukeji.hmdeer.presenter.activity;


import android.text.TextUtils;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.contract.activity.SignInContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class SignInPresenter extends  SignInContract.Presenter{

    @Override
    public void signIn(String number, String password, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.signIn(number,password), new RxObserverListener<SigninBean>(mView) {
            @Override
            public void onSuccess(SigninBean result) {

                    mView.signIn(result);
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.error();
            }
        }));
    }
}

