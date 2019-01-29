package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.contract.activity.PackageContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class PackageModel implements PackageContract.Model {

    @Override
    public Observable<BaseHttpResponse<PackageBean>> getMoney(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getMoney(user_id);
    }
}
