package com.mouqu.zhailu.zhailu.contract.activity;


import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;

public interface AddressEditContract {
    interface View extends IBaseView {

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<AddressEditContract.View, AddressEditContract.Model> {

    }
}
