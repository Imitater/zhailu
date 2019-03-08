package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.IntegralListBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface JiFenListContract {
    interface View extends IBaseView {
        void jiFenList(IntegralListBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<IntegralListBean>> jiFenList(String user_id);

    }

    abstract class Presenter extends BasePresenter<JiFenListContract.View, JiFenListContract.Model> {
        public abstract void jiFenList(String user_id ,MultipleStatusView multipleStatusView);
    }
}
