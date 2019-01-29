package com.mouqukeji.hmdeer.contract.fragment;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.IndexBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface IndexContract {
    interface View extends IBaseView {
        void getIndex(IndexBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<IndexBean>> getIndex();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getIndex(MultipleStatusView multipleStatusView);
    }
}
