package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.BuyPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyTagBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface HelpBuyContract {
    interface View extends IBaseView {
        void getItemsCategory(ItemsCategoryBean bean);

        void placeOrder(BuyPlaceOrderBean bean);

        void payYueInfo(PayYueBean bean);

        void getPreferentialList(PreferentialBean bean);

        void payWeiXing(WeixingPayBean bean);

        void payZhifubao(ZhiFuBoPayBean bean);

        void payYue(YuEBean bean);

        void helpBuyTag(HelpBuyTagBean bean);

        void isEmpty();
        void isPrefEmpty();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id);

        Observable<BaseHttpResponse<BuyPlaceOrderBean>> placeOrder(String userId, String cateId,
                                                                   String endId, String gtypeId,
                                                                   String weight, String coupon, String couponId,
                                                                   String taskPrice, String payFree, String gender,
                                                                   String deliveryTime, String remarks, String goods,
                                                                   String buy_address, String buy_lat, String buy_lng,String price);

        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id);

        Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id);

        Observable<BaseHttpResponse<HelpBuyTagBean>> helpBuyTag(String gtype_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free);
    }

    abstract class Presenter extends BasePresenter<HelpBuyContract.View, HelpBuyContract.Model> {
        public abstract void getItemsCategory(String city, String cate_id, String user_id, MultipleStatusView multipleStatusView);

        public abstract void placeOrder(String userId, String cateId,
                                        String endId, String gtypeId,
                                        String weight, String coupon, String couponId,
                                        String taskPrice, String payFree, String gender,
                                        String deliveryTime, String remarks, String goods,
                                        String buy_address, String buy_lat, String buy_lng,String price, MultipleStatusView multipleStatusView);

        public abstract void payYueInfo(String user_id, String order_id, MultipleStatusView multipleStatusView);

        public abstract void getPreferentialList(String user_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYue(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void helpBuyTag(String gtype_id, MultipleStatusView multipleStatusView);
    }
}
