package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.HelpSendInfoBean;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SendOrderInfoContract {
    interface View extends IBaseView {
        void getSendInfo(HelpSendInfoBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<HelpSendInfoBean>> getSendInfo(String task_id, String cate_id);
    }

    abstract class Presenter extends BasePresenter<SendOrderInfoContract.View, SendOrderInfoContract.Model> {
        public abstract void getSendInfo(String task_id, String cate_id, MultipleStatusView multipleStatusView);
    }
}
