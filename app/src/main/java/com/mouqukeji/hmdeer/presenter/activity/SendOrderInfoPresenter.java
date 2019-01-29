package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.contract.activity.SendOrderInfoContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class SendOrderInfoPresenter extends SendOrderInfoContract.Presenter  {
    @Override
    public void getSendInfo(String task_id, String cate_id, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getSendInfo(task_id, cate_id), new RxObserverListener<HelpSendInfoBean>(mView) {
            @Override
            public void onSuccess(HelpSendInfoBean result) {

                mView.getSendInfo(result);
            }
        }));
    }
}
