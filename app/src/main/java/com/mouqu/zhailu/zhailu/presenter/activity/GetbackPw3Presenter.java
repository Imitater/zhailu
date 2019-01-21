package com.mouqu.zhailu.zhailu.presenter.activity;


import android.app.Activity;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.bean.resetPasswordBean;
import com.mouqu.zhailu.zhailu.contract.activity.GetbackPw3Contract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class GetbackPw3Presenter extends  GetbackPw3Contract.Presenter{

    @Override
    public void resetPassword(final Activity activity, String number, String code, String password, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.resetPassword(number,code,password), new RxObserverListener<resetPasswordBean>(mView) {
            @Override
            public void onSuccess(resetPasswordBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.resetPassword(result);
            }

            @Override
            public void onBusinessError(ErrorBean errorBean) {
                Toast.makeText(activity, "密码重置错误", Toast.LENGTH_SHORT).show();
                super.onBusinessError(errorBean);
            }

        }));
    }
}

