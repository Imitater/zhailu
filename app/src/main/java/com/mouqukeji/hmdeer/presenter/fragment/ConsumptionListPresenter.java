package com.mouqukeji.hmdeer.presenter.fragment;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.ConsumptionListBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.contract.fragment.ConsumptionListContract;
import com.mouqukeji.hmdeer.contract.fragment.RechangeListContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class ConsumptionListPresenter extends ConsumptionListContract.Presenter {


    @Override
    public void getConsumptionList(String user_id, String page, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getConsumptionList(user_id,page), new RxObserverListener<ConsumptionListBean>(mView) {
            @Override
            public void onSuccess(ConsumptionListBean result) {

                if (result.getBuy().getConsume().size() != 0) {
                    mView.getConsumptionList(result);
                } else {
                    mView.isEmpty();
                }
            }
        }));
    }

    @Override
    public void getConsumptionListNext(String user_id, String page, final MultipleStatusView multipleStatusView) {

        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.getConsumptionListNext(user_id,page), new RxObserverListener<ConsumptionListBean>(mView) {
            @Override
            public void onSuccess(ConsumptionListBean result) {

                if (result.getBuy().getConsume().size() != 0) {
                    mView.getConsumptionListNext(result);
                } else {
                    mView.isEmpty();
                }
            }
        }));
    }
}

