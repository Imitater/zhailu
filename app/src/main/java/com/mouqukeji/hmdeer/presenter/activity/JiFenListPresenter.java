package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.IntegralListBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.JiFenListContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class JiFenListPresenter extends  JiFenListContract.Presenter{

    @Override
    public void jiFenList(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.jiFenList(user_id), new RxObserverListener<IntegralListBean>(mView) {
            @Override
            public void onSuccess(IntegralListBean result) {
                mView.jiFenList(result);
            }
        }));
    }
}

