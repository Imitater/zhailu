package com.mouqukeji.hmdeer.contract.activity;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AddressListBean;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

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
        public abstract void editAddress(String user_id,String id,String name,String telephone,String address,String detail,
                                         String is_default,MultipleStatusView multipleStatusView);
    }
}
