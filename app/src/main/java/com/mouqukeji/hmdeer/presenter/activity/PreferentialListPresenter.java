package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.contract.activity.PreferentialListContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class PreferentialListPresenter extends  PreferentialListContract.Presenter{

    @Override
    public void getPreferentialList(String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getPreferentialList(user_id), new RxObserverListener<PreferentialBean>(mView) {
            @Override
            public void onSuccess(PreferentialBean result) {

                mView.getPreferentialList(result);
            }
        }));
    }
}

