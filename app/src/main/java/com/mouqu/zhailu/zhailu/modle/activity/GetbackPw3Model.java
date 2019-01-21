package com.mouqu.zhailu.zhailu.modle.activity;


import com.mouqu.zhailu.zhailu.bean.TokenBean;
import com.mouqu.zhailu.zhailu.bean.resetPasswordBean;
import com.mouqu.zhailu.zhailu.contract.activity.GetbackPw3Contract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw3Model implements GetbackPw3Contract.Model {

    @Override
    public Observable<BaseHttpResponse<resetPasswordBean>> resetPassword(String number, String code, String password) {
        return RetrofitManager.getInstance().getRequestService().resetPassword(number,code,password);

    }
}
