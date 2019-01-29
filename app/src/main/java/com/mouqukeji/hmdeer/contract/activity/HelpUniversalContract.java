package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.bean.UnviersalPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface HelpUniversalContract {
    interface View extends IBaseView {
        void universalPlaceOrder(UnviersalPlaceOrderBean bean);

        void isEmpty();

        void getItemsCategory(ItemsCategoryBean bean);

        void payWeiXing(WeixingPayBean bean);

        void payZhifubao(ZhiFuBoPayBean bean);

        void payYue(YuEBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<UnviersalPlaceOrderBean>> universalPlaceOrder(String user_id, String cate_id, String end_id,
                                                                                  String demand, String task_price, String pay_fee,
                                                                                  String gender, String delivery_time);

        Observable<BaseHttpResponse<ItemsCategoryBean>> getItemsCategory(String city, String cate_id, String user_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free);

    }

    abstract class Presenter extends BasePresenter<HelpUniversalContract.View, HelpUniversalContract.Model> {
        public abstract void universalPlaceOrder(String user_id, String cate_id, String end_id,
                                                 String demand, String task_price, String pay_fee,
                                                 String gender, String delivery_time, MultipleStatusView multipleStatusView);

        public abstract void getItemsCategory(String city, String cate_id, String user_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYue(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);
    }
}
