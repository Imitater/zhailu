package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.CodeBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface GetbackPw1Contract {
    interface View extends IBaseView {
        void getCode(CodeBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CodeBean>> getCode(String number);
    }

    abstract class Presenter extends BasePresenter<GetbackPw1Contract.View, GetbackPw1Contract.Model> {
        public abstract void getCode(Activity activity,String number,MultipleStatusView multipleStatusView);
     }

}
