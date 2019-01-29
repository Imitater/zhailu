package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.ChangePhoneBean;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.contract.activity.ChangePhoneContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

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
