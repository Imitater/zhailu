package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface HelpTakeContract {
    interface View extends IBaseView {
        void placeOrder(PlaceOrderBean bean);

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
        Observable<BaseHttpResponse<PlaceOrderBean>> placeOrder(String user_id, String cate_id,
                                                                String end_id, String[] pickup_code,
                                                                String express_point, String gtype_id,
                                                                String weight, String coupon,
                                                                String coupon_id, String task_price,
                                                                String pay_fee, String gender,
                                                                String delivery_time, String remarks);

        Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id);

        Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id);

        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id,String order_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free);
    }

    abstract class Presenter extends BasePresenter<HelpTakeContract.View, HelpTakeContract.Model> {
        public abstract void placeOrder(String user_id, String cate_id,
                                        String end_id, String[] pickup_code,
                                        String express_point, String gtype_id,
                                        String weight, String coupon,
                                        String coupon_id, String task_price,
                                        String pay_fee, String gender,
                                        String delivery_time, String remarks, MultipleStatusView multipleStatusView);

        public abstract void getItemsCategory(String city, String cate_id, String user_id, MultipleStatusView multipleStatusView);

        public abstract void getPreferentialList(String user_id, MultipleStatusView multipleStatusView);

        public abstract void payYueInfo(String user_id,String order_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYue(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);
    }
}
