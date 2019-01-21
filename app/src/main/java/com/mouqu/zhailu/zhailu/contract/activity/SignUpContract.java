package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.bean.RegisteredBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SignUpContract {
    interface View extends IBaseView {
        void getCode(CodeBean bean);
        void registered(RegisteredBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CodeBean>> getCode(String number);
        Observable<BaseHttpResponse<RegisteredBean>> registered(String number,String code,String password);
    }

    abstract class Presenter extends BasePresenter<SignUpContract.View, SignUpContract.Model> {
        public abstract void getCode(Activity activity,String number,MultipleStatusView multipleStatusView);
        public abstract void registered(String number,String code,String password,MultipleStatusView multipleStatusView);
    }
}
