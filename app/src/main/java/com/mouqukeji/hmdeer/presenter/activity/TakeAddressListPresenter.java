package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.AddressListBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.contract.activity.TakeAddressListContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class TakeAddressListPresenter extends TakeAddressListContract.Presenter  {
    @Override
    public void getAddressList(String user_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getAddressList(user_id), new RxObserverListener<AddressListBean>(mView) {
            @Override
            public void onSuccess(AddressListBean result) {

                mView.getAddressList(result);
            }

        }));
    }

    @Override
    public void editAddress(String user_id, String id, String name, String telephone, String address, String detail, String is_default, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.editAddress(user_id,id,name,telephone,address,detail,is_default), new RxObserverListener<EditAddressBean>(mView) {
            @Override
            public void onSuccess(EditAddressBean result) {

                mView.editAddress(result);
            }
        }));
    }
}
