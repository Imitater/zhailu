package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.SendPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface HelpSendContract {
    interface View extends IBaseView {
        void sendPlaceOrder(SendPlaceOrderBean bean);

        void getItemsCategory(ItemsCategoryBean bean);

        void getPreferentialList(PreferentialBean bean);

        void isEmpty();

        void payYueInfo(PayYueBean bean);

        void payWeiXing(WeixingPayBean bean);

        void payZhifubao(ZhiFuBoPayBean bean);

        void payYue(YuEBean bean);
        void isPreEmpty();
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<SendPlaceOrderBean>> sendPlaceOrder(String userId, String cateId,
                                                                String startId, String expressPoint, String gtypeId,
                                                                String weight, String coupon, String couponId,
                                                                String taskPrice, String payFree,
                                                                String deliveryTime, String remarks, String expressPayType,
                                                                String name, String telephone, String address, String detail);

        Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id);

        Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id);

        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free);
    }

    abstract class Presenter extends BasePresenter<HelpSendContract.View, HelpSendContract.Model> {
        public abstract void sendPlaceOrder(String userId, String cateId,
                                        String startId, String expressPoint, String gtypeId,
                                        String weight, String coupon, String couponId,
                                        String taskPrice, String payFree,
                                        String deliveryTime, String remarks, String expressPayType,
                                        String name, String telephone, String address, String detail, MultipleStatusView multipleStatusView);

        public abstract void getItemsCategory(String city, String cate_id, String user_id, MultipleStatusView multipleStatusView);

        public abstract void getPreferentialList(String user_id, MultipleStatusView multipleStatusView);

        public abstract void payYueInfo(String user_id, String order_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYue(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);
    }
}
