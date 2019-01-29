package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.EvaluationBean;
import com.mouqukeji.hmdeer.contract.activity.EvaluationContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class EvaluationModel implements EvaluationContract.Model {

    @Override
    public Observable<BaseHttpResponse<EvaluationBean>> evaluationOrder(String user_id, String order_id, String score, String content) {
        return RetrofitManager.getInstance().getRequestService().evaluationOrder(user_id, order_id, score,content);
    }
}
