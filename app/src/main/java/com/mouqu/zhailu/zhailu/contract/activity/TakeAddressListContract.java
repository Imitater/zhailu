package com.mouqu.zhailu.zhailu.contract.activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.bean.AddressListBean;
import com.mouqu.zhailu.zhailu.bean.EditAddressBean;
import com.mouqu.zhailu.zhailu.net.BaseHttpResponse;
import com.mouqu.zhailu.zhailu.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface TakeAddressListContract {
    interface View extends IBaseView {
        void getAddressList(AddressListBean bean);
        void editAddress(EditAddressBean bean);
        void isEmpty();
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AddressListBean>> getAddressList(String user_id);
        Observable<BaseHttpResponse<EditAddressBean>> editAddress(String user_id,String id,String name,String telephone,String address,String detail,String is_default);
    }

    abstract class Presenter extends BasePresenter<TakeAddressListContract.View, TakeAddressListContract.Model> {
        public abstract void getAddressList(String user_id,MultipleStatusView multipleStatusView);
        public abstract void editAddress(String user_id,String id,String name,String telephone,String address,String detail,String is_default,MultipleStatusView multipleStatusView);
    }
}
