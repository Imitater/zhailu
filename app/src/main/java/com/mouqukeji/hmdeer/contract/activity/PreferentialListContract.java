package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface PreferentialListContract {
    interface View extends IBaseView {
        void getPreferentialList(PreferentialBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<PreferentialBean>> getPreferentialList(String user_id);
    }

    abstract class Presenter extends BasePresenter<PreferentialListContract.View, PreferentialListContract.Model> {
        public abstract void getPreferentialList(String user_id,MultipleStatusView multipleStatusView);
    }
}
