package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.ChangePasswordBean;
import com.mouqu.zhailu.zhailu.bean.ChangePhoneBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface ChangePwContract {
    interface View extends IBaseView {
        void changePassword(ChangePasswordBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<ChangePasswordBean>> changePassword(Activity activity,String id, String password, String newpassword);
    }

    abstract class Presenter extends BasePresenter<ChangePwContract.View, ChangePwContract.Model> {
        public abstract void changePassword(Activity activity,String id,String password,String newpassword, MultipleStatusView multipleStatusView);
    }
}
