package com.mouqukeji.hmdeer.contract.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ChangePasswordBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

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
