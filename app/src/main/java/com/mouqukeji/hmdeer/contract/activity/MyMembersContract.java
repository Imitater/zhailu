package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.bean.MyVipBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface MyMembersContract {
    interface View extends IBaseView {
        void myVip(MyVipBean bean);
     }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<MyVipBean>> myVip(String user_id);
     }

    abstract class Presenter extends BasePresenter<MyMembersContract.View, MyMembersContract.Model> {
        public abstract void myVip(String user_id,MultipleStatusView multipleStatusView);
     }
}
