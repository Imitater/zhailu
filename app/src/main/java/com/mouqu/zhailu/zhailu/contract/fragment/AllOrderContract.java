package com.mouqu.zhailu.zhailu.contract.fragment;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.AllOrderBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

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
