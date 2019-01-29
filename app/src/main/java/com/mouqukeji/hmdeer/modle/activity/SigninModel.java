package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.contract.activity.SignInContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class SigninModel implements SignInContract.Model {

    @Override
    public Observable<BaseHttpResponse<SigninBean>> signIn(String number, String password) {
        return RetrofitManager.getInstance().getRequestService().login(number,password);
    }
}
