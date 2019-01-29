package com.mouqukeji.hmdeer.modle.fragment;



import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.contract.fragment.MyContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class MyModel implements MyContract.Model {

    @Override
    public Observable<BaseHttpResponse<UserImageBean>> getUserImage(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getUserImage(user_id);
    }
}
