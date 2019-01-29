package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class AddressEditModel implements AddressEditContract.Model {

    @Override
    public Observable<BaseHttpResponse<EditAddressBean>> editAddress(String user_id, String id, String name, String telephone, String address, String detail, String is_defaul, String lat, String lng) {
        return RetrofitManager.getInstance().getRequestService().editAddress(user_id, id,  name,  telephone,  address,  detail,  is_defaul,  lat,  lng);
    }
}
