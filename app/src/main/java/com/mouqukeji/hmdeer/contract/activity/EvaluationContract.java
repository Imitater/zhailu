package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EvaluationBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface EvaluationContract {
    interface View extends IBaseView {
        void evaluationOrder(EvaluationBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<EvaluationBean>> evaluationOrder(String user_id, String order_id, String score, String content);
    }

    abstract class Presenter extends BasePresenter<EvaluationContract.View, EvaluationContract.Model> {
        public abstract void evaluationOrder(String user_id, String order_id,  String score, String content, MultipleStatusView multipleStatusView);

    }
}
