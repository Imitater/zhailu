package com.mouqu.zhailu.zhailu.modle.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.bean.ChangePasswordBean;
import com.mouqu.zhailu.zhailu.contract.activity.ChangePwContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class ChangePwModel implements ChangePwContract.Model {

    @Override
    public Observable<BaseHttpResponse<ChangePasswordBean>> changePassword(Activity activity,String id, String password, String newpassword) {
        return RetrofitManager.getInstance().getRequestService().changePassword(id,password,newpassword);
    }
}
