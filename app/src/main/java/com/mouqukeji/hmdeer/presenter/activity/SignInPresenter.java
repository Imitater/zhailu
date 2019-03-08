package com.mouqukeji.hmdeer.presenter.activity;


import android.text.TextUtils;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.bean.PushMesgBean;
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.contract.activity.SignInContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class SignInPresenter extends SignInContract.Presenter {

    @Override
    public void signIn(String number,String password,String did,String platform
            , String app_version, String device_model,String os_version,final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.signIn(number, password,did,platform,app_version,device_model,os_version), new RxObserverListener<SigninBean>(mView) {
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

    @Override
    public void pushMsg(String registration_id, String did, String uuid,MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.pushMsg(registration_id,did,uuid), new RxObserverListener<PushMesgBean>(mView) {
            @Override
            public void onSuccess(PushMesgBean result) {
                mView.pushMsg(result);
            }
        }));
    }


}

