package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.resetPasswordBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface GetbackPw3Contract {
    interface View extends IBaseView {
        void resetPassword(resetPasswordBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<resetPasswordBean>> resetPassword(String number,String code,String password);
    }

    abstract class Presenter extends BasePresenter<GetbackPw3Contract.View, GetbackPw3Contract.Model> {
        public abstract void resetPassword(Activity activity,String number,String code,String password, MultipleStatusView multipleStatusView);
    }
}
