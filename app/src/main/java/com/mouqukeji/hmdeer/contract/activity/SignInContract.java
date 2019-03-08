package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.PushMesgBean;
import com.mouqukeji.hmdeer.bean.SigninBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface SignInContract {
    interface View extends IBaseView {
        void signIn(SigninBean bean);
        void error();
        void pushMsg(PushMesgBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<SigninBean>> signIn(String number,String password,String did,String platform
                , String app_version, String device_model,String os_version);
        Observable<BaseHttpResponse<PushMesgBean>> pushMsg(String registration_id,String did,String uuid);
    }

    abstract class Presenter extends BasePresenter<SignInContract.View, SignInContract.Model> {
        public abstract void signIn(String number,String password,String did,String platform
                , String app_version, String device_model,String os_version ,MultipleStatusView multipleStatusView);
        public abstract void pushMsg(String registration_id,String did,String uuid,MultipleStatusView multipleStatusView);
    }
}
