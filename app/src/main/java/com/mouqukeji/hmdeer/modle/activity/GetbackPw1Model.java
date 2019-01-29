package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw1Contract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw1Model implements GetbackPw1Contract.Model {

    @Override
    public Observable<BaseHttpResponse<CodeBean>> getCode(String number) {
        return RetrofitManager.getInstance().getRequestService().getCode(number);
    }
}
