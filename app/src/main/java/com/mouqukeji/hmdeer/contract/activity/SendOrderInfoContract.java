package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SendOrderInfoContract {
    interface View extends IBaseView {
        void getSendInfo(HelpSendInfoBean bean);
        void locationDown(LocationDownBean bean);
        void payYueInfo(PayYueBean bean);

        void payAgainWeixin(WeixingPayBean bean);

        void payAgainZhiFuBao(ZhiFuBoPayBean bean);

        void payAgainYue(YuEBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<HelpSendInfoBean>> getSendInfo(String task_id, String cate_id);
        Observable<BaseHttpResponse<LocationDownBean>> locationDown(String user_id, String lat,String lng,String server_id);
        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<YuEBean>> payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee);
    }

    abstract class Presenter extends BasePresenter<SendOrderInfoContract.View, SendOrderInfoContract.Model> {
        public abstract void getSendInfo(String task_id, String cate_id, MultipleStatusView multipleStatusView);
        public abstract void locationDown(String user_id, String lat,String lng,String server_id, MultipleStatusView multipleStatusView);
        public abstract void payYueInfo(String user_id, String order_id, MultipleStatusView multipleStatusView);

        public abstract void payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);
    }
}
