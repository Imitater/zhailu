package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.BuyPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyTagBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpBuyContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class HelpBuyModel implements HelpBuyContract.Model {

    @Override
    public Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().getItemsCategory(city, cate_id, user_id);
    }

    @Override
    public Observable<BaseHttpResponse<BuyPlaceOrderBean>> placeOrder(String userId, String cateId, String endId, String gtypeId, String weight, String coupon, String couponId, String taskPrice, String payFree, String gender, String deliveryTime, String remarks, String goods, String buy_address, String buy_lat, String buy_lng,String price) {
        return RetrofitManager.getInstance().getRequestService().buyPlaceOrder(userId, cateId, endId, gtypeId, weight, coupon,couponId, taskPrice, payFree, gender, deliveryTime, remarks, goods,buy_address,buy_lat,buy_lng,price);

    }

    @Override
    public Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getPreferentialList(user_id);
    }

    @Override
    public Observable<BaseHttpResponse<HelpBuyTagBean>> helpBuyTag(String gtype_id) {
        return RetrofitManager.getInstance().getRequestService().helpBuyTag(gtype_id);
    }

    @Override
    public Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id) {
        return RetrofitManager.getInstance().getRequestService().payYueInfo(user_id, order_id);
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
