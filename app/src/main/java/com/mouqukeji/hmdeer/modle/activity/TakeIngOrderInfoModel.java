package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.TakeIngOrderInfoContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class TakeIngOrderInfoModel implements TakeIngOrderInfoContract.Model {

    @Override
    public Observable<BaseHttpResponse<HelpTakeInfoBean>> getTakeInfo(String task_id, String cate_id) {
        return RetrofitManager.getInstance().getRequestService().helpTakeInfo(task_id, cate_id);
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
}
