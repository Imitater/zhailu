package com.mouqu.zhailu.zhailu.modle.activity;


import com.mouqu.zhailu.zhailu.bean.ChangePhoneBean;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.contract.activity.ChangePhoneContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class ChangePhoneModel implements ChangePhoneContract.Model {

    @Override
    public Observable<BaseHttpResponse<ChangePhoneBean>> changePhone(String id, String number, String code) {
        return RetrofitManager.getInstance().getRequestService().changeNumber(id,number,code);
    }

    @Override
    public Observable<BaseHttpResponse<CodeBean>> getCode(String number) {
        return RetrofitManager.getInstance().getRequestService().getCode(number);
    }
}
