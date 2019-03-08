package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.DrawIntegralBean;
import com.mouqukeji.hmdeer.bean.IntegralPageBean;
import com.mouqukeji.hmdeer.contract.activity.IntegralMallContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;


public class IntegralMallPresenter extends IntegralMallContract.Presenter {

    @Override
    public void integralPage(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.integralPage(user_id), new RxObserverListener<IntegralPageBean>(mView) {
            @Override
            public void onSuccess(IntegralPageBean result) {
                mView.integralPage(result);
            }
        }));
    }

    @Override
    public void drawIntegral(String user_id, MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.drawIntegral(user_id), new RxObserverListener<DrawIntegralBean>(mView) {
            @Override
            public void onSuccess(DrawIntegralBean result) {
                mView.drawIntegral(result);
            }
        }));
    }
}

