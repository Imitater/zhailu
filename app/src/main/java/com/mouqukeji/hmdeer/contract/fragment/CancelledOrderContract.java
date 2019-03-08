package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AllOrderBean;
import com.mouqukeji.hmdeer.bean.DeleteOrderBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface CancelledOrderContract {
    interface View extends IBaseView {
        void getProgressIndent(AllOrderBean bean);
        void getIndentNext(AllOrderBean bean);
        void getEmpty();
        void deleteOrder(DeleteOrderBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AllOrderBean>> getProgressIndent(String user_id, String procress);
        Observable<BaseHttpResponse<AllOrderBean>> getIndentNext(String user_id, String progress, String page);
        Observable<BaseHttpResponse<DeleteOrderBean>> deleteOrder(String task_id);
    }

    abstract class Presenter extends BasePresenter<CancelledOrderContract.View, CancelledOrderContract.Model> {
        public abstract void getProgressIndent(String user_id,String procress,MultipleStatusView multipleStatusView);
        public abstract void getIndentNext(String user_id,String progress,String page,MultipleStatusView multipleStatusView);
        public abstract void deleteOrder(String task_id, MultipleStatusView multipleStatusView);
    }
}
