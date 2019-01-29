package com.mouqukeji.hmdeer.modle.fragment;


import com.mouqukeji.hmdeer.bean.ConsumptionListBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.contract.fragment.ConsumptionListContract;
import com.mouqukeji.hmdeer.contract.fragment.RechangeListContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class ConsumptionListModel implements ConsumptionListContract.Model {

    @Override
    public Observable<BaseHttpResponse<ConsumptionListBean>> getConsumptionList(String user_id, String page) {
        return RetrofitManager.getInstance().getRequestService().getConsumptionList(user_id,page);
    }

    @Override
    public Observable<BaseHttpResponse<ConsumptionListBean>> getConsumptionListNext(String user_id, String page) {
        return RetrofitManager.getInstance().getRequestService().getConsumptionList(user_id,page);
    }
}
