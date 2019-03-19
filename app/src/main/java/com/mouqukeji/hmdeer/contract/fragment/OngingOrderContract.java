package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.FinishOrderBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface OngingOrderContract {
    interface View extends IBaseView {
        void getProgressIndent(AllOrderBean bean);
        void getIndentNext(AllOrderBean bean);
        void getEmpty();
        void finishOrder(FinishOrderBean bean);

        void payAgainWeixin(WeixingPayBean bean);

        void payAgainZhiFuBao(ZhiFuBoPayBean bean);

        void payAgainYue(YuEBean bean);
        void payYueInfo(PayYueBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(String user_id, String procress);
        Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page);
        Observable<BaseHttpResponse<FinishOrderBean>> finishOrder(String task_id,String user_id);
        Observable<BaseHttpResponse<WeixingPayBean>> payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<YuEBean>> payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee);
        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id);
    }

    abstract class Presenter extends BasePresenter<OngingOrderContract.View, OngingOrderContract.Model> {
        public abstract void getProgressIndent(String user_id, String procress,MultipleStatusView multipleStatusView);
        public abstract void getIndentNext(String user_id,String progress,String page,MultipleStatusView multipleStatusView);
        public abstract void finishOrder(String task_id,String user_id, MultipleStatusView multipleStatusView);
        public abstract void payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);
        public abstract void payYueInfo(String user_id, String order_id, MultipleStatusView multipleStatusView);
    }
}
