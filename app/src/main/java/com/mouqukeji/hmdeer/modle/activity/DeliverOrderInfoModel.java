package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.DeliverPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
import com.mouqukeji.hmdeer.contract.activity.DeliverOrderInfoContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class DeliverOrderInfoModel implements DeliverOrderInfoContract.Model {

    @Override
    public Observable<BaseHttpResponse<HelpDeliverInfoBean>> getDeliverInfo(String task_id, String cate_id) {
        return RetrofitManager.getInstance().getRequestService().helpDeliverInfo(task_id, cate_id);
    }
    @Override
    public Observable<BaseHttpResponse<LocationDownBean>> locationDown(String user_id, String lat, String lng, String server_id) {
        return RetrofitManager.getInstance().getRequestService().locationDown(user_id, lat,lng,server_id);
    }


}
