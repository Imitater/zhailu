package com.mouqukeji.hmdeer.modle.activity;



import com.mouqukeji.hmdeer.bean.DrawIntegralBean;
import com.mouqukeji.hmdeer.bean.IntegralPageBean;
import com.mouqukeji.hmdeer.contract.activity.IntegralMallContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class IntegralMallModel implements IntegralMallContract.Model {


    @Override
    public Observable<BaseHttpResponse<IntegralPageBean>> integralPage(String user_id) {
        return RetrofitManager.getInstance().getRequestService().integralPage(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<DrawIntegralBean>> drawIntegral(String user_id) {
        return RetrofitManager.getInstance().getRequestService().drawIntegral(user_id);
    }
}
