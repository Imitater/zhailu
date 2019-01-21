package com.mouqu.zhailu.zhailu.contract.fragment;



import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.UserImageBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

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
