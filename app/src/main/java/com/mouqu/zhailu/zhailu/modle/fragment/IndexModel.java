package com.mouqu.zhailu.zhailu.modle.fragment;


import com.mouqu.zhailu.zhailu.bean.IndexBean;
import com.mouqu.zhailu.zhailu.contract.fragment.IndexContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;


public class IndexModel implements IndexContract.Model {

    @Override
    public Observable<BaseHttpResponse<IndexBean>> getIndex() {
        return RetrofitManager.getInstance().getRequestService().getIndex();
    }
}
