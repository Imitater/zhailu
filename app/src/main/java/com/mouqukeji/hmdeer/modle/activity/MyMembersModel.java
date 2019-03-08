package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.MyVipBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.MyMembersContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class MyMembersModel implements MyMembersContract.Model {
    @Override
    public Observable<BaseHttpResponse<MyVipBean>> myVip(String user_id) {
        return RetrofitManager.getInstance().getRequestService().myVip(user_id);
    }
}
