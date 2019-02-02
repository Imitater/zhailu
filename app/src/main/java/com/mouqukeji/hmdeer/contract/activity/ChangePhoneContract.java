package com.mouqukeji.hmdeer.contract.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.ChangePhoneBean;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface ChangePhoneContract {
    interface View extends IBaseView {
        void changePhone(ChangePhoneBean bean);
        void getCode(CodeBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<ChangePhoneBean>> changePhone(String id,String number,String code);
        Observable<BaseHttpResponse<CodeBean>> getCode(String number,String type);
    }

    abstract class Presenter extends BasePresenter<ChangePhoneContract.View, ChangePhoneContract.Model> {
        public abstract void changePhone(String id,String number,String code, MultipleStatusView multipleStatusView);
        public abstract void getCode(Activity activity,String number,String type,MultipleStatusView multipleStatusView);
    }
}
