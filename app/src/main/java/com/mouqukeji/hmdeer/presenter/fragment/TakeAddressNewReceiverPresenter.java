package com.mouqukeji.hmdeer.presenter.fragment;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.AddAddressBean;
import com.mouqukeji.hmdeer.contract.fragment.TakeAddressNewReceiverContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class TakeAddressNewReceiverPresenter extends TakeAddressNewReceiverContract.Presenter {

    @Override
    public void addAddress(String user_id, String name, String telephone, String address, String detail, String is_default,String lat,String lng, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.addAddress(user_id,name,telephone,address,detail,is_default,lat,lng), new RxObserverListener<AddAddressBean>(mView) {
            @Override
            public void onSuccess(AddAddressBean result) {

                mView.addAddress(result);
            }
        }));
    }
}

