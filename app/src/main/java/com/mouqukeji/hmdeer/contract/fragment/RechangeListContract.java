package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.RechangeListBean;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface RechangeListContract {
    interface View extends IBaseView {
        void getRechangeList(RechangeListBean bean);
        void getRechangeListNext(RechangeListBean bean);
        void isEmpty();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<RechangeListBean>> getRechangeList(String user_id,String page);
        Observable<BaseHttpResponse<RechangeListBean>> getRechangeListNext(String user_id,String page);
    }

    abstract class Presenter extends BasePresenter<RechangeListContract.View, RechangeListContract.Model> {
        public abstract void getRechangeList(String user_id,String page,MultipleStatusView multipleStatusView);
        public abstract void getRechangeListNext(String user_id,String page,MultipleStatusView multipleStatusView);
    }
}
