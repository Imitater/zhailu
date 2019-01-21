package com.mouqu.zhailu.zhailu.modle.fragment;


import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.contract.fragment.CancelledOrderContract;
import com.mouqu.zhailu.zhailu.contract.fragment.WaitListContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class CancelledOrderModel implements CancelledOrderContract.Model {
    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(String user_id,String procress) {
        return RetrofitManager.getInstance().getRequestService().getProgressIndent(user_id,procress);
    }

    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page) {
        return RetrofitManager.getInstance().getRequestService().getIndentNext(user_id,progress,page);
    }
}
