package com.mouqukeji.hmdeer.presenter.activity;


import com.mouqukeji.hmdeer.base.RxObserverListener;
import com.mouqukeji.hmdeer.bean.EvaluationBean;
import com.mouqukeji.hmdeer.contract.activity.EvaluationContract;
import com.mouqukeji.hmdeer.net.RetrofitManager;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

public class EvaluationPresenter extends   EvaluationContract.Presenter  {
    @Override
    public void evaluationOrder(String user_id, String order_id, String score, String content, final MultipleStatusView multipleStatusView) {
        rxManager.addObserver(RetrofitManager.getInstance().doRequest(mModel.evaluationOrder(user_id,order_id,score,content), new RxObserverListener<EvaluationBean>(mView) {
            @Override
            public void onSuccess(EvaluationBean result) {
                mView.evaluationOrder(result);
            }
        }));
    }
}
