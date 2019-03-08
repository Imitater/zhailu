package com.mouqukeji.hmdeer.modle.activity;

import android.content.Context;

import com.mouqukeji.hmdeer.bean.CheckVersionBean;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.contract.activity.MainContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class MainModel implements MainContract.Model {
    @Override
    public Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(Context context, String platform, String version_code) {
        return RetrofitManager.getInstance().getRequestService().checkVersion(platform, version_code);
    }
}
