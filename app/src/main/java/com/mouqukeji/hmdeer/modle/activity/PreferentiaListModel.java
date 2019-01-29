package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.contract.activity.PreferentialListContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class PreferentiaListModel implements PreferentialListContract.Model {

    @Override
    public Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getPreferentialList(user_id);
    }
}
