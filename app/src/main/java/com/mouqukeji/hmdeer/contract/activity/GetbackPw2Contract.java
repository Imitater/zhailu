package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.CodeBean;
import com.mouqukeji.hmdeer.bean.CodeCheckBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface GetbackPw2Contract {
    interface View extends IBaseView {
        void checkCode(CodeCheckBean bean);
        void getCode(CodeBean bean);
        void isSend();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<CodeCheckBean>> checkCode(String number,String code);
        Observable<BaseHttpResponse<CodeBean>> getCode(String number,String type);
    }

    abstract class Presenter extends BasePresenter<GetbackPw2Contract.View, GetbackPw2Contract.Model> {
        public abstract void checkCode(String number,String code, MultipleStatusView multipleStatusView);
        public abstract void getCode(String number,String type, MultipleStatusView multipleStatusView);
    }
}
