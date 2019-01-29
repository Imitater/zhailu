package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.RegisteredBean;
import com.mouqukeji.hmdeer.contract.activity.SignUpContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

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
