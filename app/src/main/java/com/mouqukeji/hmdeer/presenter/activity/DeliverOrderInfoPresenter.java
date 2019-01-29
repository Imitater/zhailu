package com.mouqukeji.hmdeer.presenter.activity;
import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.contract.activity.DeliverOrderInfoContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class DeliverOrderInfoPresenter extends DeliverOrderInfoContract.Presenter {
    @Override
    public void getDeliverInfo(String task_id, String cate_id, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getDeliverInfo(task_id, cate_id), new RxObserverListener<HelpDeliverInfoBean>(mView) {
            @Override
            public void onSuccess(HelpDeliverInfoBean result) {
                mView.getDeliverInfo(result);
            }
        }));
    }
}
