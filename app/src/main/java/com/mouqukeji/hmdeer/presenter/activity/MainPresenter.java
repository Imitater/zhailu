package com.mouqukeji.hmdeer.presenter.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.CheckVersionBean;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.contract.activity.MainContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.activity.SignInActivity;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;
import com.mouqukeji.hmdeer.util.PhoneUtils;

public class MainPresenter extends MainContract.Presenter {

    @Override
    public void checkVersion(final Context context, String platform, String version_code, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.checkVersion(context,platform,version_code), new RxObserverListener<CheckVersionBean>(mView) {
            @Override
            public void onSuccess(CheckVersionBean result) {
                if (result.getVersionInfo()!=null||!result.getVersionInfo().getVersion_code().equals(PhoneUtils.getVersionName(context))) {
                    mView.checkVersion(result);
                }else{
                    mView.isNeedUp();
                }
            }
        }));
    }
}

