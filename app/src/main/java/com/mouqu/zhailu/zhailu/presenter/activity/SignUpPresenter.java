package com.mouqu.zhailu.zhailu.presenter.activity;


import android.app.Activity;
import android.widget.Toast;
import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.bean.RegisteredBean;
import com.mouqu.zhailu.zhailu.contract.activity.SignUpContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class SignUpPresenter extends SignUpContract.Presenter {

    @Override
    public void getCode(final Activity activity, String number, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getCode(number), new RxObserverListener<CodeBean>(mView) {
            @Override
            public void onSuccess(CodeBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getCode(result);
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(activity, "短信验证码请求发送异常", Toast.LENGTH_SHORT).show();
                super.onError(e);
            }

            @Override
            public void onBeing() {
                Toast.makeText(activity, "该手机号已存在", Toast.LENGTH_SHORT).show();
                super.onBeing();
            }
        }));
    }

    @Override
    public void registered(String number, String code, String password, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.registered(number,code,password), new RxObserverListener<RegisteredBean>(mView) {
            @Override
            public void onSuccess(RegisteredBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.registered(result);
            }
        }));
    }
}

