package com.mouqu.zhailu.zhailu.modle.activity;


import com.mouqu.zhailu.zhailu.bean.SigninBean;
import com.mouqu.zhailu.zhailu.contract.activity.SignInContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class SigninModel implements SignInContract.Model {

    @Override
    public Observable<BaseHttpResponse<SigninBean>> signIn(String number, String password) {
        return RetrofitManager.getInstance().getRequestService().login(number,password);
    }
}
