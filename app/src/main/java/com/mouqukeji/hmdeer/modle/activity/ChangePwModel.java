package com.mouqukeji.hmdeer.modle.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.bean.ChangePasswordBean;
import com.mouqukeji.hmdeer.contract.activity.ChangePwContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class ChangePwModel implements ChangePwContract.Model {

    @Override
    public Observable<BaseHttpResponse<ChangePasswordBean>> changePassword(Activity activity,String id, String password, String newpassword) {
        return RetrofitManager.getInstance().getRequestService().changePassword(id,password,newpassword);
    }
}
