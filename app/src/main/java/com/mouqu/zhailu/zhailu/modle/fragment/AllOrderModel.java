package com.mouqu.zhailu.zhailu.modle.fragment;


import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.contract.fragment.AllOrderContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class AllOrderModel implements AllOrderContract.Model {
    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getIndent(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getIndent(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page) {
        return RetrofitManager.getInstance().getRequestService().getIndentNext(user_id,progress,page);
    }
}
