package com.mouqu.zhailu.zhailu.contract.fragment;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.AddAddressBean;
import com.mouqu.zhailu.zhailu.contract.activity.MainContract;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface TakeAddressNewReceiverContract {
    interface View extends IBaseView {
        void addAddress(AddAddressBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AddAddressBean>> addAddress(String user_id, String name, String telephone, String address, String detail, String is_default);
    }

    abstract class Presenter extends BasePresenter<TakeAddressNewReceiverContract.View, TakeAddressNewReceiverContract.Model> {
        public abstract void addAddress(String user_id,String name,String telephone,String address,String detail,String is_default,MultipleStatusView multipleStatusView);
    }
}
