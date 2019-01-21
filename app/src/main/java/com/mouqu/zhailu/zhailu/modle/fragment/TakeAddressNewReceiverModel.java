package com.mouqu.zhailu.zhailu.modle.fragment;


import com.mouqu.zhailu.zhailu.bean.AddAddressBean;
import com.mouqu.zhailu.zhailu.contract.fragment.TakeAddressNewReceiverContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;

import io.reactivex.Observable;

public class TakeAddressNewReceiverModel implements TakeAddressNewReceiverContract.Model {

    @Override
    public Observable<BaseHttpResponse<AddAddressBean>> addAddress(String user_id, String name, String telephone, String address, String detail, String is_default) {
        return RetrofitManager.getInstance().getRequestService().addAddress(user_id, name, telephone, address, detail, is_default);

    }
}
