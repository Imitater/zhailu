package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.DeleteOrderBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface AllOrderContract {
    interface View extends IBaseView {
        void getIndent(AllOrderBean bean);

        void getIndentNext(AllOrderBean bean);

        void getEmpty();

        void cancelOrder(CancelOrderBean bean);

        void payWeiXing(WeixingPayBean bean);

        void payZhifubao(ZhiFuBoPayBean bean);

        void payYue(YuEBean bean);

        void payYueInfo(PayYueBean bean);

        void deleteOrder(DeleteOrderBean bean);

        void finishOrder(FinishOrderBean bean);

        void payAgainWeixin(WeixingPayBean bean);

        void payAgainZhiFuBao(ZhiFuBoPayBean bean);

        void payAgainYue(YuEBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AllOrderBean>> getIndent(String user_id);

        Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page);

        Observable<BaseHttpResponse<CancelOrderBean>> cancelOrder(String task_id, String user_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id);

        Observable<BaseHttpResponse<DeleteOrderBean>> deleteOrder(String task_id);

        Observable<BaseHttpResponse<FinishOrderBean>> finishOrder(String task_id,String user_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<YuEBean>> payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee);
    }

    abstract class Presenter extends BasePresenter<AllOrderContract.View, AllOrderContract.Model> {
        public abstract void getIndent(String user_id, MultipleStatusView multipleStatusView);

        public abstract void getIndentNext(String user_id, String progress, String page, MultipleStatusView multipleStatusView);

        public abstract void cancelOrder(String task_id, String user_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYue(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYueInfo(String user_id, String order_id, MultipleStatusView multipleStatusView);

        public abstract void deleteOrder(String task_id, MultipleStatusView multipleStatusView);

        public abstract void finishOrder(String task_id,String user_id, MultipleStatusView multipleStatusView);

        public abstract void payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);
    }
}
