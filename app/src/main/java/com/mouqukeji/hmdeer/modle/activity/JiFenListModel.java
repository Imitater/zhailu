package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.IntegralListBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.JiFenListContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class JiFenListModel implements JiFenListContract.Model {


    @Override
    public Observable<BaseHttpResponse<IntegralListBean>> jiFenList(String user_id) {
        return RetrofitManager.getInstance().getRequestService().integralList(user_id);
    }
}
