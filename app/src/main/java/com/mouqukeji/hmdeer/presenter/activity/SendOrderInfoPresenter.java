package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.SendOrderInfoContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class SendOrderInfoPresenter extends SendOrderInfoContract.Presenter  {
    @Override
    public void getSendInfo(String task_id, String cate_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getSendInfo(task_id, cate_id), new RxObserverListener<HelpSendInfoBean>(mView) {
            @Override
            public void onSuccess(HelpSendInfoBean result) {

                mView.getSendInfo(result);
            }
        }));
    }

    @Override
    public void locationDown(String user_id, String lat, String lng, String server_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.locationDown(user_id, lat,lng,server_id), new RxObserverListener<LocationDownBean>(mView) {
            @Override
            public void onSuccess(LocationDownBean result) {

                mView.locationDown(result);
            }
        }));
    }
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
    public void payAgainWeixin(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainWeixin(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<WeixingPayBean>(mView) {
            @Override
            public void onSuccess(WeixingPayBean result) {
                mView.payAgainWeixin(result);
            }
        }));
    }

    @Override
    public void payAgainZhiFuBao(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainZhiFuBao(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<ZhiFuBoPayBean>(mView) {
            @Override
            public void onSuccess(ZhiFuBoPayBean result) {
                mView.payAgainZhiFuBao(result);
            }
        }));
    }

    @Override
    public void payAgainYue(String makeup_id, String user_id, String pay_type, String makeup_fee, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.payAgainYue(makeup_id, user_id, pay_type, makeup_fee), new RxObserverListener<YuEBean>(mView) {
            @Override
            public void onSuccess(YuEBean result) {
                mView.payAgainYue(result);
            }
        }));
    }

}
