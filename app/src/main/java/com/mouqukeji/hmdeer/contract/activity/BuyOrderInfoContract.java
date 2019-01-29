package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.HelpBuyTagBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface BuyOrderInfoContract {
    interface View extends IBaseView {
        void payYueInfo(PayYueBean bean);

        void payAgainWeixin(WeixingPayBean bean);

        void payAgainZhiFuBao(ZhiFuBoPayBean bean);

        void payAgainYue(YuEBean bean);

        void helpBuyInfo(HelpBuyInfoBean buyInfoBean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PayYueBean>> payYueInfo(String user_id, String order_id);

        Observable<BaseHttpResponse<WeixingPayBean>> payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<ZhiFuBoPayBean>> payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<YuEBean>> payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee);

        Observable<BaseHttpResponse<HelpBuyInfoBean>> helpBuyInfo(String taskId, String cateId);
    }

    abstract class Presenter extends BasePresenter<BuyOrderInfoContract.View, BuyOrderInfoContract.Model> {
        public abstract void payYueInfo(String user_id, String order_id, MultipleStatusView multipleStatusView);

        public abstract void payWeiXing(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payZhifubao(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void payYue(String makeup_id, String user_id, String pay_type, String makeup_fee, MultipleStatusView multipleStatusView);

        public abstract void helpBuyInfo(String taskId, String cateId, MultipleStatusView multipleStatusView);
    }
}
