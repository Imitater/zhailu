package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.HelpBuyInfoBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.BuyOrderInfoContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class BuyOrderInfoPresenter extends BuyOrderInfoContract.Presenter  {
    @Override
    public void payYueInfo(String user_id, String order_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payYueInfo(user_id, order_id), new RxObserverListener<PayYueBean>(mView) {
            @Override
            public void onSuccess(PayYueBean result) {
                mView.payYueInfo(result);
            }
        }));
    }

    @Override
    public void payWeiXing(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainWeixin(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<WeixingPayBean>(mView) {
            @Override
            public void onSuccess(WeixingPayBean result) {
                mView.payAgainWeixin(result);
            }
        }));
    }

    @Override
    public void payZhifubao(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainZhiFuBao(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<ZhiFuBoPayBean>(mView) {
            @Override
            public void onSuccess(ZhiFuBoPayBean result) {
                mView.payAgainZhiFuBao(result);
            }
        }));
    }

    @Override
    public void payYue(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainYue(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<YuEBean>(mView) {
            @Override
            public void onSuccess(YuEBean result) {
                mView.payAgainYue(result);
            }
        }));
    }

    @Override
    public void helpBuyInfo(String taskId, String cateId, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.helpBuyInfo(taskId, cateId), new RxObserverListener<HelpBuyInfoBean>(mView) {
            @Override
            public void onSuccess(HelpBuyInfoBean result) {
                mView.helpBuyInfo(result);
            }
        }));
    }

}
