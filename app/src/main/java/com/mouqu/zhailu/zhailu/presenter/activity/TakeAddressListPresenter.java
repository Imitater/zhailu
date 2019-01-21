package com.mouqu.zhailu.zhailu.presenter.activity;


import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.AddressListBean;
import com.mouqu.zhailu.zhailu.bean.EditAddressBean;
import com.mouqu.zhailu.zhailu.bean.ErrorBean;
import com.mouqu.zhailu.zhailu.contract.activity.TakeAddressListContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class TakeAddressListPresenter extends TakeAddressListContract.Presenter  {
    @Override
    public void getAddressList(String user_id, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getAddressList(user_id), new RxObserverListener<AddressListBean>(mView) {
            @Override
            public void onSuccess(AddressListBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.getAddressList(result);
            }

        }));
    }

    @Override
    public void editAddress(String user_id, String id, String name, String telephone, String address, String detail, String is_default, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.editAddress(user_id,id,name,telephone,address,detail,is_default), new RxObserverListener<EditAddressBean>(mView) {
            @Override
            public void onSuccess(EditAddressBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.editAddress(result);
            }
        }));
    }
}
