package com.mouqukeji.hmdeer.modle.fragment;


import com.mouqukeji.hmdeer.bean.IndexBean;
import com.mouqukeji.hmdeer.contract.fragment.IndexContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class IndexModel implements IndexContract.Model {

    @Override
    public Observable<BaseHttpResponse<IndexBean>> getIndex() {
        return RetrofitManager.getInstance().getRequestService().getIndex();
    }
}
