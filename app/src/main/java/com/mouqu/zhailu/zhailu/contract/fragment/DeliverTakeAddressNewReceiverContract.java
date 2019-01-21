package com.mouqu.zhailu.zhailu.contract.fragment;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;
import com.mouqu.zhailu.zhailu.contract.activity.MainContract;

public interface DeliverTakeAddressNewReceiverContract {
    interface View extends IBaseView {

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {

    }
}
