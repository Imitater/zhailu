package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface AllOrderContract {
    interface View extends IBaseView {
        void getIndent(AllOrderBean bean);
        void getIndentNext(AllOrderBean bean);
        void getEmpty();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AllOrderBean>> getIndent(String user_id);
        Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id,String progress,String page);
    }

    abstract class Presenter extends BasePresenter<AllOrderContract.View, AllOrderContract.Model> {
        public abstract void getIndent(String user_id,MultipleStatusView multipleStatusView);
        public abstract void getIndentNext(String user_id,String progress,String page,MultipleStatusView multipleStatusView);
    }
}
