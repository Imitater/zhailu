package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;
import android.widget.Toast;
import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.RegisteredBean;
import com.mouqukeji.hmdeer.contract.activity.SignUpContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class SignUpPresenter extends SignUpContract.Presenter {

    @Override
    public void getCode(final Activity activity, String number, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCode(number), new RxObserverListener<CodeBean>(mView) {
            @Override
            public void onSuccess(CodeBean result) {
                mView.getCode(result);
            }
        }));
    }

    @Override
    public void registered(String number, String code, String password, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.registered(number,code,password), new RxObserverListener<RegisteredBean>(mView) {
            @Override
            public void onSuccess(RegisteredBean result) {
                mView.registered(result);
            }
        }));
    }
}

