package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.contract.activity.AddressEditContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class AddressEditPresenter extends  AddressEditContract.Presenter{

    @Override
    public void editAddress(String user_id, String id, String name, String telephone, String address, String detail, String is_defaul, String lat, String lng, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.editAddress(user_id,id,name,telephone,address,detail,is_defaul,lat,lng), new RxObserverListener<EditAddressBean>(mView) {
            @Override
            public void onSuccess(EditAddressBean result) {
                mView.editAddress(result);
            }
        }));
    }
}

