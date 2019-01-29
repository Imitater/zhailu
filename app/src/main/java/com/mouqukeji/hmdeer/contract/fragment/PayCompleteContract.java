package com.mouqukeji.hmdeer.contract.fragment;

import com.mouqukeji.hmdeer.base.BaseModel;
import com.mouqukeji.hmdeer.base.BasePresenter;
import com.mouqukeji.hmdeer.base.IBaseView;
import com.mouqukeji.hmdeer.contract.activity.MainContract;

public interface PayCompleteContract {
    interface View extends IBaseView {

    }

    interface Model extends BaseModel {

    }

    abstract class Presenter extends BasePresenter<MainContract.View, MainContract.Model> {

    }
}
