package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.HelpUniversalInfoBean;
import com.mouqukeji.hmdeer.bean.LocationDownBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface UniversalOrderInfoContract {
    interface View extends IBaseView {
        void getUniversalInfo(HelpUniversalInfoBean bean);
        void locationDown(LocationDownBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<HelpUniversalInfoBean>> getUniversalInfo(String task_id, String cate_id);
        Observable<BaseHttpResponse<LocationDownBean>> locationDown(String user_id, String lat,String lng,String server_id);
    }

    abstract class Presenter extends BasePresenter<UniversalOrderInfoContract.View, UniversalOrderInfoContract.Model> {
        public abstract void getUniversalInfo(String task_id, String cate_id, MultipleStatusView multipleStatusView);
        public abstract void locationDown(String user_id, String lat,String lng,String server_id, MultipleStatusView multipleStatusView);
     }
}
