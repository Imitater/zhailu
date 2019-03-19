package com.mouqukeji.hmdeer.modle.fragment;


import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.DeleteOrderBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.fragment.AllOrderContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class AllOrderModel implements AllOrderContract.Model {
    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getIndent(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getIndent(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page) {
        return RetrofitManager.getInstance().getRequestService().getIndentNext(user_id,progress,page);
    }

    @Override
    public Observable<BaseHttpResponse<CancelOrderBean>> cancelOrder(String task_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().cancelOrder(task_id,user_id);
    }

    @Override
    public Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free) {
        return RetrofitManager.getInstance().getRequestService().payWeiXingOrder(order_id, user_id, pay_type, pay_free);
    }

    @Override
    public Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free) {
        return RetrofitManager.getInstance().getRequestService().payZhiFuBaoOrder(order_id, user_id, pay_type, pay_free);
    }

    @Override
    public Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free) {
        return RetrofitManager.getInstance().getRequestService().payYuEOrder(order_id, user_id, pay_type, pay_free);
    }

    @Override
    public Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id) {
        return RetrofitManager.getInstance().getRequestService().payYueInfo(user_id, order_id);
    }

    @Override
    public Observable<BaseHttpResponse<DeleteOrderBean>> deleteOrder(String task_id) {
        return RetrofitManager.getInstance().getRequestService().deleteOrder(task_id);
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
