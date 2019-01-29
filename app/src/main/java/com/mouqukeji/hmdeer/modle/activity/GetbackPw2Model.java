package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.CodeCheckBean;
import com.mouqukeji.hmdeer.contract.activity.GetbackPw2Contract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw2Model implements GetbackPw2Contract.Model {

    @Override
    public Observable<BaseHttpResponse<CodeCheckBean>> checkCode(String number, String code) {
        return RetrofitManager.getInstance().getRequestService().checkCode(number,code);
    }
}
