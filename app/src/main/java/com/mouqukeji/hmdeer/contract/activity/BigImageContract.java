package com.mouqukeji.hmdeer.contract.activity;


import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;

public interface BigImageContract {
    interface View extends IBaseView {

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {

    }
}
