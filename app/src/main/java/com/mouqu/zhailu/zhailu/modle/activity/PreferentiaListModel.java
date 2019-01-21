package com.mouqu.zhailu.zhailu.modle.activity;


import com.mouqu.zhailu.zhailu.bean.PreferentialBean;
import com.mouqu.zhailu.zhailu.contract.activity.PreferentialListContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class PreferentiaListModel implements PreferentialListContract.Model {

    @Override
    public Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getPreferentialList(user_id);
    }
}
