package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface BuyIngOrderInfoContract {
    interface View extends IBaseView {
        void payYueInfo(PayYueBean bean);

        void payWeiXing(WeixingPayBean bean);

        void payZhifubao(ZhiFuBoPayBean bean);

        void payYue(YuEBean bean);

        void helpBuyInfo(HelpBuyInfoBean buyInfoBean);

        void cancelOrder(CancelOrderBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<HelpBuyInfoBean>> helpBuyInfo(String taskId, String cateId);
        Observable<BaseHttpResponse<CancelOrderBean>> cancelOrder(String task_id, String user_id);
    }

    abstract class Presenter extends BasePresenter<BuyIngOrderInfoContract.View, BuyIngOrderInfoContract.Model> {
        public abstract void payYueInfo(String user_id, String order_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String order_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String order_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payYue(String order_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void helpBuyInfo(String taskId, String cateId, MultipleStatusView multipleStatusView);
        public abstract void cancelOrder(String task_id, String user_id, MultipleStatusView multipleStatusView);
    }
}
