package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
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
}
