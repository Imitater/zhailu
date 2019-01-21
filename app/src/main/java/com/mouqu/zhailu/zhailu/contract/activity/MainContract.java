package com.mouqu.zhailu.zhailu.contract.activity;


import android.app.Activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.IndexBean;
import com.mouqu.zhailu.zhailu.bean.TokenBean;
import com.mouqu.zhailu.zhailu.contract.fragment.IndexContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface MainContract {

    interface View extends IBaseView {
        void getToken(TokenBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<TokenBean>> getToken(String token);
    }

    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {
        public abstract void getToken(Activity activity,String token, MultipleStatusView multipleStatusView);
    }
}
