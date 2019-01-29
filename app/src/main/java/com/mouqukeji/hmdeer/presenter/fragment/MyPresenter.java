package com.mouqukeji.hmdeer.presenter.fragment;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.contract.fragment.MyContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class MyPresenter extends MyContract.Presenter {

    @Override
    public void getUserImage(String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getUserImage(user_id), new RxObserverListener<UserImageBean>(mView) {
            @Override
            public void onSuccess(UserImageBean result) {

                mView.getUserImage(result);
            }
        }));
    }
}

