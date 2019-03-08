package com.mouqukeji.hmdeer.modle.activity;


import android.content.Context;

import com.mouqukeji.hmdeer.bean.CheckVersionBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.contract.activity.AboutContract;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class AboutModel implements AboutContract.Model {
    @Override
    public Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(Context context, String platform, String version_code) {
        return RetrofitManager.getInstance().getRequestService().checkVersion(platform, version_code);
    }
}
