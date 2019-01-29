package com.mouqukeji.hmdeer.modle.activity;

import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.HelpTakeContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;

import io.reactivex.Observable;


public class HelpTakeModel implements HelpTakeContract.Model {


    @Override
    public Observable<BaseHttpResponse<PlaceOrderBean>> placeOrder(String user_id, String cate_id, String end_id, String[] pickup_code, String express_point, String gtype_id, String weight, String coupon, String coupon_id, String task_price, String pay_fee, String gender, String delivery_time, String remarks) {
        return RetrofitManager.getInstance().getRequestService().placeOrder(user_id, cate_id, end_id, pickup_code, express_point, gtype_id, weight, coupon, coupon_id, task_price, pay_fee, gender, delivery_time, remarks);
    }

    @Override
    public Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id) {
        return RetrofitManager.getInstance().getRequestService().getItemsCategory(city, cate_id, user_id);
    }

    @Override
    public Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id) {
        return RetrofitManager.getInstance().getRequestService().getPreferentialList(user_id);
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
