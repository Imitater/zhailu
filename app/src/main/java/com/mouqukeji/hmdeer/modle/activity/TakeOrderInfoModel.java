package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.contract.activity.TakeOrderInfoContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class TakeOrderInfoModel implements TakeOrderInfoContract.Model {

    @Override
    public Observable<BaseHttpResponse<HelpTakeInfoBean>> getTakeInfo(String task_id, String cate_id) {
        return RetrofitManager.getInstance().getRequestService().helpTakeInfo(task_id, cate_id);
    }
}
