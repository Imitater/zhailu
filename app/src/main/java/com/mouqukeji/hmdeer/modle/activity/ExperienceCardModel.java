package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.ConvertGoodsBean;
import com.mouqukeji.hmdeer.bean.DetailIntegralBean;
import com.mouqukeji.hmdeer.contract.activity.ExperienceCardContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class ExperienceCardModel implements ExperienceCardContract.Model {
    @Override
    public Observable<BaseHttpResponse<DetailIntegralBean>> detailIntegral(String good_id) {
        return RetrofitManager.getInstance().getRequestService().detailIntegral(good_id);
    }

    @Override
    public Observable<BaseHttpResponse<ConvertGoodsBean>> convertGoods(String good_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().convertGoods(good_id,user_id);
    }
}
