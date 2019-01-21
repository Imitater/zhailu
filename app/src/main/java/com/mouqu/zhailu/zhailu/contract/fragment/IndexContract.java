package com.mouqu.zhailu.zhailu.contract.fragment;


import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.IndexBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

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
