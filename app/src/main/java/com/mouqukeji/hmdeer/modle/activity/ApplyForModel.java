package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.AddPostBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.ApplyForContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class ApplyForModel implements ApplyForContract.Model {


    @Override
    public Observable<BaseHttpResponse<AddPostBean>> addPost(String name, String school, String grade, String mobile, String superiority) {
        return RetrofitManager.getInstance().getRequestService().addPost(name,school,grade,mobile,superiority);
    }
}
