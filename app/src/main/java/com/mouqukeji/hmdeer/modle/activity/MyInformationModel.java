package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.UserInfoBean;
import com.mouqukeji.hmdeer.bean.UserInfoUpBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.MyInformationContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class MyInformationModel implements MyInformationContract.Model {


    @Override
    public Observable<BaseHttpResponse<UserInfoBean>> getUserInfo(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getUserInfo(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<UserInfoUpBean>> putUserInfo(String user_id, String nickname, String avatar, String gender, String age, String school_name) {
        return RetrofitManager.getInstance().getRequestService().putUserInfo(user_id,nickname,avatar,gender,age,school_name);
    }
}
