package com.mouqu.zhailu.zhailu.contract.fragment;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface CancelledOrderContract {
    interface View extends IBaseView {
        void getProgressIndent(AllOrderBean bean);
        void getIndentNext(AllOrderBean bean);
        void getEmpty();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(String user_id, String procress);
        Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page);
    }

    abstract class Presenter extends BasePresenter<CancelledOrderContract.View, CancelledOrderContract.Model> {
        public abstract void getProgressIndent(String user_id,String procress,MultipleStatusView multipleStatusView);
        public abstract void getIndentNext(String user_id,String progress,String page,MultipleStatusView multipleStatusView);
    }
}
