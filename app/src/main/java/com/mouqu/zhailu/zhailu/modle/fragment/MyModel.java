package com.mouqu.zhailu.zhailu.modle.fragment;



import com.mouqu.zhailu.zhailu.bean.UserImageBean;
import com.mouqu.zhailu.zhailu.contract.fragment.MyContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class MyModel implements MyContract.Model {

    @Override
    public Observable<BaseHttpResponse<UserImageBean>> getUserImage(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getUserImage(user_id);
    }
}
