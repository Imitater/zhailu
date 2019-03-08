package com.mouqukeji.hmdeer.contract.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SplashContract {

    interface View extends IBaseView {
        void getToken(TokenBean bean);
        void restart();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<TokenBean>> getToken(String token);
    }

    abstract class Presenter extends BasePresenter<SplashContract.View, SplashContract.Model> {
        public abstract void getToken(Activity activity,String token, MultipleStatusView multipleStatusView);
    }
}
