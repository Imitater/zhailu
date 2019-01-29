package com.mouqukeji.hmdeer.modle.fragment;


import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.contract.fragment.AllOrderContract;
import com.mouqukeji.hmdeer.contract.fragment.RechangeListContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class RechangeListModel implements RechangeListContract.Model {

    @Override
    public Observable<BaseHttpResponse<RechangeListBean>> getRechangeList(String user_id, String page) {
        return RetrofitManager.getInstance().getRequestService().getRechangeList(user_id,page);
    }

    @Override
    public Observable<BaseHttpResponse<RechangeListBean>> getRechangeListNext(String user_id, String page) {
        return RetrofitManager.getInstance().getRequestService().getRechangeListNext(user_id,page);
    }
}
