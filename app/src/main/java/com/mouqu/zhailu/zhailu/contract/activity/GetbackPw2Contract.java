package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.CodeCheckBean;
import com.mouqu.zhailu.zhailu.bean.TokenBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface GetbackPw2Contract {
    interface View extends IBaseView {
        void checkCode(CodeCheckBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CodeCheckBean>> checkCode(String number,String code);
    }

    abstract class Presenter extends BasePresenter<GetbackPw2Contract.View, GetbackPw2Contract.Model> {
        public abstract void checkCode(String number,String code, MultipleStatusView multipleStatusView);
    }
}
