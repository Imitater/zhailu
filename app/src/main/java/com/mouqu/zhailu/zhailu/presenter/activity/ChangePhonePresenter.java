package com.mouqu.zhailu.zhailu.presenter.activity;


import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.ChangePhoneBean;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.bean.TokenBean;
import com.mouqu.zhailu.zhailu.contract.activity.ChangePhoneContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.activity.SignInActivity;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class ChangePhonePresenter extends  ChangePhoneContract.Presenter{

    @Override
    public void changePhone(String id, String number, String code, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.changePhone(id,number,code), new RxObserverListener<ChangePhoneBean>(mView) {
            @Override
            public void onSuccess(ChangePhoneBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.changePhone(result);
            }
        }));
    }

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
            public void onBusinessError(ErrorBean errorBean) {
                    if (errorBean.getCode().equals(10001)&&errorBean.getMsg().equals("该手机号已存在")) {
                        Toast.makeText(activity, "该手机号已存在", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity, "短信验证码请求发送异常", Toast.LENGTH_SHORT).show();
                    }
                    super.onBusinessError(errorBean);
            }
        }));
    }
}

