package com.mouqukeji.hmdeer.modle.fragment;


import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.fragment.OngingOrderContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class OngoingOrderModel implements OngingOrderContract.Model {
    @Override
    public Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id) {
        return RetrofitManager.getInstance().getRequestService().payYueInfo(user_id, order_id);
    }

    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(String user_id, String procress) {
        return RetrofitManager.getInstance().getRequestService().getProgressIndent(user_id,procress);
    }

    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page) {
        return RetrofitManager.getInstance().getRequestService().getIndentNext(user_id,progress,page);
    }

    @Override
    public Observable<BaseHttpResponse<FinishOrderBean>> finishOrder(String task_id,String user_id) {
        return RetrofitManager.getInstance().getRequestService().finishOrder(task_id,user_id);
    }
    @Override
    public Observable<BaseHttpResponse<WeixingPayBean>> payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee) {
        return RetrofitManager.getInstance().getRequestService().payAgainWeixin(makeup_id, user_id, pay_type, makeup_fee);
    }

    @Override
    public Observable<BaseHttpResponse<ZhiFuBoPayBean>> payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee) {
        return RetrofitManager.getInstance().getRequestService().payAgainZhiFuBao(makeup_id, user_id, pay_type, makeup_fee);
    }

    @Override
    public Observable<BaseHttpResponse<YuEBean>> payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee) {
        return RetrofitManager.getInstance().getRequestService().payAgainYue(makeup_id, user_id, pay_type, makeup_fee);
    }
}
