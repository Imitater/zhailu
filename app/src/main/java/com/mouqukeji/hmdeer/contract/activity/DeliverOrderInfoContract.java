package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.DeliverPlaceOrderBean;
import com.mouqukeji.hmdeer.bean.HelpDeliverInfoBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.bean.SericeBean;
import com.mouqukeji.hmdeer.bean.TrackBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface DeliverOrderInfoContract {
    interface View extends IBaseView {
        void getDeliverInfo(HelpDeliverInfoBean bean);
        void locationDown(LocationDownBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<HelpDeliverInfoBean>> getDeliverInfo(String task_id, String cate_id);
        Observable<BaseHttpResponse<LocationDownBean>> locationDown(String user_id, String lat,String lng,String server_id);
    }

    abstract class Presenter extends BasePresenter<DeliverOrderInfoContract.View, DeliverOrderInfoContract.Model> {
        public abstract void getDeliverInfo(String task_id, String cate_id, MultipleStatusView multipleStatusView);
        public abstract void locationDown(String user_id, String lat,String lng,String server_id, MultipleStatusView multipleStatusView);
    }
}
