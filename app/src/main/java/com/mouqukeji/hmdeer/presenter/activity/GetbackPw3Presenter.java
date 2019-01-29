package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;
import android.widget.Toast;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.bean.resetPasswordBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw3Contract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class GetbackPw3Presenter extends  GetbackPw3Contract.Presenter{

    @Override
    public void resetPassword(final Activity activity, String number, String code, String password, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.resetPassword(number,code,password), new RxObserverListener<resetPasswordBean>(mView) {
            @Override
            public void onSuccess(resetPasswordBean result) {
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

