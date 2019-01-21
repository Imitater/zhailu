package com.mouqu.zhailu.zhailu.presenter.activity;


import android.app.Activity;
import android.widget.Toast;

import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.ChangePasswordBean;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.contract.activity.ChangePwContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class ChangePwPresenter extends  ChangePwContract.Presenter{

    @Override
    public void changePassword(final Activity activity, String id, String password, String newpassword, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.changePassword(activity,id,password,newpassword), new RxObserverListener<ChangePasswordBean>(mView) {
            @Override
            public void onSuccess(ChangePasswordBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.changePassword(result);
            }

            @Override
            public void onBusinessError(ErrorBean errorBean) {
                Toast.makeText(activity, "旧密码输入错误", Toast.LENGTH_SHORT).show();
                super.onBusinessError(errorBean);
            }

        }));
    }
}

