package com.mouqu.zhailu.zhailu.modle.activity;


import com.mouqu.zhailu.zhailu.bean.CodeCheckBean;
import com.mouqu.zhailu.zhailu.contract.activity.GetbackPw2Contract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class GetbackPw2Model implements GetbackPw2Contract.Model {

    @Override
    public Observable<BaseHttpResponse<CodeCheckBean>> checkCode(String number, String code) {
        return RetrofitManager.getInstance().getRequestService().checkCode(number,code);
    }
}
