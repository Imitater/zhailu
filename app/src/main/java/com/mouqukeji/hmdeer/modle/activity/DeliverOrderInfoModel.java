package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.DeliverPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.contract.activity.DeliverOrderInfoContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class DeliverOrderInfoModel implements DeliverOrderInfoContract.Model {

    @Override
    public Observable<BaseHttpResponse<HelpDeliverInfoBean>> getDeliverInfo(String task_id, String cate_id) {
        return RetrofitManager.getInstance().getRequestService().helpDeliverInfo(task_id, cate_id);
    }
}
