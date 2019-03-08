package com.mouqukeji.hmdeer.contract.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.RegisteredBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SignUpContract {
    interface View extends IBaseView {
        void getCode(CodeBean bean);
        void registered(RegisteredBean bean);
        void isRegistered();
        void isSend();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CodeBean>> getCode(String number,String type);
        Observable<BaseHttpResponse<RegisteredBean>> registered(String number,String code,String password,String school);
    }

    abstract class Presenter extends BasePresenter<SignUpContract.View, SignUpContract.Model> {
        public abstract void getCode(Activity activity,String number,String type,MultipleStatusView multipleStatusView);
        public abstract void registered(String number,String code,String password,String school,MultipleStatusView multipleStatusView);
    }
}
