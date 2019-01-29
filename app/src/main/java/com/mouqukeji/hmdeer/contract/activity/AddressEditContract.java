package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.bean.EditAddressBean;
import com.mouqukeji.hmdeer.net.BaseHttpResponse;
import com.mouqukeji.hmdeer.ui.widget.MultipleStatusView;

import io.reactivex.Observable;

public interface AddressEditContract {
    interface View extends IBaseView {
        void editAddress(EditAddressBean bean);
    }

    interface Model extends BaseModel {
        Observable<BaseHttpResponse<EditAddressBean>> editAddress(String user_id,String id,String name,String telephone,String address,String detail,String is_defaul,String lat,String lng);
    }

    abstract class Presenter extends BasePresenter<AddressEditContract.View, AddressEditContract.Model> {
        public abstract void editAddress(String user_id,String id,String name,String telephone,String address,String detail,String is_defaul,String lat,String lng,MultipleStatusView multipleStatusView);
    }
}
