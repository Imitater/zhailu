package com.mouqu.zhailu.zhailu.modle.activity;


import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.bean.RegisteredBean;
import com.mouqu.zhailu.zhailu.contract.activity.SignUpContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class SignUpModel implements SignUpContract.Model {

    @Override
    public Observable<BaseHttpResponse<CodeBean>> getCode(String number) {
        return RetrofitManager.getInstance().getRequestService().getCode(number);
    }

    @Override
    public Observable<BaseHttpResponse<RegisteredBean>> registered(String number, String code, String password) {
        return RetrofitManager.getInstance().getRequestService().registered(number,code,password);
    }
}
