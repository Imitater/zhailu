package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.FeedBackBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.AdviceContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class AdviceModel implements AdviceContract.Model {


    @Override
    public Observable<BaseHttpResponse<FeedBackBean>> feedBack(String picture, String suggestion, String question, String userid) {
        return RetrofitManager.getInstance().getRequestService().feedBack(picture,suggestion,question,userid);
    }
}
