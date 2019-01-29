package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.UserImageBean;
import com.mouqukeji.hmdeer.bean.UserInfoBean;
import com.mouqukeji.hmdeer.bean.UserInfoUpBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface MyInformationContract {
    interface View extends IBaseView {
        void getUserInfo(UserInfoBean bean);
        void putUserInfo(UserInfoUpBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<UserInfoBean>> getUserInfo(String user_id);
        Observable<BaseHttpResponse<UserInfoUpBean>> putUserInfo(String user_id,String nickname,String avatar,String gender,String age,String school_name);
     }

    abstract class Presenter extends BasePresenter<MyInformationContract.View, MyInformationContract.Model> {
        public abstract void getUserInfo(String user_id,MultipleStatusView multipleStatusView);
        public abstract void putUserInfo(String user_id,String nickname,String avatar,String gender,String age,String school_name,MultipleStatusView multipleStatusView);
     }
}
