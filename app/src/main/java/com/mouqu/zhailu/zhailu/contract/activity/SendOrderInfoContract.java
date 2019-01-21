package com.mouqu.zhailu.zhailu.contract.activity;

import com.mouqu.zhailu.zhailu.base.BaseModel;
import com.mouqu.zhailu.zhailu.base.BasePresenter;
import com.mouqu.zhailu.zhailu.base.IBaseView;

public interface SendOrderInfoContract {
    interface View extends IBaseView {

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {

    }
}
