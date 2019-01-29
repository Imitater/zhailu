package com.mouqukeji.hmdeer.contract.activity;
import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.HelpTakeInfoBean;
import com.mouqukeji.hmdeer.bean.ItemsCategoryBean;
import com.mouqukeji.hmdeer.bean.PreferentialBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface TakeOrderInfoContract {
    interface View extends IBaseView {
        void getTakeInfo(HelpTakeInfoBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<HelpTakeInfoBean>> getTakeInfo(String task_id, String cate_id);
    }

    abstract class Presenter extends BasePresenter<TakeOrderInfoContract.View, TakeOrderInfoContract.Model> {
        public abstract void getTakeInfo(String task_id, String cate_id, MultipleStatusView multipleStatusView);
    }
}
