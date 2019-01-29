package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.PackageBean;
import com.mouqukeji.hmdeer.contract.activity.PackageContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class PackagePresenter extends  PackageContract.Presenter{

    @Override
    public void getMoney(String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getMoney(user_id), new RxObserverListener<PackageBean>(mView) {
            @Override
            public void onSuccess(PackageBean result) {

                mView.getMoney(result);
            }
        }));
    }
}

