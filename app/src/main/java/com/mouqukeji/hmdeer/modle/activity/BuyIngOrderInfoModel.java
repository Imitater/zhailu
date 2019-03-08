package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.BuyIngOrderInfoContract;
import com.mouqukeji.hmdeer.contract.activity.BuyOrderInfoContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class BuyIngOrderInfoModel implements BuyIngOrderInfoContract.Model {
    @Override
    public Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id) {
        return RetrofitManager.getInstance().getRequestService().payYueInfo(user_id, order_id);
    }

    @Override
    public Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String makeup_fee) {
        return RetrofitManager.getInstance().getRequestService().payWeiXingOrder(order_id, user_id, pay_type, makeup_fee);
    }

    @Override
    public Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String makeup_fee) {
        return RetrofitManager.getInstance().getRequestService().payZhiFuBaoOrder(order_id, user_id, pay_type, makeup_fee);
    }

    @Override
    public Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String makeup_fee) {
        return RetrofitManager.getInstance().getRequestService().payYuEOrder(order_id, user_id, pay_type, makeup_fee);
    }

    @Override
    public Observable<BaseHttpResponse<HelpBuyInfoBean>> helpBuyInfo(String taskId, String cateId) {
        return RetrofitManager.getInstance().getRequestService().helpBuyInfo(taskId, cateId);
    }

    @Override
    public Observable<BaseHttpResponse<CancelOrderBean>> cancelOrder(String task_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().cancelOrder(task_id, user_id);
    }
}
