package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.resetPasswordBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw3Contract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw3Model implements GetbackPw3Contract.Model {

    @Override
    public Observable<BaseHttpResponse<resetPasswordBean>> resetPassword(String number, String code, String password) {
        return RetrofitManager.getInstance().getRequestService().resetPassword(number,code,password);

    }
}
