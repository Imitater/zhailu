package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.AddAddressBean;
import com.mouqukeji.hmdeer.bean.ReChargeBean;
import com.mouqukeji.hmdeer.contract.activity.ReChargeContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class ReChargePresenter extends ReChargeContract.Presenter {
    @Override
    public void reCharge(String user_id, String price, String pay_fee, String pay_type, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.reCharge(user_id,price,pay_fee,pay_type), new RxObserverListener<ReChargeBean>(mView) {
            @Override
            public void onSuccess(ReChargeBean result) {

                mView.reCharge(result);
            }
        }));
    }
}
