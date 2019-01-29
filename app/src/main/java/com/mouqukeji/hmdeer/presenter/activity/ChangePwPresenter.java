package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;
import android.widget.Toast;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ChangePasswordBean;
import com.mouqukeji.hmdeer.bean.ErrorBean;
import com.mouqukeji.hmdeer.contract.activity.ChangePwContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class ChangePwPresenter extends  ChangePwContract.Presenter{

    @Override
    public void changePassword(final Activity activity, String id, String password, String newpassword, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.changePassword(activity,id,password,newpassword), new RxObserverListener<ChangePasswordBean>(mView) {
            @Override
            public void onSuccess(ChangePasswordBean result) {
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

