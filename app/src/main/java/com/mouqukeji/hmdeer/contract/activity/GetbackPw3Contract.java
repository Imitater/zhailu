package com.mouqukeji.hmdeer.contract.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.resetPasswordBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

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
