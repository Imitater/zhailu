package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.bean.UserInfoBean;
import com.mouqukeji.hmdeer.bean.UserInfoUpBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.MyInformationContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class MyInformationPresenter extends MyInformationContract.Presenter {

    @Override
    public void getUserInfo(String user_id, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getUserInfo(user_id), new RxObserverListener<UserInfoBean>(mView) {
            @Override
            public void onSuccess(UserInfoBean result) {
                mView.getUserInfo(result);
            }
        }));
    }

    @Override
    public void putUserInfo(String user_id, String nickname, String avatar, String gender, String age, String school_name, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.putUserInfo(user_id, nickname, avatar, gender, age, school_name), new RxObserverListener<UserInfoUpBean>(mView) {
            @Override
            public void onSuccess(UserInfoUpBean result) {
                mView.putUserInfo(result);
            }
        }));
    }
}

