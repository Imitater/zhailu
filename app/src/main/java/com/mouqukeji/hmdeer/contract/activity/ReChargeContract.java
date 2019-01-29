package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ReChargeBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface ReChargeContract {
    interface View extends IBaseView {
        void reCharge(ReChargeBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<ReChargeBean>> reCharge(String user_id, String price, String pay_fee, String pay_type);
    }

    abstract class Presenter extends BasePresenter<ReChargeContract.View, ReChargeContract.Model> {
        public abstract void reCharge(String user_id, String price, String pay_fee, String pay_type,MultipleStatusView multipleStatusView);
    }
}
