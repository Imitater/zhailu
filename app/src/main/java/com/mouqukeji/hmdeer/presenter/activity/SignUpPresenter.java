package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;
import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.RegisteredBean;
import com.mouqukeji.hmdeer.contract.activity.SignUpContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class SignUpPresenter extends SignUpContract.Presenter {

    @Override
    public void getCode(final Activity activity, String number,String type, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCode(number,type), new RxObserverListener<CodeBean>(mView) {
            @Override
            public void onSuccess(CodeBean result) {
                     mView.getCode(result);
                }

            @Override
            public void onBeing() {
                super.onBeing();
                mView.isRegistered();
            }

            @Override
            public void onReLoad() {
                super.onReLoad();
                mView.isSend();
            }
        }));
    }

    @Override
    public void registered(String number, String code, String password, String school,final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.registered(number, code, password,school), new RxObserverListener<RegisteredBean>(mView) {
            @Override
            public void onSuccess(RegisteredBean result) {
                mView.registered(result);
            }
        }));
    }
}

