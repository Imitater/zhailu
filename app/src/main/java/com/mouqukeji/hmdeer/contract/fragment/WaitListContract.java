package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.CancelOrderBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface WaitListContract {
    interface View extends IBaseView {
        void getProgressIndent(AllOrderBean bean);
        void getIndentNext(AllOrderBean bean);
        void getEmpty();
        void cancelOrder(CancelOrderBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(String user_id, String procress);
        Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page);
        Observable<BaseHttpResponse<CancelOrderBean>> cancelOrder(String task_id, String user_id);
    }

    abstract class Presenter extends BasePresenter<WaitListContract.View, WaitListContract.Model> {
        public abstract void getProgressIndent(String user_id,String procress,MultipleStatusView multipleStatusView);
        public abstract void getIndentNext(String user_id,String progress,String page,MultipleStatusView multipleStatusView);
        public abstract void cancelOrder(String task_id, String user_id, MultipleStatusView multipleStatusView);
    }

}
