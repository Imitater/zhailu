package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.SigninBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SignInContract {
    interface View extends IBaseView {
        void signIn(SigninBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<SigninBean>> signIn(String number,String password);
    }

    abstract class Presenter extends BasePresenter<SignInContract.View, SignInContract.Model> {
        public abstract void signIn(String number,String password, MultipleStatusView multipleStatusView);
    }
}
