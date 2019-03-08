package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.HelpUniversalInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.UniversalOrderInfoContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class UniversalOrderPresenter extends  UniversalOrderInfoContract.Presenter{

    @Override
    public void getUniversalInfo(String task_id, String cate_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getUniversalInfo(task_id, cate_id), new RxObserverListener<HelpUniversalInfoBean>(mView) {
            @Override
            public void onSuccess(HelpUniversalInfoBean result) {

                mView.getUniversalInfo(result);
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

