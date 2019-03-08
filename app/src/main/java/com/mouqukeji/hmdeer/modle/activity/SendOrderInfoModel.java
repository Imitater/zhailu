package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
import com.mouqukeji.hmdeer.contract.activity.SendOrderInfoContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class SendOrderInfoModel implements SendOrderInfoContract.Model {


    @Override
    public Observable<BaseHttpResponse<HelpSendInfoBean>> getSendInfo(String task_id, String cate_id) {
        return RetrofitManager.getInstance().getRequestService().helpSendInfo(task_id, cate_id);
    }

    @Override
    public Observable<BaseHttpResponse<LocationDownBean>> locationDown(String user_id, String lat, String lng, String server_id) {
        return RetrofitManager.getInstance().getRequestService().locationDown(user_id, lat,lng,server_id);
    }

}
