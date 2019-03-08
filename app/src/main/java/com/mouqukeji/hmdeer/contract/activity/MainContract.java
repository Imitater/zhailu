package com.mouqukeji.hmdeer.contract.activity;


import android.app.Activity;
import android.content.Context;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.CheckVersionBean;
import com.mouqukeji.hmdeer.bean.TokenBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface MainContract {

    interface View extends IBaseView {
        void checkVersion(CheckVersionBean backBean);
        void isNeedUp();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CheckVersionBean>> checkVersion(Context context, String platform, String version_code);
    }

    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {
        public abstract void checkVersion(Context context, String platform, String version_code, MultipleStatusView multipleStatusView);
    }
}
