package com.mouqukeji.hmdeer.contract.fragment;



import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface MyContract {
    interface View extends IBaseView {
        void getUserImage(UserImageBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<UserImageBean>> getUserImage(String user_id);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getUserImage(String user_id,MultipleStatusView multipleStatusView);
    }
}
