package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.PlaceOrderBean;
import com.mouqukeji.hmdeer.contract.activity.TakeOrderInfoContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class TakeOrderInfoPresenter extends TakeOrderInfoContract.Presenter  {

    @Override
    public void getTakeInfo(String task_id, String cate_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getTakeInfo(task_id, cate_id), new RxObserverListener<HelpTakeInfoBean>(mView) {
            @Override
            public void onSuccess(HelpTakeInfoBean result) {

                mView.getTakeInfo(result);
            }
        }));
    }
}
