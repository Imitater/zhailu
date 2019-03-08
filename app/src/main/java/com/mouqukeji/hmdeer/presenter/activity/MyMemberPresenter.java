package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.IndexBean;
import com.mouqukeji.hmdeer.bean.MyVipBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.contract.activity.MyMembersContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class MyMemberPresenter extends  MyMembersContract.Presenter{


    @Override
    public void myVip(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.myVip(user_id), new RxObserverListener<MyVipBean>(mView) {
            @Override
            public void onSuccess(MyVipBean result) {

                mView.myVip(result);
            }

        }));
    }
}

