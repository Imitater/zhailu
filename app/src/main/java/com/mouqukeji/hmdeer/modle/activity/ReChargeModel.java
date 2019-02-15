package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.ReChargeBean;
import com.mouqukeji.hmdeer.bean.RechargePageBean;
import com.mouqukeji.hmdeer.contract.activity.ReChargeContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class ReChargeModel implements ReChargeContract.Model {

    @Override
    public Observable<BaseHttpResponse<ReChargeBean>> reCharge(String user_id, String price, String pay_fee, String pay_type) {
        return RetrofitManager.getInstance().getRequestService().reCharge(user_id,price,pay_fee,pay_type);

    }

    @Override
    public Observable<BaseHttpResponse<RechargePageBean>> reChargePage() {
        return RetrofitManager.getInstance().getRequestService().rechangePage();
    }
}
