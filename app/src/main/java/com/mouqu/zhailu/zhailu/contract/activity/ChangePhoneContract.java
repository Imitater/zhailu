package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.ChangePhoneBean;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.bean.RegisteredBean;
import com.mouqu.zhailu.zhailu.bean.TokenBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface ChangePhoneContract {
    interface View extends IBaseView {
        void changePhone(ChangePhoneBean bean);
        void getCode(CodeBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<ChangePhoneBean>> changePhone(String id,String number,String code);
        Observable<BaseHttpResponse<CodeBean>> getCode(String number);
    }

    abstract class Presenter extends BasePresenter<ChangePhoneContract.View, ChangePhoneContract.Model> {
        public abstract void changePhone(String id,String number,String code, MultipleStatusView multipleStatusView);
        public abstract void getCode(Activity activity,String number,MultipleStatusView multipleStatusView);
    }
}
