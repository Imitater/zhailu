package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ConsumptionListBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface ConsumptionListContract {
    interface View extends IBaseView {
        void getConsumptionList(ConsumptionListBean bean);
        void getConsumptionListNext(ConsumptionListBean bean);
        void isEmpty();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<ConsumptionListBean>> getConsumptionList(String user_id, String page);
        Observable<BaseHttpResponse<ConsumptionListBean>> getConsumptionListNext(String user_id, String page);
    }

    abstract class Presenter extends BasePresenter<ConsumptionListContract.View, ConsumptionListContract.Model> {
        public abstract void getConsumptionList(String user_id,String page,MultipleStatusView multipleStatusView);
        public abstract void getConsumptionListNext(String user_id,String page,MultipleStatusView multipleStatusView);
    }
}
