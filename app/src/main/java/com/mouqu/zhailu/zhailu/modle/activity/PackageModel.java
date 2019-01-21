package com.mouqu.zhailu.zhailu.modle.activity;


import com.mouqu.zhailu.zhailu.bean.PackageBean;
import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.contract.activity.PackageContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class PackageModel implements PackageContract.Model {

    @Override
    public Observable<BaseHttpResponse<PackageBean>> getMoney(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getMoney(user_id);
    }
}
