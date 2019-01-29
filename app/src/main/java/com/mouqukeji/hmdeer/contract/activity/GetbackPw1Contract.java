package com.mouqukeji.hmdeer.contract.activity;


import android.app.Activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

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
