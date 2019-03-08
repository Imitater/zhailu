package com.mouqukeji.hmdeer.contract.activity;
import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface TakeIngOrderInfoContract {
    interface View extends IBaseView {
        void getTakeInfo(HelpTakeInfoBean bean);
        void cancelOrder(CancelOrderBean bean);

        void payWeiXing(WeixingPayBean bean);

        void payZhifubao(ZhiFuBoPayBean bean);

        void payYue(YuEBean bean);

        void payYueInfo(PayYueBean bean);
      }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<HelpTakeInfoBean>> getTakeInfo(String task_id, String cate_id);
        Observable<BaseHttpResponse<CancelOrderBean>> cancelOrder(String task_id, String user_id);
        Observable<BaseHttpResponse<WeixingPayBean>> payWeiXing(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payZhifubao(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<YuEBean>> payYue(String order_id, String user_id, String pay_type, String pay_free);

        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id,String order_id);
      }

    abstract class Presenter extends BasePresenter<TakeIngOrderInfoContract.View, TakeIngOrderInfoContract.Model> {
        public abstract void getTakeInfo(String task_id, String cate_id, MultipleStatusView multipleStatusView);
        public abstract void cancelOrder(String task_id, String user_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYue(String order_id, String user_id, String pay_type, String pay_free, MultipleStatusView multipleStatusView);

        public abstract void payYueInfo(String user_id,String order_id, MultipleStatusView multipleStatusView);
      }
}
