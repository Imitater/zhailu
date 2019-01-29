package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.AddAddressBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface TakeAddressNewReceiverContract {
    interface View extends IBaseView {
        void addAddress(AddAddressBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<AddAddressBean>> addAddress(String user_id, String name, String telephone, String address, String detail, String is_default,String lat,String lng);
    }

    abstract class Presenter extends BasePresenter<TakeAddressNewReceiverContract.View, TakeAddressNewReceiverContract.Model> {
        public abstract void addAddress(String user_id,String name,String telephone,String address,String detail,String is_default,String lat,String lng,MultipleStatusView multipleStatusView);
    }
}
