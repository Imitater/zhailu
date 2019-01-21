package com.mouqu.zhailu.zhailu.presenter.fragment;


import com.mouqu.zhailu.zhailu.base.RxObserverListener;
import com.mouqu.zhailu.zhailu.bean.AddAddressBean;
import com.mouqu.zhailu.zhailu.bean.PackageBean;
import com.mouqu.zhailu.zhailu.contract.fragment.ReceiverContract;
import com.mouqu.zhailu.zhailu.net.RetrofitManager;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

public class ReceiverPresenter extends ReceiverContract.Presenter {

    @Override
    public void addAddress(String user_id, String name, String telephone, String address, String detail, String is_default, final MultipleStatusView multipleStatusView) {
        if (multipleStatusView != null) {
            multipleStatusView.showLoading();
        }
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.addAddress(user_id,name,telephone,address,detail,is_default), new RxObserverListener<AddAddressBean>(mView) {
            @Override
            public void onSuccess(AddAddressBean result) {
                if (multipleStatusView != null) {
                    new android.os.Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            multipleStatusView.showContent();
                        }
                    }, 2000);
                }
                mView.addAddress(result);
            }
        }));
    }
}

