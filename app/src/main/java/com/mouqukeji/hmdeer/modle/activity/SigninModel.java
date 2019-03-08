package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.PushMesgBean;
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.contract.activity.SignInContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class SigninModel implements SignInContract.Model {

    @Override
    public Observable<BaseHttpResponse<SigninBean>> signIn(String number,String password,String did,String platform
            , String app_version, String device_model,String os_version) {
        return RetrofitManager.getInstance().getRequestService().login(number,password,did,platform,app_version,device_model,os_version);
    }

    @Override
    public Observable<BaseHttpResponse<PushMesgBean>> pushMsg(String registration_id,String did,String uuid) {
        return RetrofitManager.getInstance().getRequestService().pushMesg(registration_id,did,uuid);
    }
}
