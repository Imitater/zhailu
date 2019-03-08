package com.mouqukeji.hmdeer.presenter.activity;
import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.PayYueBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
import com.mouqukeji.hmdeer.bean.WeixingPayBean;
import com.mouqukeji.hmdeer.bean.YuEBean;
import com.mouqukeji.hmdeer.bean.ZhiFuBoPayBean;
import com.mouqukeji.hmdeer.contract.activity.DeliverIngOrderInfoContract;
import com.mouqukeji.hmdeer.contract.activity.DeliverOrderInfoContract;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public class DeliverOrderInfoPresenter extends DeliverOrderInfoContract.Presenter {
    @Override
    public void getDeliverInfo(String task_id, String cate_id, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getDeliverInfo(task_id, cate_id), new RxObserverListener<HelpDeliverInfoBean>(mView) {
            @Override
            public void onSuccess(HelpDeliverInfoBean result) {
                mView.getDeliverInfo(result);
            }
        }));
    }
    @Override
    public void locationDown(String user_id, String lat, String lng, String server_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.locationDown(user_id, lat, lng, server_id), new RxObserverListener<LocationDownBean>(mView) {
            @Override
            public void onSuccess(LocationDownBean result) {
                mView.locationDown(result);
            }
        }));
    }
}
