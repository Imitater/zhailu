package com.mouqukeji.hmdeer.modle.fragment;


import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.contract.fragment.CompleteOrderContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class CompleteOrderModel implements CompleteOrderContract.Model {
    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(String user_id,String procress) {
        return RetrofitManager.getInstance().getRequestService().getProgressIndent(user_id,procress);
    }

    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page) {
        return RetrofitManager.getInstance().getRequestService().getIndentNext(user_id,progress,page);
    }
}
