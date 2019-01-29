package com.mouqukeji.hmdeer.modle.activity;


import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.UnviersalPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpUniversalContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;

public class HelpUniversalModel implements HelpUniversalContract.Model {
    @Override
    public Observable<BaseHttpResponse<UnviersalPlaceOrderBean>> universalPlaceOrder(String user_id, String cate_id, String end_id,
                                                                                     String demand, String task_price, String pay_fee,
                                                                                     String gender, String delivery_time) {
        return RetrofitManager.getInstance().getRequestService().universalPlaceOrder(user_id, cate_id, end_id, demand,task_price,pay_fee,gender,delivery_time);
    }

    @Override
    public Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().getItemsCategory(city, cate_id, user_id);
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

}
